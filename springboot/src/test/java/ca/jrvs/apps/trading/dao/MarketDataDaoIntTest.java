package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

public class MarketDataDaoIntTest {

  MarketDataDao marketDataDao;

  @Before
  public void setUp() throws Exception {
    HttpClientConnectionManager httpClientConnectionManager = new BasicHttpClientConnectionManager();
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost("https://cloud.iexapis.com/stable");
    marketDataConfig.setToken(System.getenv("token"));
    marketDataDao = new MarketDataDao(httpClientConnectionManager, marketDataConfig);
  }

  @Test
  public void findById() {
    Optional<IexQuote> response = marketDataDao.findById("googl");

    System.out.println("\n" + response.toString() + "\n");

    assertEquals("GOOGL", response.get().getSymbol());

    response = marketDataDao.findById("somesymbol");
    assertFalse(response.isPresent());
  }

  @Test
  public void findAllById() {
    Iterable<String> tickers = Arrays.asList("aapl", "googl");
    List<IexQuote> response = (List)marketDataDao.findAllById(tickers);

    System.out.println("\n" + response.toString() + "\n");

    assertEquals(2, response.size());
    assertEquals("GOOGL", response.get(0).getSymbol());
    assertEquals("AAPL", response.get(1).getSymbol());

    tickers = new LinkedList<>();
    try {
      marketDataDao.findAllById(tickers);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}