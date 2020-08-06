package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class TwitterDao implements CrdDao<Tweet, String> {

  /**
   * URI Constants
   */
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";

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

  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
    percentEscaper = new PercentEscaper("", false);
  }

  /**
   * Create a POST URI
   *
   * @param tweetText
   * @return
   */
  private URI getPostUri(String tweetText) {
    try {
      return new URI(
          API_BASE_URI + POST_PATH + QUERY_SYM + "status=" + percentEscaper.escape(tweetText));
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet text input", e);
    }
  }

  /**
   * Convert response entity to Tweet object
   *
   * @param response
   * @param expectedStatusCode
   * @return
   */
  private Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    Tweet tweet = null;

    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        System.out.println("Response has no entity");
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
      throw new RuntimeException("Unable to convert JSON str to Object", e);
    }

    return tweet;
  }

  @Override
  public Tweet create(Tweet entity) {
    URI uri = null;
    uri = getPostUri(entity.getText());

    HttpResponse response = httpHelper.httpPost(uri);

    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet findById(String s) {
    return null;
  }

  @Override
  public Tweet deleteById(String s) {
    return null;
  }
}
