package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.service.QuoteService;
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
  private QuoteService quoteService;

  private Quote savedQuote;

  @Before
  public void setUp() throws Exception {
    savedQuote = new Quote();
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setTicker("GOOGL");
    savedQuote.setLastPrice(10.1d);
    quoteDao.save(savedQuote);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void save() {
    savedQuote.setLastPrice(15.4d);
    savedQuote.setAskPrice(17.8d);
    savedQuote.setAskSize(3);
    savedQuote.setBidPrice(19.0d);
    savedQuote.setBidSize(4);

    Quote returnedQuote = quoteDao.save(savedQuote);

    assertEquals(returnedQuote, savedQuote);

    IexQuote iexQuote = quoteService.findIexQuoteByTicker("aapl");

    Quote savedQuote2 = new Quote();

    savedQuote2.setAskPrice(iexQuote.getIexAskPrice());
    savedQuote2.setAskSize((int)iexQuote.getIexAskSize());
    savedQuote2.setBidPrice(iexQuote.getIexBidPrice());
    savedQuote2.setBidSize((int)iexQuote.getIexBidSize());
    savedQuote2.setTicker(iexQuote.getSymbol());
    savedQuote2.setLastPrice(iexQuote.getLatestPrice());

    returnedQuote = quoteDao.save(savedQuote2);

    assertEquals(returnedQuote, savedQuote2);
  }
}