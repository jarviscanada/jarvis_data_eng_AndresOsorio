package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class QuoteService {

  public static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

  private QuoteDao quoteDao;
  private MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }

  /**
   * Update quote table with IEX sources.
   * - get all quotes from the db
   * - for each ticker get iexQuote
   * - convert iexQuote to Quote
   * - persist Quote to db
   *
   * @throws org.springframework.dao.DataRetrievalFailureException if ticker is not found from IEX
   * @throws org.springframework.dao.DataAccessException if unable to retrieve data
   * @throws IllegalArgumentException for invalid input
   */
  public void updateMarketData() {
    List<Quote> quotes = (List)quoteDao.findAll();
    List<String> tickers = new LinkedList<>();
    quotes.forEach(quote -> tickers.add(quote.getTicker()));
    List<IexQuote> iexQuotes = (List)marketDataDao.findAllById(tickers);

    quotes.clear();

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

    quoteDao.saveAll(quotes);
  }

  /**
   * Helper method to build a Quote from an IexQuote
   *
   * @param iexQuote quote from IEX
   * @return Quote buildt from IexQuote
   */
  public Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
    Quote quote = new Quote();
    quote.setTicker(iexQuote.getSymbol());
    quote.setAskPrice(iexQuote.getIexAskPrice());
    quote.setAskSize((int)iexQuote.getIexAskSize());
    quote.setBidPrice(iexQuote.getIexBidPrice());
    quote.setBidSize((int)iexQuote.getIexBidSize());
    quote.setLastPrice(iexQuote.getLatestPrice());

    return quote;
  }

  /**
   * Helper method to save quote by ticker
   */
  public Quote saveQuoteByTicker(String ticker) {
    Optional<IexQuote> iexQuote = marketDataDao.findById(ticker);
    Quote quote = buildQuoteFromIexQuote(iexQuote.get());
    return quoteDao.save(quote);
  }

  /**
   * Find quotes by ticker in IEX and save to db
   * - get IexQuotes
   * - convert each IexQuote to Quote
   * - persist quotes to db
   *
   * @param tickers list of tickers
   * @throws IllegalArgumentException if ticker is not found from IEX
   */
  public List<Quote> saveQuotesByTickers(List<String> tickers) {
    List<Quote> quotes = new LinkedList<>();
    tickers.forEach(ticker -> quotes.add(saveQuoteByTicker(ticker)));
    return quotes;
  }

  /**
   * Put/update quote without without validation from IEX
   *
   * @param quote to be saved
   * @return quote saved
   */
  public Quote saveQuote(Quote quote) {
    return quoteDao.save(quote);
  }

  /**
   * Put/update quotes without validation from IEX
   *
   * @param quotes to be saved
   * @return quotes saved
   */
  public List<Quote> saveQuotes(List<Quote> quotes) {
    return (List)quoteDao.saveAll(quotes);
  }

  /**
   * Find all quotes from the table
   *
   * @return all quotes from the table quotes
   */
  public List<Quote> findAllQuotes() {
    return (List)quoteDao.findAll();
  }

  /**
   * Find an IexQuote from IEX
   *
   * @param ticker
   * @return IexQuote
   */
  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
  }
}
