package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ca.jrvs.apps.trading.dao", "ca.jrvs.apps.trading.service"})
public class TestConfig {

  @Bean
  public MarketDataConfig marketDataConfig() {
    System.out.println("Creating market data config");

    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost("https://cloud.iexapis.com/stable");
    marketDataConfig.setToken(System.getenv("token"));

    System.out.println(marketDataConfig.getHost() + " " + marketDataConfig.getToken());
    return marketDataConfig;
  }

  @Bean
  public HttpClientConnectionManager httpClientConnectionManager() {
    System.out.println("Creating http client");

    return new BasicHttpClientConnectionManager();
  }

  @Bean
  public DataSource dataSource() {
    System.out.println("Creating data source");

    String url = System.getenv("PSQL_URL");
    String user = System.getenv("PSQL_USER");
    String password = System.getenv("PSQL_PASSWORD");

    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(url);
    basicDataSource.setUsername(user);
    basicDataSource.setPassword(password);

    System.out.println(basicDataSource.getUrl() + " " + basicDataSource.getUsername() + " " + basicDataSource.getPassword());

    return basicDataSource;
  }

}
