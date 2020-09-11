package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {

  private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);

  abstract public JdbcTemplate getJdbcTemplate();

  abstract public SimpleJdbcInsert getSimpleJdbcInsert();

  abstract public String getTableName();

  abstract public String getColumnName();

  abstract Class<T> getEntityClass();

  /**
   * Helper method to save quote
   * @param entity
   */
  private <S extends T> void addOne(S entity) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);
    Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
    entity.setId(newId.intValue());
  }

  /**
   * Helper method to update a quote
   *
   * @param entity
   * @return number of rows updated
   */
  abstract public int updateOne(T entity);

  /**
   * Save a quote object to database
   *
   * @param   entity to be saved
   * @return  quote that was saved
   */
  @Override
  public <S extends T> S save(S entity) {
    if (existsById(entity.getId())) {
      int updatedRowNum = updateOne(entity);
      if (updatedRowNum != 1)
        throw new DataRetrievalFailureException("Unable to update entity");
    } else {
      addOne(entity);
    }
    return entity;
  }

  @Override
  public Optional<T> findById(Integer id) {
    Optional entity = Optional.empty();
    String query = "SELECT * FROM " + getTableName() + " WHERE " + getColumnName() + " =?";

    try {
      entity = Optional.ofNullable(getJdbcTemplate().queryForObject(query, BeanPropertyRowMapper.newInstance(getEntityClass()), id));
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.error("Can't find trader with id: " + id, e);
    }
    return entity;
  }

  @Override
  public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
    List<S> savedEntities = new LinkedList<>();
    iterable.forEach(entity -> savedEntities.add(save(entity)));
    return (List)savedEntities;
  }

  @Override
  public boolean existsById(Integer id) {
    int rows = getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM " + getTableName() + " WHERE " + getColumnName() + " = ?", Integer.class, id);
    return rows > 0;
  }

  @Override
  public List<T> findAll() {
    String query = "SELECT * FROM " + getTableName();
    List<T> entities = getJdbcTemplate().query(query, BeanPropertyRowMapper.newInstance(getEntityClass()));
    if (entities.isEmpty())
      logger.error("No entries in database");
    return entities;
  }

  @Override
  public List<T> findAllById(Iterable<Integer> ids) {
    List<T> entities = new LinkedList<>();
    ids.forEach(id -> entities.add(findById(id).get()));
    return entities;
  }

  @Override
  public long count() {
    String query = "SELECT COUNT(*) FROM " + getTableName();
    return getJdbcTemplate().queryForObject(query, Integer.class);
  }

  @Override
  public void deleteById(Integer id) {
    String query = "DELETE FROM " + getTableName() + " WHERE " + getColumnName() + " = ?";
    if (existsById(id))
      getJdbcTemplate().update(query, id);
    else
      logger.error("Entry with id " + id + " does not exist");
  }

  @Override
  public void deleteAll() {
    String query = "DELETE FROM " + getTableName();
    getJdbcTemplate().update(query);
  }
}
