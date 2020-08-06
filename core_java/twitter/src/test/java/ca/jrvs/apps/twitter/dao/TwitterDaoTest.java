package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoTest {

  TwitterDao dao;
  Tweet tweet;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

    dao = new TwitterDao(httpHelper);

    tweet = new Tweet("20:45", 1, "1", "Fith Tweet", null, null, 0, 0, false, false);
  }

  @Test
  public void create() throws JsonProcessingException {
    System.out.println(JsonUtil.toJson(tweet, true, true));

    tweet = dao.create(tweet);
    System.out.println(tweet.toString());
  }

  @Test
  public void findById() {
  }

  @Test
  public void deleteById() {
  }
}