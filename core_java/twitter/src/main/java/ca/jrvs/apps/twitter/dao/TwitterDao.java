package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.TwitterCLIApp;
import ca.jrvs.apps.twitter.dao.http_helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TwitterDao implements CrdDao<Tweet, String> {

  /**
   * URI Constants
   */
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy/";

  /**
   * URI Symbols
   */
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  /**
   * Response code
   */
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;
  private PercentEscaper percentEscaper;

  @Autowired
  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
    percentEscaper = new PercentEscaper("", false);
  }

  /**
   * Create a URI to post tweet
   *
   * @param tweet
   * @return
   */
  private URI getPostUri(Tweet tweet) {
    double lg = tweet.getCoordinates().getCoordinates().get(0);
    double lt = tweet.getCoordinates().getCoordinates().get(1);
    try {
      return new URI(
          API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL +
              percentEscaper.escape(tweet.getText()) + AMPERSAND + "long" + EQUAL + lg + AMPERSAND + "lat" + EQUAL + lt);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet input", e);
    }
  }

  /**
   * Create a URI to show tweet
   *
   * @param id
   * @return
   */
  private URI getShowUri(String id) {
    try {
      return new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + id);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet id input", e);
    }
  }

  /**
   * Create a URI to delete tweet
   *
   * @param id
   * @return
   */
  private URI getDeleteUri(String id) {
    try {
      return new URI(API_BASE_URI + DELETE_PATH + id + ".json");
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet id input", e);
    }
  }

  /**
   * Convert response entity to Tweet object
   *
   * @param response
   * @param expectedStatusCode
   * @return
   */
  public Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    Tweet tweet = null;

    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        TwitterCLIApp.LOGGER.error("Response has no entity", e);
      }
      throw new RuntimeException("Unexpected HTTP status: " + status);
    }

    if (response.getEntity() == null)
      throw new RuntimeException("Empty response body");

    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert entity to json string", e);
    }

    try {
      tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert JSON str to Object", e);
    }

    return tweet;
  }

  @Override
  public Tweet create(Tweet entity) {
    URI uri = getPostUri(entity);

    HttpResponse response = httpHelper.httpPost(uri);

    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet findById(String id) {
    URI uri = getShowUri(id);

    HttpResponse response = httpHelper.httpGet(uri);

    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet deleteById(String id) {
    URI uri = getDeleteUri(id);

    HttpResponse response = httpHelper.httpPost(uri);

    return parseResponseBody(response, HTTP_OK);
  }
}
