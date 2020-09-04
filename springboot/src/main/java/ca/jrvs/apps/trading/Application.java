package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.controller.QuoteController;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
    (exclude =
    {JdbcTemplateAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class},
    scanBasePackages = "ca.jrvs.apps.trading" )
public class Application implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(Application.class);

//  private QuoteController quoteController;

//  @Value("${app.init.dailyList}")
//  private String[] intDailyList;

//  @Autowired
//  public Application(QuoteController quoteController) {
//    this.quoteController = quoteController;
//  }

  @Override
  public void run(String... args) throws Exception {
//    IexQuote quote = quoteController.getQuote("aapl");
//    System.out.println(quote.toString());
  }

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.run(args);
  }
}
