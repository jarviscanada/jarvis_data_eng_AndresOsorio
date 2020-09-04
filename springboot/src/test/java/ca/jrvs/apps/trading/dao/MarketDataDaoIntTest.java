package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
public class MarketDataDaoIntTest {

  @Autowired
  MarketDataDao marketDataDao;

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