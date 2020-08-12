package ca.jrvs.apps.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ca.jrvs.apps.twitter")
public class TwitterCLISpringBoot implements CommandLineRunner {

  private TwitterCLIApp app;

  @Autowired
  public TwitterCLISpringBoot(TwitterCLIApp app) {
    this.app = app;
  }

  @Override
  public void run(String... args) throws Exception {
    app.run(args);
  }

  public static void main(String[] args) {
    SpringApplication springApp = new SpringApplication(TwitterCLISpringBoot.class);

    springApp.setWebApplicationType(WebApplicationType.NONE);

    springApp.run(args);
  }

}
