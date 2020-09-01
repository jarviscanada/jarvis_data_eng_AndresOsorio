package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.util.JsonUtil;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * MarketDataDao is responsible for getting Quotes from IEX
 */
@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

  private static final String IEX_BATCH_PATH_FIRST_PART = "/stock/market/batch?symbols=";
  private static final String IEX_BATCH_PATH_SECOND_PART = "&types=quote&token=";

  private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;
  private MarketDataConfig marketDataConfig;

  @Autowired
  public MarketDataDao() {
    HttpClientConnectionManager httpClientConnectionManager = new BasicHttpClientConnectionManager();
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost("https://cloud.iexapis.com/stable");
    marketDataConfig.setToken(System.getenv("token"));
    this.httpClientConnectionManager = httpClientConnectionManager;
    this.marketDataConfig = marketDataConfig;
  }

  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    this.marketDataConfig = marketDataConfig;
  }

  private String getBatchUrl(List<String> tickers) {
    StringBuilder url = new StringBuilder();

    url.append(marketDataConfig.getHost());
    url.append(IEX_BATCH_PATH_FIRST_PART);

    for (int i = 0; i < tickers.size(); i++) {
      url.append(tickers.get(i));
      if (i < tickers.size() - 1)
        url.append(",");
    }

    url.append(IEX_BATCH_PATH_SECOND_PART);
    url.append(marketDataConfig.getToken());

    return url.toString();
  }

  @Override
  public <S extends IexQuote> S save(S s) {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not Implemented");
  }

  /**
   * Get an IexQuote
   *
   * @param ticker
   * @return an IexQuote Optional object
   * @throws IllegalArgumentException if given ticker is invalid
   * @throws DataRetrievalFailureException if HTTP request failed
   */
  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote = Optional.empty();
    List<IexQuote> quotes = (List) findAllById(Collections.singletonList(ticker));

    if (quotes.size() == 0)
      return iexQuote;
    else if (quotes.size() == 1)
      iexQuote = Optional.of(quotes.get(0));
    else
      throw new DataRetrievalFailureException("Unexpected number of quotes");

    return iexQuote;
  }

  @Override
  public boolean existsById(String s) {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public Iterable<IexQuote> findAll() {
    throw new UnsupportedOperationException("Not Implemented");
  }

  /**
   * Get quotes from IEX
   *
   * @param tickers is a list of tickers
   * @return a list of IexQuote objects
   * @throws IllegalArgumentException if any ticker is invalid or tickers is empty
   * @throws DataRetrievalFailureException if HTTP request failed
   */
  @Override
  public Iterable<IexQuote> findAllById(Iterable<String> tickers) {
    List<String> tickersList = (List)tickers;
    JSONObject IexQuotesJson = null;
    List<IexQuote> quotes = new LinkedList<>();

    if (tickersList.size() == 0)
      throw new IllegalArgumentException("Ticker list is empty");

    String url = getBatchUrl(tickersList);
    Optional<String> response = Optional.empty();

    response = executeHttpGet(url);

    if (!response.isPresent())
      return quotes;

    IexQuotesJson = new JSONObject(response.get());

    if (IexQuotesJson.length() == 0)
      throw new IllegalArgumentException("Invalid ticker(s)");

    Iterator<String> keys = IexQuotesJson.keys();

    while (keys.hasNext()) {
      try {
        JSONObject jsonQuote = new JSONObject(IexQuotesJson.get(keys.next()).toString());
        IexQuote quote = JsonUtil.toObjectFromJson(jsonQuote.get("quote").toString(), IexQuote.class);
        quotes.add(quote);
      } catch (IOException e) {
        throw new RuntimeException("Unable to deserialize JSON to IexQuote object", e);
      }
    }

    return quotes;
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public void deleteById(String s) {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public void delete(IexQuote iexQuote) {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> iterable) {
    throw new UnsupportedOperationException("Not Implemented");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not Implemented");
  }

  /**
   * Borrow an HTTP client from the httpClientConnectionManager
   *
   * @return an HttpClient
   */
  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom()
        // prevent connectionManager shutdown when calling httpClient.close()
        .setConnectionManager(httpClientConnectionManager)
        .setConnectionManagerShared(true)
        .build();
  }

  /**
   * Execute a GET request and return http entity/body as a string
   *
   * @param url resource URL
   * @return HTTP response body or Optional.empty for 404 response
   * @throws DataRetrievalFailureException if HTTP request failed or status code is unexpected
   */
  private Optional<String> executeHttpGet(String url) {
    CloseableHttpClient httpClient = getHttpClient();
    HttpResponse response = null;
    Optional<String> responseString = Optional.empty();

    HttpGet request = new HttpGet(url);

    try {
      response = httpClient.execute(request);
    } catch (IOException e) {
      throw new DataRetrievalFailureException("Failed to execute request", e);
    }

    int status = response.getStatusLine().getStatusCode();

    if (status == 404)
      return responseString;

    if (status != 200)
      throw new DataRetrievalFailureException("Unexpected status code: " + status);

    try {
      responseString = Optional.of(EntityUtils.toString(response.getEntity()));
    } catch (IOException e) {
      throw new RuntimeException("HTTP response entity is empty or failed to convert entity to JSON string", e);
    }

    return responseString;
  }
}
