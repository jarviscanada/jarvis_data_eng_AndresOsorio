package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.SecurityOrder.Status;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import java.sql.Date;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderAccountServiceIntTest {

  @Autowired
  private TraderAccountService traderAccountService;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;
  @Autowired
  private QuoteDao quoteDao;
  @Autowired
  private SecurityOrderDao securityOrderDao;

  private TraderAccountView traderAccountView;
  private Trader trader1, trader2;
  private Account account1, account2;
  private Quote quote1, quote2;
  private SecurityOrder securityOrder1, securityOrder2;

  @Before
  public void setUp() throws Exception {
    trader1 = new Trader();
    trader1.setFirstName("Joe");
    trader1.setLastName("Owen");
    trader1.setEmail("something@.com");
    trader1.setDob(Date.valueOf("1980-01-20"));
    trader1.setCountry("Canada");

    trader2 = new Trader();
    trader2.setFirstName("Mike");
    trader2.setLastName("Foster");
    trader2.setEmail("mike@.com");
    trader2.setDob(Date.valueOf("1989-09-05"));
    trader2.setCountry("US");

    traderDao.saveAll(Arrays.asList(trader1, trader2));

    quote1 = new Quote();
    quote1.setAskPrice(10d);
    quote1.setAskSize(10);
    quote1.setBidPrice(10.2d);
    quote1.setBidSize(10);
    quote1.setTicker("GOOGL");
    quote1.setLastPrice(10.1d);

    quote2 = new Quote();
    quote2.setTicker("AAPL");
    quote2.setLastPrice(15.4d);
    quote2.setAskPrice(9.5d);
    quote2.setAskSize(7);
    quote2.setBidPrice(45.9d);
    quote2.setBidSize(8);

    quoteDao.saveAll(Arrays.asList(quote1, quote2));

    account1 = new Account();
    account1.setAmount(100.5);
    account1.setTraderId(trader1.getId());

    account2 = new Account();
    account2.setAmount(45000.2);
    account2.setTraderId(trader2.getId());

    accountDao.saveAll(Arrays.asList(account1, account2));

    securityOrder1 = new SecurityOrder();
    securityOrder1.setAccountId(account1.getId());
    securityOrder1.setTicker(quote1.getTicker());
    securityOrder1.setPrice(45.2);
    securityOrder1.setSize(5);
    securityOrder1.setNotes("Notes");
    securityOrder1.setStatus(Status.FILLED.toString());

    securityOrder2 = new SecurityOrder();
    securityOrder2.setAccountId(account1.getId());
    securityOrder2.setTicker(quote2.getTicker());
    securityOrder2.setPrice(26.3);
    securityOrder2.setSize(9);
    securityOrder2.setNotes("Notes");
    securityOrder2.setStatus(Status.PENDING.toString());

    securityOrderDao.saveAll(Arrays.asList(securityOrder1, securityOrder2));
  }

  @After
  public void tearDown() throws Exception {
    securityOrderDao.deleteAll();
    accountDao.deleteAll();
    traderDao.deleteAll();
    quoteDao.deleteAll();
  }

  @Test
  public void createTraderAndAccount() {
    trader1 = new Trader();
    trader1.setFirstName("Joe");
    trader1.setLastName("Owen");
    trader1.setEmail("something@.com");
    trader1.setDob(Date.valueOf("1980-01-20"));
    trader1.setCountry("Canada");

    trader2 = new Trader();
    trader2.setFirstName("Mike");
    trader2.setLastName("Foster");
    trader2.setEmail("mike@.com");
    trader2.setDob(Date.valueOf("1989-09-05"));
    trader2.setCountry("US");

    traderAccountView = traderAccountService.createTraderAndAccount(trader1);
    assertTrue(trader1.equals(traderAccountView.getTrader()));
    assertEquals(trader1.getId(), traderAccountView.getAccount().getTraderId());

    traderAccountView = traderAccountService.createTraderAndAccount(trader2);
    assertTrue(trader2.equals(traderAccountView.getTrader()));
    assertEquals(trader2.getId(), traderAccountView.getAccount().getTraderId());

    trader1 = new Trader();
    try {
      traderAccountService.createTraderAndAccount(trader1);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    trader1.setId(3);
    try {
      traderAccountService.createTraderAndAccount(trader1);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void deleteTraderById() {
    try {
      traderAccountService.deleteTraderById(trader1.getId());
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    account1.setAmount(0.0);
    accountDao.save(account1);
    try {
      traderAccountService.deleteTraderById(trader1.getId());
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    assertEquals(2, traderDao.count());
    assertEquals(2, accountDao.count());
    assertEquals(2, securityOrderDao.count());

    securityOrder2.setStatus(Status.FILLED.toString());
    securityOrderDao.save(securityOrder2);
    traderAccountService.deleteTraderById(trader1.getId());

    assertEquals(1, traderDao.count());
    assertEquals(1, accountDao.count());
    assertEquals(0, securityOrderDao.count());
  }

  @Test
  public void deposit() {
    try {
      traderAccountService.deposit(-1, 50.9);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try {
      traderAccountService.deposit(1, -6.9);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    double previousAmount = account1.getAmount();

    Account account = traderAccountService.deposit(1, 150.0);

    assertTrue(account.getAmount().doubleValue() == previousAmount + 150.0);
  }

  @Test
  public void withdraw() {
    try {
      traderAccountService.withdraw(-1, 50.9);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try {
      traderAccountService.withdraw(1, -6.9);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try {
      traderAccountService.withdraw(1, 100.6);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    double previousAmount = account1.getAmount();

    Account account = traderAccountService.withdraw(1, 100.0);

    assertTrue(account.getAmount().doubleValue() == previousAmount - 100.0);
  }
}