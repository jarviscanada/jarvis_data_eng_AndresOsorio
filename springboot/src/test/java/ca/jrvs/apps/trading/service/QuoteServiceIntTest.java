package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

public class QuoteServiceIntTest {

  QuoteService quoteService;
  QuoteDao quoteDao;
  MarketDataDao marketDataDao;

  @Before
  public void setUp() throws Exception {
    HttpClientConnectionManager httpClientConnectionManager = new BasicHttpClientConnectionManager();
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost("https://cloud.iexapis.com/stable");
    marketDataConfig.setToken(System.getenv("token"));
    marketDataDao = new MarketDataDao(httpClientConnectionManager, marketDataConfig);
    BasicDataSource basicDataSource = new BasicDataSource();
    quoteDao = new QuoteDao(basicDataSource);
    quoteService = new QuoteService(quoteDao, marketDataDao);
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
}