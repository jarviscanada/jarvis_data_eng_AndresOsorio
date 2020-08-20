package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.http_helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.http_helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  TwitterDao dao;
  Tweet tweet, createdTweet, foundTweet;
  Coordinates coords;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    dao = new TwitterDao(httpHelper);
    coords = new Coordinates(1, 2);
    tweet = new Tweet("", 1, "1", "create tweet test #random", null, coords, 0, 0, false, false);
  }

  @Test
  public void create() throws JsonProcessingException {
    System.out.println(JsonUtil.toJson(tweet, true, true));

    createdTweet = dao.create(tweet);

    assertEquals(tweet.getText(), createdTweet.getText());
    assertEquals(2, createdTweet.getCoordinates().getCoordinates().size());
    assertEquals(tweet.getCoordinates().getCoordinates().get(0), createdTweet.getCoordinates().getCoordinates().get(0));
    assertEquals(tweet.getCoordinates().getCoordinates().get(1), createdTweet.getCoordinates().getCoordinates().get(1));

    dao.deleteById(createdTweet.getIdStr());

    System.out.println("\n" + createdTweet.toString() + "\n");
  }

  @Test
  public void findById() {
    createdTweet = dao.create(tweet);

    foundTweet = dao.findById(createdTweet.getIdStr());

    assertEquals(tweet.getText(), foundTweet.getText());

    dao.deleteById(createdTweet.getIdStr());

    System.out.println("\n" + foundTweet.toString() + "\n");
  }

  @Test
  public void deleteById() {
    createdTweet = dao.create(tweet);

    foundTweet = dao.deleteById(createdTweet.getIdStr());

    assertEquals(tweet.getText(), foundTweet.getText());

    System.out.println("\n" + foundTweet.toString() + "\n");
  }
}