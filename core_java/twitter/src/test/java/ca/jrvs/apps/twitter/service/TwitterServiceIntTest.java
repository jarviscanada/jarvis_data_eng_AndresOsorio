package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

  Service service;
  Tweet tweet, createdTweet, foundTweet;

  final String CONSUMER_KEY = System.getenv("consumerKey");
  final String CONSUMER_SECRET = System.getenv("consumerSecret");
  final String ACCESS_TOKEN = System.getenv("accessToken");
  final String TOKEN_SECRET = System.getenv("tokenSecret");

  @Before
  public void setUp() throws Exception {
    tweet = new Tweet("", 1, "1", "create tweet test #random", null, (new Coordinates(1, 2)), 0, 0,
        false, false);
    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    CrdDao<Tweet, String> dao = new TwitterDao(httpHelper);
    service = new TwitterService(dao);
  }

  @Test
  public void postTweet() throws JsonProcessingException {
    System.out.println(JsonUtil.toJson(tweet, true, true));

    createdTweet = service.postTweet(tweet);

    assertEquals(tweet.getText(), createdTweet.getText());
    assertEquals(2, createdTweet.getCoordinates().getCoordinates().size());
    assertEquals(tweet.getCoordinates().getCoordinates().get(0), createdTweet.getCoordinates().getCoordinates().get(0));
    assertEquals(tweet.getCoordinates().getCoordinates().get(1), createdTweet.getCoordinates().getCoordinates().get(1));

    service.deleteTweets(new String[]{createdTweet.getIdStr()});

    System.out.println("\n" + createdTweet.toString() + "\n");
  }

  @Test
  public void showTweet() {
    createdTweet = service.postTweet(tweet);

    foundTweet = service.showTweet(createdTweet.getIdStr(), new String[0]);

    assertEquals(tweet.getText(), foundTweet.getText());
    assertEquals(tweet.getCoordinates().getCoordinates().get(0), foundTweet.getCoordinates().getCoordinates().get(0));
    assertEquals(tweet.getCoordinates().getCoordinates().get(1), foundTweet.getCoordinates().getCoordinates().get(1));

    service.deleteTweets(new String[]{createdTweet.getIdStr()});

    System.out.println("\n" + foundTweet.toString() + "\n");
  }

  @Test
  public void deleteTweets() {
    createdTweet = service.postTweet(tweet);

    List<Tweet> deletedTweets = service.deleteTweets(new String[]{createdTweet.getIdStr()});

    assertEquals(tweet.getText(), deletedTweets.get(0).getText());
    assertEquals(tweet.getCoordinates().getCoordinates().get(0), deletedTweets.get(0).getCoordinates().getCoordinates().get(0));
    assertEquals(tweet.getCoordinates().getCoordinates().get(1), deletedTweets.get(0).getCoordinates().getCoordinates().get(1));

    deletedTweets.stream().forEach(deletedTweet -> System.out.println("\n" + deletedTweet.toString() + "\n"));
  }
}