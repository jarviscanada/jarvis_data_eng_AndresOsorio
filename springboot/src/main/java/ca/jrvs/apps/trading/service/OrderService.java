package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.SecurityOrder.Status;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

  private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

  private QuoteDao quoteDao;
  private AccountDao accountDao;
  private SecurityOrderDao securityOrderDao;
  private PositionDao positionDao;

  @Autowired
  public OrderService(QuoteDao quoteDao, AccountDao accountDao,
      SecurityOrderDao securityOrderDao, PositionDao positionDao) {
    this.quoteDao = quoteDao;
    this.accountDao = accountDao;
    this.securityOrderDao = securityOrderDao;
    this.positionDao = positionDao;
  }

  /**
   * Execute a market order
   * <p>
   * - validate the order (e.g. accountId, size and ticker) - create a security order - handle buy
   * or sell order - buy order: check account balance - sell order: check position for the ticker -
   * update security order status - save and return the security order
   *
   * @param orderDto market order
   * @return SecurityOrder from security_order table
   * @throws org.springframework.dao.DataAccessException if unable to get data from DAO
   * @throws IllegalArgumentException                    if invalid input
   */
  public SecurityOrder executeMarketOrder(MarketOrderDto orderDto) {
    if (orderDto.getId() == null || orderDto.getSize() == null || orderDto.getTicker() == null
        || orderDto.getAction() == null)
      throw new IllegalArgumentException("MarketOrderDto fields must be non-null");

    if (orderDto.getId() <= 0 || orderDto.getSize() <= 0)
      throw new IllegalArgumentException("Id and size must be > 0");

    Optional<Quote> quoteOpt = quoteDao.findById(orderDto.getTicker());
    Optional<Account> accountOpt = accountDao.findById(orderDto.getId());

    if (!quoteOpt.isPresent() || !accountOpt.isPresent()) {
      throw new DataRetrievalFailureException("Unable to get quote/account data");
    }

    Quote quote = quoteOpt.get();
    Account account = accountOpt.get();

    SecurityOrder securityOrder = new SecurityOrder();
    securityOrder.setAccountId(orderDto.getId());
    securityOrder.setSize(orderDto.getSize());
    securityOrder.setTicker(orderDto.getTicker());
    securityOrder.setNotes("");
    securityOrder.setStatus(Status.PENDING.toString());

    if (orderDto.getAction().equals("buy")) {
      securityOrder.setPrice(quote.getBidPrice());
      handleBuyMarketOrder(securityOrder, account);
    } else {
      securityOrder.setPrice(quote.getAskPrice());
      handleSellMarketOrder(securityOrder, account);
    }

    return securityOrder;
  }

  /**
   * Helper method that executes a buy order
   *
   * @param securityOrder to be saved in the database
   * @param account       account
   */
  protected void handleBuyMarketOrder(SecurityOrder securityOrder, Account account) {
    double total = securityOrder.getPrice() * securityOrder.getSize();

    if (total > account.getAmount()) {
      securityOrder.setStatus(Status.CANCELLED.toString());
      securityOrder.setNotes("Insufficient funds");
    } else {
      account.setAmount(account.getAmount() - total);
      securityOrder.setStatus(Status.FILLED.toString());
      accountDao.save(account);
    }

    securityOrderDao.save(securityOrder);
  }

  /**
   * Helper method that executes a sell order
   *
   * @param securityOrder to be saved in the database
   * @param account       account
   */
  protected void handleSellMarketOrder(SecurityOrder securityOrder, Account account) {
    double total = securityOrder.getPrice() * securityOrder.getSize();

    account.setAmount(account.getAmount() + total);
    securityOrder.setStatus(Status.FILLED.toString());

    accountDao.save(account);
    securityOrderDao.save(securityOrder);
  }

}
