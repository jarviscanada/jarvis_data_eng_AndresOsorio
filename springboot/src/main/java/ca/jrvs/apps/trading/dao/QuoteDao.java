package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }

  /**
   * Helper method to save quote
   * @param quote
   */
  private void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);

    if (row != 1)
      throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
  }

  /**
   * Helper method to update a quote
   *
   * @param quote
   * @return number of rows updated
   */
  private int updateOne(Quote quote) {
    String update_sql =
        "UPDATE " + TABLE_NAME + " " +
        "SET last_price=?, bid_price=?, bid_size=?, ask_price=?, ask_size=? " +
        "WHERE " + ID_COLUMN_NAME + "=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
  }

  /**
   * Helper method that turn sql update values into objects
   *
   * @param q; a quote object to be updated in the database
   * @return values of q as objects
   */
  private Object[] makeUpdateValues(Quote q) {
    return new Object[]{q.getLastPrice(), q.getBidPrice(), q.getBidSize(), q.getAskPrice(), q.getAskSize(), q.getTicker()};
  }

  /**
   * Save a quote object to database
   *
   * @param   quote to be saved
   * @return  quote that was saved
   */
  @Override
  public Quote save(Quote quote) {
    if (existsById(quote.getTicker())) {
      int updatedRowNum = updateOne(quote);
      if (updatedRowNum != 1)
        throw new DataRetrievalFailureException("Unable to update quote");
    } else {
      addOne(quote);
    }
    return quote;
  }

  @Override
  public <S extends Quote> Iterable<S> saveAll(Iterable<S> iterable) {
    List<Quote> savedQuotes = new LinkedList<>();
    iterable.forEach(quote -> savedQuotes.add(save(quote)));
    return (List)savedQuotes;
  }

  @Override
  public Optional<Quote> findById(String ticker) {
    String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " = ? LIMIT 1";
    Quote quote = new Quote();
    try {
      quote = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Quote.class), ticker);
    } catch (EmptyResultDataAccessException e) {
      logger.error("No entry with ticker " + ticker, e);
    }
    return Optional.ofNullable(quote);
  }

  @Override
  public boolean existsById(String ticker) {
    int rows = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " = ?", Integer.class, ticker);
    return rows > 0;
  }

  @Override
  public Iterable<Quote> findAll() {
    String query = "SELECT * FROM " + TABLE_NAME;
    List<Quote> quotes = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Quote.class));
    if (quotes.isEmpty())
      logger.error("No entries in database");
    return quotes;
  }

  @Override
  public Iterable<Quote> findAllById(Iterable<String> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public long count() {
    String query = "SELECT COUNT(*) FROM " + TABLE_NAME;
    return jdbcTemplate.queryForObject(query, Integer.class);
  }

  @Override
  public void deleteById(String ticker) {
    String query = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " = ?";
    if (existsById(ticker))
      jdbcTemplate.update(query, ticker);
    else
      logger.error("Entry with ticker " + ticker + " does not exist");
  }

  @Override
  public void delete(Quote quote) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Quote> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll() {
    String query = "DELETE FROM " + TABLE_NAME;
    jdbcTemplate.update(query);
  }
}
