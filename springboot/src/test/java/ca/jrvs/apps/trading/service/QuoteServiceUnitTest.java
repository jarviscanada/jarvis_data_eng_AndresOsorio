package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuoteServiceUnitTest {

  @Mock
  MarketDataDao marketDataDao;
  QuoteDao quoteDao;

  @InjectMocks
  QuoteService quoteService;

  Optional<IexQuote> quote;

  @Before
  public void setUp() {
    quote = Optional.of(new IexQuote());
    quote.get().setSymbol("GOOGL");
  }

  @Test
  public void findIexQuoteByTicker() {
    doReturn(quote).when(marketDataDao).findById(anyString());

    IexQuote response = quoteService.findIexQuoteByTicker("googl");

    verify(marketDataDao).findById("googl");
    assertEquals(quote.get(), response);
    assertEquals(quote.get().getSymbol(), response.getSymbol());

    doThrow(new IllegalArgumentException()).when(marketDataDao).findById("somesymbol");
    try {
      quoteService.findIexQuoteByTicker("somesymbol");
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}