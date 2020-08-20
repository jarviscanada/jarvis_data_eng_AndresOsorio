package ca.jrvs.apps.twitter.dao.http_helper;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class TwitterHttpHelperTest {

  HttpHelper httpHelper;

  @Before
  public void setUp() throws Exception {
    final String CONSUMER_KEY = System.getenv("consumerKey");
    final String CONSUMER_SECRET = System.getenv("consumerSecret");
    final String ACCESS_TOKEN = System.getenv("accessToken");
    final String TOKEN_SECRET = System.getenv("tokenSecret");

    httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
  }

  @Test
  public void httpPost() throws URISyntaxException, IOException {
    HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=Third%20Tweet"));
    assertTrue(response.getEntity() != null);
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void httpGet() throws URISyntaxException, IOException {
    HttpResponse response = httpHelper.httpGet(new URI("https://api.twitter.com/1.1/statuses/home_timeline.json"));
    assertTrue(response.getEntity() != null);
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
}