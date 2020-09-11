package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.SecurityOrder.Status;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceUnitTest {

  @Captor
  ArgumentCaptor<SecurityOrder> captorSecurityOrder;

  @Mock
  private AccountDao accountDao;
  @Mock
  private SecurityOrderDao securityOrderDao;
  @Mock
  private QuoteDao quoteDao;
  @Mock
  private PositionDao positionDao;

  @InjectMocks
  private OrderService orderService;

  private SecurityOrder securityOrder = new SecurityOrder();
  private Quote quote = new Quote();
  private Account account = new Account();
  private MarketOrderDto marketOrderDto = new MarketOrderDto();

  @Before
  public void setUp() throws Exception {
    account.setAmount(100.0);
    account.setId(1);

    marketOrderDto.setId(1);
    marketOrderDto.setTicker("GOOGL");
    marketOrderDto.setSize(5);

    quote.setTicker("GOOGL");
    quote.setAskPrice(10.2);
    quote.setBidPrice(11.0);

    securityOrder.setAccountId(1);
    securityOrder.setTicker("GOOGL");
    securityOrder.setSize(marketOrderDto.getSize());
    securityOrder.setNotes("");
    securityOrder.setStatus(Status.PENDING.toString());
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void executeMarketOrder() {
    marketOrderDto.setAction("buy");
    securityOrder.setPrice(quote.getBidPrice());

    doReturn(Optional.of(quote)).when(quoteDao).findById(anyString());
    doReturn(Optional.of(account)).when(accountDao).findById(anyInt());

    SecurityOrder returnedSecurityOrder = orderService.executeMarketOrder(marketOrderDto);

    assertEquals(securityOrder.getTicker(), returnedSecurityOrder.getTicker());

    verify(securityOrderDao).save(captorSecurityOrder.capture());

    assertTrue(securityOrder.equals(captorSecurityOrder.getValue()));

    marketOrderDto.setAction("sell");
    securityOrder.setPrice(quote.getAskPrice());

    returnedSecurityOrder = orderService.executeMarketOrder(marketOrderDto);

    assertEquals(securityOrder.getTicker(), returnedSecurityOrder.getTicker());

    verify(securityOrderDao).save(securityOrder);
  }
}