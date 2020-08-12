package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIApp {

  public static final Logger LOGGER = LoggerFactory.getLogger(TwitterCLIApp.class);

  private static final String USAGE = "USAGE: TwitterCLIApp post|show|delete [options]";

  private static Controller controller;

  @Autowired
  public TwitterCLIApp(Controller controller) {
    this.controller = controller;
  }

  private void printTweet(Tweet tweet) {
    try {
      System.out.println(JsonUtil.toJson(tweet, true, true));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to convert object to JSON string", e);
    }
  }

  public void run(String[] args) {
    if (args.length < 2)
      throw new IllegalArgumentException(USAGE);

    String[] actionArgs = new String[args.length - 1];
    System.arraycopy(args, 1, actionArgs, 0, args.length - 1);

    switch (args[0]) {
      case "post":
        printTweet(controller.postTweet(actionArgs));
        break;
      case "show":
        printTweet(controller.showTweet(actionArgs));
        break;
      case "delete":
        controller.deleteTweet(actionArgs).forEach(tweet -> printTweet(tweet));
        break;
      default:
        throw new IllegalArgumentException(USAGE);
    }
  }
}
