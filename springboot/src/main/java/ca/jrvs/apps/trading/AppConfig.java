package ca.jrvs.apps.trading;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public DataSource dataSource() {
    String url = System.getenv("PSQL_URL");
    String user = System.getenv("PSQL_USER");
    String password = System.getenv("PSQL_PASSWORD");

    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(url);
    basicDataSource.setUsername(user);
    basicDataSource.setPassword(password);
    return basicDataSource;
  }

}
