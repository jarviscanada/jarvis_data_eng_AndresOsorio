package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.SecurityOrder.Status;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
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
public class SecurityOrderDaoIntTest {

  @Autowired
  private SecurityOrderDao securityOrderDao;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private QuoteDao quoteDao;
  @Autowired
  private AccountDao accountDao;

  private SecurityOrder securityOrder1, securityOrder2;
  private Trader trader1, trader2;
  private Quote quote1, quote2;
  private Account account1, account2;

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

    account1 = new Account();
    account1.setAmount(100.5);
    account1.setTraderId(trader1.getId());

    account2 = new Account();
    account2.setAmount(45000.2);
    account2.setTraderId(trader2.getId());

    accountDao.saveAll(Arrays.asList(account1, account2));

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

    securityOrder1 = new SecurityOrder();
    securityOrder1.setAccountId(account1.getId());
    securityOrder1.setTicker(quote1.getTicker());
    securityOrder1.setPrice(45.2);
    securityOrder1.setSize(5);
    securityOrder1.setNotes("Notes");
    securityOrder1.setStatus(Status.FILLED.toString());

    securityOrder2 = new SecurityOrder();
    securityOrder2.setAccountId(account2.getId());
    securityOrder2.setTicker(quote2.getTicker());
    securityOrder2.setPrice(26.3);
    securityOrder2.setSize(9);
    securityOrder2.setNotes("Notes");
    securityOrder2.setStatus(Status.CANCELLED.toString());

    securityOrderDao.saveAll(Arrays.asList(securityOrder1, securityOrder2));
  }

  @After
  public void tearDown() throws Exception {
    securityOrderDao.deleteAll();
  }

  @Test
  public void save() {
    SecurityOrder securityOrder = new SecurityOrder();
    securityOrder.setAccountId(account2.getId());
    securityOrder.setTicker(quote2.getTicker());
    securityOrder.setPrice(15.6);
    securityOrder.setSize(4);
    securityOrder.setNotes("Notes");
    securityOrder.setStatus(Status.PENDING.toString());

    SecurityOrder returnedSecurityOrder = securityOrderDao.save(securityOrder);

    assertEquals(securityOrder, returnedSecurityOrder);
  }

  @Test
  public void saveAll() {
    securityOrder2 = new SecurityOrder();
    securityOrder2.setAccountId(account1.getId());
    securityOrder2.setTicker(quote1.getTicker());
    securityOrder2.setPrice(5.4);
    securityOrder2.setSize(6);
    securityOrder2.setNotes("Notes");
    securityOrder2.setStatus(Status.CANCELLED.toString());

    securityOrder1 = new SecurityOrder();
    securityOrder1.setAccountId(account2.getId());
    securityOrder1.setTicker(quote2.getTicker());
    securityOrder1.setPrice(15.6);
    securityOrder1.setSize(4);
    securityOrder1.setNotes("Notes");
    securityOrder1.setStatus(Status.PENDING.toString());

    List<SecurityOrder> returnedSecurityOrders = (List)securityOrderDao.saveAll(Arrays.asList(securityOrder1, securityOrder2));

    assertEquals(2, returnedSecurityOrders.size());
    assertEquals(quote2.getTicker(), returnedSecurityOrders.get(0).getTicker());
    assertEquals(quote1.getTicker(), returnedSecurityOrders.get(1).getTicker());
  }

  @Test
  public void existsById() {
    assertTrue(securityOrderDao.existsById(1));
  }

  @Test
  public void findAll() {
    List<SecurityOrder> returnedSecurityOrders = securityOrderDao.findAll();

    assertTrue(returnedSecurityOrders.size() > 0);
    assertTrue( returnedSecurityOrders.get(0).getId() == 1);
  }

  @Test
  public void findAllById() {
    List<Integer> ids = Arrays.asList(securityOrder1.getId(), securityOrder2.getId());
    List<SecurityOrder> returnedSecurityOrders = securityOrderDao.findAllById(ids);

    assertEquals(2, returnedSecurityOrders.size());
    assertTrue(returnedSecurityOrders.get(0).getAccountId() == account1.getId());
    assertTrue(returnedSecurityOrders.get(1).getAccountId() == account2.getId());
  }

  @Test
  public void count() {
    assertEquals(2, securityOrderDao.count());

    securityOrder1 = new SecurityOrder();
    securityOrder1.setAccountId(account2.getId());
    securityOrder1.setTicker(quote1.getTicker());
    securityOrder1.setPrice(45.0);
    securityOrder1.setSize(9);
    securityOrder1.setNotes("Notes");
    securityOrder1.setStatus(Status.CANCELLED.toString());

    securityOrderDao.save(securityOrder1);

    assertEquals(3, securityOrderDao.count());
  }

  @Test
  public void deleteById() {
    assertEquals(2, securityOrderDao.count());

    securityOrderDao.deleteById(1);

    assertEquals(1, securityOrderDao.count());

    securityOrderDao.deleteById(2);

    assertEquals(0, securityOrderDao.count());
  }
}