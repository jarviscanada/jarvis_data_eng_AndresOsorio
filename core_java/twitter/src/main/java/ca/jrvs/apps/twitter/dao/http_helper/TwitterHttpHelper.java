package ca.jrvs.apps.twitter.dao.http_helper;

import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Component;

@Component
public class TwitterHttpHelper implements HttpHelper {

  /**
   * Dependencies are specified as private member variables
   */
  private OAuthConsumer consumer;
  private HttpClient httpClient;

  /**
   * Default constructor to be used by @Autowired (Spring)
   */
  public TwitterHttpHelper() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    httpClient = new DefaultHttpClient();
  }


  /**
   * Constructor
   * Setup dependecies using secrets
   *
   * @param consumerKey
   * @param consumerSecret
   * @param accessToken
   * @param tokenSecret
   */
  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    httpClient = new DefaultHttpClient();
  }

  private HttpResponse executeRequest(HttpRequestBase request) throws OAuthException, IOException {
    consumer.sign(request);
    return httpClient.execute(request);
  }

  private HttpResponse httpRequest(String methodType, URI uri, StringEntity stringEntity) throws OAuthException, IOException {
    if (methodType.equals("POST")) {
      HttpPost request = new HttpPost(uri);
      if (stringEntity != null)
        request.setEntity(stringEntity);
      return executeRequest(request);
    } else if (methodType.equals("GET")) {
      HttpGet request = new HttpGet(uri);
      return executeRequest(request);
    } else {
      throw new IllegalArgumentException("Unknown HTTP method: " + methodType);
    }
  }

  @Override
  public HttpResponse httpPost(URI uri) {
    try {
      return httpRequest("POST", uri, null);
    } catch (OAuthException | IOException e) {
      throw new RuntimeException("Failed to execute HTTP request");
    }
  }

  @Override
  public HttpResponse httpGet(URI uri) {
    try {
      return httpRequest("GET", uri, null);
    } catch (OAuthException | IOException e) {
      throw new RuntimeException("Failed to execute HTTP request");
    }
  }
}
