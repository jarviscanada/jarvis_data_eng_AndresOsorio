package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ca.jrvs.apps.trading.dao", "ca.jrvs.apps.trading.service"})
public class TestConfig {

  private Logger logger = LoggerFactory.getLogger(TestConfig.class);

  @Bean
  public MarketDataConfig marketDataConfig() {
    logger.info("Creating market data config");

    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost("https://cloud.iexapis.com/stable");
    marketDataConfig.setToken(System.getenv("token"));

    logger.info(marketDataConfig.getHost() + " " + marketDataConfig.getToken());
    return marketDataConfig;
  }

  @Bean
  public HttpClientConnectionManager httpClientConnectionManager() {
    logger.info("Creating http client");

    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(50);
    cm.setDefaultMaxPerRoute(50);

    return cm;
  }

  @Bean
  public DataSource dataSource() {
    logger.info("Creating data source");

    String url = System.getenv("PSQL_URL");
    String user = System.getenv("PSQL_USER");
    String password = System.getenv("PSQL_PASSWORD");

    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(url);
    basicDataSource.setUsername(user);
    basicDataSource.setPassword(password);

    logger.info(basicDataSource.getUrl() + " " + basicDataSource.getUsername() + " " + basicDataSource.getPassword());

    return basicDataSource;
  }

}
