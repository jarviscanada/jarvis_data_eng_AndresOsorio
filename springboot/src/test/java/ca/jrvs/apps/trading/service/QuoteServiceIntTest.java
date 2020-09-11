package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Arrays;
import java.util.LinkedList;
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
public class QuoteServiceIntTest {

  @Autowired
  QuoteDao quoteDao;
  @Autowired
  MarketDataDao marketDataDao;
  @Autowired
  QuoteService quoteService;

  Quote quote1, quote2, quote3;
  List<Quote> quotes;

  @Before
  public void setUp() {
    quote1 = new Quote();
    quote1.setTicker("GOOGL");
    quote1.setAskPrice(0.0);
    quote1.setAskSize(0);
    quote1.setBidPrice(0.0);
    quote1.setBidSize(0);
    quote1.setLastPrice(0.0);

    quote2 = new Quote();
    quote2.setTicker("AAPL");
    quote2.setAskPrice(0.0);
    quote2.setAskSize(0);
    quote2.setBidPrice(0.0);
    quote2.setBidSize(0);
    quote2.setLastPrice(0.0);

    quote3 = new Quote();
    quote3.setTicker("FB");
    quote3.setAskPrice(0.0);
    quote3.setAskSize(0);
    quote3.setBidPrice(0.0);
    quote3.setBidSize(0);
    quote3.setLastPrice(0.0);

    quotes = new LinkedList<>();
    quotes.add(quote1);
    quotes.add(quote2);
    quotes.add(quote3);

    quoteDao.saveAll(quotes);
  }

  @After
  public void tearDown() {
    quoteDao.deleteAll();
  }

  @Test
  public void findIexQuoteByTicker() {
    IexQuote response = quoteService.findIexQuoteByTicker("googl");

    System.out.println("\n" + response.toString() + "\n");

    assertEquals("GOOGL", response.getSymbol());

    response = quoteService.findIexQuoteByTicker("aapl");

    System.out.println("\n" + response.toString() + "\n");

    assertEquals("AAPL", response.getSymbol());

    try {
      response = quoteService.findIexQuoteByTicker("somesymbol");
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void updateMarketData() {
    quoteService.updateMarketData();

    assertTrue(quoteDao.findById("GOOGL").get().getLastPrice() > 0);
  }

  @Test
  public void saveQuotesByTickers() {
    List<Quote> savedQuotes = quoteService.saveQuotesByTickers(Arrays.asList("AMD", "DG", "HD"));

    assertTrue(savedQuotes.size() > 0);
    assertEquals("AMD", savedQuotes.get(0).getTicker());
    assertEquals("DG", savedQuotes.get(1).getTicker());
    assertEquals("HD", savedQuotes.get(2).getTicker());
  }

  @Test
  public void findAllQuotes() {
    List<Quote> savedQuotes = quoteService.findAllQuotes();

    assertTrue(savedQuotes.size() > 0);
    assertEquals("GOOGL", savedQuotes.get(0).getTicker());
    assertEquals("AAPL", savedQuotes.get(1).getTicker());
  }

}