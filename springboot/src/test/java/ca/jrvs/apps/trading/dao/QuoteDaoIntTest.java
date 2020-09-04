package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
public class QuoteDaoIntTest {

  @Autowired
  private QuoteDao quoteDao;

  @Autowired
  private MarketDataDao marketDataDao;

  private Quote quote1;

  @Before
  public void setUp() throws Exception {
    quote1 = new Quote();
    quote1.setAskPrice(10d);
    quote1.setAskSize(10);
    quote1.setBidPrice(10.2d);
    quote1.setBidSize(10);
    quote1.setTicker("GOOGL");
    quote1.setLastPrice(10.1d);
    quoteDao.save(quote1);
  }

  @After
  public void tearDown() throws Exception {
    quoteDao.deleteAll();
  }

  @Test
  public void save() {
    quote1.setLastPrice(15.4d);
    quote1.setAskPrice(17.8d);
    quote1.setAskSize(3);
    quote1.setBidPrice(19.0d);
    quote1.setBidSize(4);

    Quote returnedQuote = quoteDao.save(quote1);

    assertEquals(returnedQuote, quote1);

    Optional<IexQuote> iexQuote = marketDataDao.findById("aapl");

    Quote quote2 = new Quote();

    quote2.setAskPrice(iexQuote.get().getIexAskPrice());
    quote2.setAskSize((int)iexQuote.get().getIexAskSize());
    quote2.setBidPrice(iexQuote.get().getIexBidPrice());
    quote2.setBidSize((int)iexQuote.get().getIexBidSize());
    quote2.setTicker(iexQuote.get().getSymbol());
    quote2.setLastPrice(iexQuote.get().getLatestPrice());

    returnedQuote = quoteDao.save(quote2);

    assertEquals(returnedQuote, quote2);
  }

  @Test
  public void saveAll() {
    List<Quote> quotes = new LinkedList<>();

    quote1.setTicker("SYM2");
    quote1.setLastPrice(15.4d);
    quote1.setAskPrice(9.5d);
    quote1.setAskSize(7);
    quote1.setBidPrice(45.9d);
    quote1.setBidSize(8);

    Quote quote2 = new Quote();
    quote2.setTicker("SYM1");
    quote2.setLastPrice(15.4d);
    quote2.setAskPrice(9.5d);
    quote2.setAskSize(7);
    quote2.setBidPrice(45.9d);
    quote2.setBidSize(8);

    quotes.add(quote1);
    quotes.add(quote2);

    List<Quote> returnedQuotes = (List)quoteDao.saveAll(quotes);

    assertTrue(quotes.equals(returnedQuotes));

    quotes.clear();

    List<IexQuote> iexQuotes = (List)marketDataDao.findAllById(Arrays.asList("amd", "fb", "bio"));

    iexQuotes.forEach(iexQuote -> {
      Quote q = new Quote();
      q.setTicker(iexQuote.getSymbol());
      q.setAskPrice(iexQuote.getIexAskPrice());
      q.setAskSize((int)iexQuote.getIexAskSize());
      q.setBidPrice(iexQuote.getIexBidPrice());
      q.setBidSize((int)iexQuote.getIexBidSize());
      q.setLastPrice(iexQuote.getLatestPrice());
      quotes.add(q);
    });

    returnedQuotes = (List)quoteDao.saveAll(quotes);

    assertTrue(quotes.equals(returnedQuotes));

  }

  @Test
  public void findById() {
    Optional<Quote> returnedQuote = quoteDao.findById("GOOGL");

    assertTrue(returnedQuote.isPresent());
    assertEquals("GOOGL", returnedQuote.get().getTicker());

    returnedQuote = quoteDao.findById("SOMESYM");
  }

  @Test
  public void findAll() {
    List<Quote> returnedQuotes = (List)quoteDao.findAll();

    assertTrue(returnedQuotes.size() > 0);
    assertEquals("GOOGL", returnedQuotes.get(0).getTicker());
  }

  @Test
  public void existsById() {
    assertTrue(quoteDao.existsById("GOOGL"));
    assertFalse(quoteDao.existsById("SOMESYM"));
  }

  @Test
  public void deleteById() {
    quoteDao.deleteById("GOOGL");
    assertFalse(quoteDao.existsById("GOOGL"));

    quoteDao.deleteById("GOOGL");
  }

  @Test
  public void count() {
    assertTrue(quoteDao.count() > 0);
  }
}