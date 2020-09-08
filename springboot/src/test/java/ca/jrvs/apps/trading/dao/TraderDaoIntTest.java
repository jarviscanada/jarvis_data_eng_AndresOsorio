package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
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
public class TraderDaoIntTest {

  @Autowired
  private TraderDao traderDao;

  private Trader trader;

  @Before
  public void setUp() throws Exception {
    trader = new Trader();
    trader.setFirstName("Joe");
    trader.setLastName("Owen");
    trader.setEmail("something@.com");
    trader.setDob(Date.valueOf("1980-01-20"));
    trader.setCountry("Canada");
    traderDao.save(trader);
  }

  @After
  public void tearDown() throws Exception {
    traderDao.deleteAll();
  }

  @Test
  public void save() {
    Trader trader = new Trader();
    trader.setFirstName("Mike");
    trader.setLastName("Foster");
    trader.setEmail("mike@.com");
    trader.setDob(Date.valueOf("1989-09-05"));
    trader.setCountry("US");
    Trader returnedTrader = traderDao.save(trader);

    assertTrue(trader.getId() > 0);
    assertEquals(trader, returnedTrader);
  }

  @Test
  public void findById() {
    Trader returnedTrader = traderDao.findById(1).get();

    assertEquals(trader.getFirstName(), returnedTrader.getFirstName());
    assertTrue(trader.equals(returnedTrader));
  }

  @Test
  public void saveAll() {
    Trader trader1 = new Trader(), trader2 = new Trader(), trader3 = new Trader();

    trader1.setFirstName("Oscar");
    trader1.setLastName("Correa");
    trader1.setEmail("oscar@.com");
    trader1.setDob(Date.valueOf("1992-07-06"));
    trader1.setCountry("Argentina");

    trader2.setFirstName("Diana");
    trader2.setLastName("Nilson");
    trader2.setEmail("diana@.com");
    trader2.setDob(Date.valueOf("1999-12-15"));
    trader2.setCountry("Mexico");

    trader3.setFirstName("Allison");
    trader3.setLastName("Roberts");
    trader3.setEmail("Ally@.com");
    trader3.setDob(Date.valueOf("1984-10-19"));
    trader3.setCountry("Australia");

    List<Trader> traders = Arrays.asList(trader1, trader2, trader3);
    List<Trader> returnedTraders = (List)traderDao.saveAll(traders);

    assertEquals(3, returnedTraders.size());
    assertTrue(traders.equals(returnedTraders));

  }

  @Test
  public void existsById() {
    assertTrue(traderDao.existsById(1));
  }

  @Test
  public void findAll() {
    List<Trader> returnedTraders = traderDao.findAll();

    assertTrue(returnedTraders.size() > 0);
    assertTrue( returnedTraders.get(0).getId() == 1);
  }

  @Test
  public void findAllById() {
    Trader trader1 = new Trader();
    trader1.setFirstName("Mike");
    trader1.setLastName("Foster");
    trader1.setEmail("mike@.com");
    trader1.setDob(Date.valueOf("1989-09-05"));
    trader1.setCountry("US");
    traderDao.save(trader1);

    List<Integer> ids = Arrays.asList(trader.getId(), trader1.getId());
    List<Trader> returnedTraders = traderDao.findAllById(ids);

    assertEquals(2, returnedTraders.size());
    assertEquals("Joe", returnedTraders.get(0).getFirstName());
    assertEquals("Mike", returnedTraders.get(1).getFirstName());
  }

  @Test
  public void count() {
    assertEquals(1, traderDao.count());

    Trader trader1 = new Trader();
    trader1.setFirstName("Mike");
    trader1.setLastName("Foster");
    trader1.setEmail("mike@.com");
    trader1.setDob(Date.valueOf("1989-09-05"));
    trader1.setCountry("US");
    traderDao.save(trader1);

    assertEquals(2, traderDao.count());
  }

  @Test
  public void deleteById() {
    assertEquals(1, traderDao.count());

    traderDao.deleteById(1);

    assertEquals(0, traderDao.count());
  }
}