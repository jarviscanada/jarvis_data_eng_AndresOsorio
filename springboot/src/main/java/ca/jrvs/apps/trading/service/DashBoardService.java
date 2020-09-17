package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.view.PortfolioView;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityRow;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DashBoardService {

  private TraderDao traderDao;
  private PositionDao positionDao;
  private AccountDao accountDao;
  private QuoteDao quoteDao;

  @Autowired
  public DashBoardService(TraderDao traderDao, PositionDao positionDao,
      AccountDao accountDao, QuoteDao quoteDao) {
    this.traderDao = traderDao;
    this.positionDao = positionDao;
    this.accountDao = accountDao;
    this.quoteDao = quoteDao;
  }

  private void validateId(Integer id) {
    if (id == null || id <= 0)
      throw new IllegalArgumentException("Illegal traderId");

    traderDao.findById(id).orElseThrow(() -> new IllegalArgumentException("traderId " + id + " does not exist"));
  }

  /**
   * Create and return a TraderAccountView by trader ID
   * - get trader account by id
   * - get trader info by id
   * - create and return a TraderAccountView
   *
   * @param traderId must be non-null
   * @return TraderAccountView
   * @throws IllegalArgumentException is traderId is null or not found
   */
  public TraderAccountView getTraderAccount(Integer traderId) {
    validateId(traderId);

    Trader trader = traderDao.findById(traderId).get();
    Account account = accountDao.findById(traderId).get();

    return new TraderAccountView(account, trader);
  }

  /**
   * Create and return a PortfolioView by trader ID
   * - get account by trader id
   * - get positions by account id
   * - create and return a PortfolioView
   *
   * @param traderId must be non-null
   * @return PortfolioView
   * @throws IllegalArgumentException if traderId is null or not found
   */
  public PortfolioView getPortfolioViewByTraderId(Integer traderId) {
    validateId(traderId);

    List<Position> positions = positionDao.findAll().stream().filter(p -> p.getId() == traderId).collect(Collectors.toList());

    List<SecurityRow> securityRows = new LinkedList<>();
    positions.stream().forEach(p -> {
      Quote quote = quoteDao.findById(p.getTicker()).get();
      SecurityRow securityRow = new SecurityRow(p, quote, quote.getTicker());
      securityRows.add(securityRow);
    });

    return new PortfolioView(securityRows);
  }
}
