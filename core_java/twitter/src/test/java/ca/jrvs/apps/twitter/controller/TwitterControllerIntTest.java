package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.http_helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.http_helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {

  Controller controller;
  Tweet createdTweet, foundTweet;

  final String CONSUMER_KEY = System.getenv("consumerKey");
  final String CONSUMER_SECRET = System.getenv("consumerSecret");
  final String ACCESS_TOKEN = System.getenv("accessToken");
  final String TOKEN_SECRET = System.getenv("tokenSecret");

  @Before
  public void setUp() throws Exception {
    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    CrdDao<Tweet, String> dao = new TwitterDao(httpHelper);
    Service service = new TwitterService(dao);
    controller = new TwitterController(service);
  }

  @Test
  public void postTweet() {
    try {
      controller.postTweet(new String[]{"text"});
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    createdTweet = controller.postTweet(new String[]{"#controller created", "45:60"});

    assertEquals(createdTweet.getText(), "#controller created");
    assertEquals(2, createdTweet.getCoordinates().getCoordinates().size());
    assertTrue(createdTweet.getCoordinates().getCoordinates().get(0) == 45);
    assertTrue(createdTweet.getCoordinates().getCoordinates().get(1) == 60);

    controller.deleteTweet(new String[]{createdTweet.getIdStr()});

    System.out.println("\n" + createdTweet + "\n");
  }

  @Test
  public void showTweet() {
    try {
      controller.showTweet(new String[]{""});
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    createdTweet = controller.postTweet(new String[]{"#controller created", "45:60"});

    foundTweet = controller.showTweet(new String[]{createdTweet.getIdStr()});

    assertEquals(foundTweet.getText(), "#controller created");
    assertEquals(2, foundTweet.getCoordinates().getCoordinates().size());
    assertTrue(foundTweet.getCoordinates().getCoordinates().get(0) == 45);
    assertTrue(foundTweet.getCoordinates().getCoordinates().get(1) == 60);

    controller.deleteTweet(new String[]{foundTweet.getIdStr()});

    System.out.println("\n" + foundTweet + "\n");
  }

  @Test
  public void deleteTweet() {
    try {
      controller.deleteTweet(new String[]{""});
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    createdTweet = controller.postTweet(new String[]{"#controller created", "45:60"});
    List<Tweet> deletedTweets = controller.deleteTweet(new String[]{createdTweet.getIdStr()});

    assertEquals(createdTweet.getText(), deletedTweets.get(0).getText());
    assertEquals(createdTweet.getCoordinates().getCoordinates().get(0), deletedTweets.get(0).getCoordinates().getCoordinates().get(0));
    assertEquals(createdTweet.getCoordinates().getCoordinates().get(1), deletedTweets.get(0).getCoordinates().getCoordinates().get(1));

    deletedTweets.stream().forEach(deletedTweet -> System.out.println("\n" + deletedTweet.toString() + "\n"));
  }
}