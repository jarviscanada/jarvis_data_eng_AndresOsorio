package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
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
public class AccountDaoIntTest {

  @Autowired
  private AccountDao accountDao;
  @Autowired
  private TraderDao traderDao;

  private Account account;
  private Trader trader, trader1, trader2;

  @Before
  public void setUp() throws Exception {
    trader = new Trader();
    trader.setFirstName("Joe");
    trader.setLastName("Owen");
    trader.setEmail("something@.com");
    trader.setDob(Date.valueOf("1980-01-20"));
    trader.setCountry("Canada");
    traderDao.save(trader);

    trader1 = new Trader();
    trader1.setFirstName("Oscar");
    trader1.setLastName("Correa");
    trader1.setEmail("oscar@.com");
    trader1.setDob(Date.valueOf("1992-07-06"));
    trader1.setCountry("Argentina");
    traderDao.save(trader1);

    trader2 = new Trader();
    trader2.setFirstName("Mike");
    trader2.setLastName("Foster");
    trader2.setEmail("mike@.com");
    trader2.setDob(Date.valueOf("1989-09-05"));
    trader2.setCountry("US");
    traderDao.save(trader2);

    account = new Account();
    account.setAmount(100.5);
    account.setTraderId(trader.getId());
    accountDao.save(account);
  }

  @After
  public void tearDown() throws Exception {
    accountDao.deleteAll();
  }

  @Test
  public void save() {
    Account account = new Account();
    account.setAmount(45000.2);
    account.setTraderId(trader.getId());

    Account returnedAccount = accountDao.save(account);
    assertTrue(returnedAccount.getTraderId() == trader.getId());

    account = new Account();
    account.setAmount(2600.0);
    account.setTraderId(trader2.getId());

    returnedAccount = accountDao.save(account);
    assertTrue(returnedAccount.getTraderId() == trader2.getId());
  }

  @Test
  public void saveAll() {
    Account account1 = new Account();
    account1.setAmount(28000.6);
    account1.setTraderId(trader.getId());

    Account account2 = new Account();
    account2.setAmount(5600.4);
    account2.setTraderId(trader1.getId());

    Account account3 = new Account();
    account3.setAmount(16000.3);
    account3.setTraderId(trader2.getId());

    List<Account> accounts = Arrays.asList(account1, account2, account3);
    List<Account> returnedAccount = (List)accountDao.saveAll(accounts);

    assertEquals(3, returnedAccount.size());
    assertTrue(accounts.equals(returnedAccount));
  }

  @Test
  public void existsById() {
    assertTrue(accountDao.existsById(1));
  }

  @Test
  public void findAll() {
    List<Account> returnedAccounts = accountDao.findAll();

    assertTrue(returnedAccounts.size() > 0);
    assertTrue( returnedAccounts.get(0).getId() == 1);
  }

  @Test
  public void findAllById() {
    Account account1 = new Account();
    account1.setAmount(28000.6);
    account1.setTraderId(trader2.getId());
    accountDao.save(account1);

    List<Integer> ids = Arrays.asList(account.getId(), account1.getId());
    List<Account> returnedAccounts = accountDao.findAllById(ids);

    assertEquals(2, returnedAccounts.size());
    assertTrue(returnedAccounts.get(0).getTraderId() == trader.getId());
    assertTrue(returnedAccounts.get(1).getTraderId() == trader2.getId());
  }

  @Test
  public void count() {
    assertEquals(1, accountDao.count());

    account = new Account();
    account.setAmount(28000.6);
    account.setTraderId(trader2.getId());
    accountDao.save(account);

    assertEquals(2, accountDao.count());
  }

  @Test
  public void deleteById() {
    assertEquals(1, accountDao.count());

    accountDao.deleteById(1);

    assertEquals(0, accountDao.count());
  }
}