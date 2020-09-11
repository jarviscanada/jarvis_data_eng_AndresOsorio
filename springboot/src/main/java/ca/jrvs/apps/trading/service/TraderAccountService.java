package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.SecurityOrder.Status;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderAccountService {

  private TraderDao traderDao;
  private AccountDao accountDao;
  private SecurityOrderDao securityOrderDao;
  private PositionDao positionDao;

  @Autowired
  public TraderAccountService(TraderDao traderDao, AccountDao accountDao,
      SecurityOrderDao securityOrderDao, PositionDao positionDao) {
    this.traderDao = traderDao;
    this.accountDao = accountDao;
    this.securityOrderDao = securityOrderDao;
    this.positionDao = positionDao;
  }

  private Account validateId(Integer traderId) {
    if (traderId == null || traderId.intValue() < 1)
      throw new IllegalArgumentException("Illegal traderId");

    Optional<Account> result = accountDao.findById(traderId);
    if (!result.isPresent())
      throw new IllegalArgumentException("traderId " + traderId + " does not exist");

    return result.get();
  }

  private void validateFunds(Double funds) {
    if (funds <= 0)
      throw new IllegalArgumentException("Amount must be greater than 0");
  }

  /**
   * Create a new trader and initialize a new account with 0 amount
   * - validate user input (all fields must be non-null)
   * - create a trader
   * - create an account
   * - create, setup and return a new TraderAccountView
   *
   * Assumption: to simplify logic, each trader has only one account where traderId == accountId
   *
   * @param trader cannot be null; all fields must be non-null except for id (auto-generated by db)
   * @return TraderAccountView
   * @throws IllegalArgumentException if a trader has null fields or id is not null
   */
  public TraderAccountView createTraderAndAccount(Trader trader) {
    if (trader.getFirstName() == null || trader.getLastName() == null ||
        trader.getEmail() == null || trader.getDob() == null || trader.getCountry() == null)
      throw new IllegalArgumentException("All trader fields (except id) must be non-null");

    if (trader.getId() != null)
      throw new IllegalArgumentException("Trader id must be null because it is auto-generated by db");

    traderDao.save(trader);

    Account account = new Account();
    account.setTraderId(trader.getId());
    account.setAmount(0.0);

    accountDao.save(account);

    TraderAccountView traderAccountView = new TraderAccountView(account, trader);

    return traderAccountView;
  }

  /**
   * A trader can be deleted iff it has no open positions and 0 cash balance
   * - validate traderId
   * - get trader account by traderId and check account balance
   * - get positions by accountId and check positions
   * - delete all securityOrders, account, trader (in this order)
   *
   * @param traderId must be non-null
   * @throws IllegalArgumentException if traderId is null or not found or unable to delete
   */
  public void deleteTraderById(Integer traderId) {
    Account account = validateId(traderId);

    if (account.getAmount() > 0)
      throw new IllegalArgumentException("Trader's balance must be 0 to be able to be deleted");

    List<SecurityOrder> orders = securityOrderDao.findAll();

    orders.forEach(order -> {
      if (order.getAccountId() == account.getId() && order.getStatus().equals(Status.PENDING.toString()))
        throw new IllegalArgumentException("Trader's positions must be closed to be able to be deleted");
    });

    securityOrderDao.deleteAllByAccountId(account.getId());
    accountDao.deleteById(account.getId());
    traderDao.deleteById(traderId);
  }

  /**
   * Deposit funds to an account by traderId
   * - validate user input
   * - account = accountDao.findByTraderId
   * - update the amount of the account
   *
   * @param traderId must be non-null
   * @param funds must be > 0
   * @return updated Account
   * @throws IllegalArgumentException if traderId is null or not found, or funds <= 0
   */
  public Account deposit(Integer traderId, Double funds) {
    Account account = validateId(traderId);
    validateFunds(funds);

    account.setAmount(account.getAmount() + funds);

    return accountDao.save(account);
  }

  /**
   * Withdraw funds from an account by traderId
   * - validate user input
   * - account = accountDao.findIdByTraderId
   * - update the amount of the account
   *
   * @param traderId must be non-null
   * @param funds must be > 0
   * @return updated Account
   * @throws IllegalArgumentException id traderId is null or not found, or funds <= 0 or account.amount - funds < 0
   */
  public Account withdraw(Integer traderId, Double funds) {
    Account account = validateId(traderId);
    validateFunds(funds);

    if (account.getAmount() - funds < 0)
      throw new IllegalArgumentException("Insufficient funds");

    account.setAmount(account.getAmount() - funds);

    return accountDao.save(account);
  }
}
