package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;

  Tweet tweet, expectedTweet, returnedTweet;
  Coordinates coords;
  TwitterDao spyDao;

  final String tweetJsonStr = "{\n"
      + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
      + "   \"id\":1097607853932564480,\n"
      + "   \"id_str\":\"1097607853932564480\",\n"
      + "   \"text\":\"test with loc223\",\n"
      + "   \"entities\":{\n"
      + "       \"hashtags\":[],\n"
      + "       \"user_mentions\":[]\n"
      + "   },\n"
      + "   \"coordinates\":null,\n"
      + "   \"retweet_count\":0,\n"
      + "   \"favorite_count\":0,\n"
      + "   \"favorited\":false,\n"
      + "   \"retweeted\":false\n"
      + "}";

  @Before
  public void setUp() throws IOException {
    coords = new Coordinates(1, 2);
    tweet = new Tweet("", 0, "", "sometext", null, coords, 0, 0, false, false);
    spyDao = Mockito.spy(dao);
    // make a spyDao which can fake parseResponseBody return value using tweetJsonStr and JsonUtil.toObjectFromJson
    expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
  }

  @Test
  public void create() {
    // exception expected
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.create(tweet);
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);

    returnedTweet = spyDao.create(tweet);
    assertNotNull(returnedTweet);
    assertNotNull(returnedTweet.getText());
    assertEquals(expectedTweet.getText(), returnedTweet.getText());
  }

  @Test
  public void findById() {
    // exception expected
    when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.findById(tweet.getIdStr());
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    when(mockHelper.httpGet(isNotNull())).thenReturn(null);

    returnedTweet = spyDao.findById(tweet.getIdStr());
    assertNotNull(returnedTweet);
    assertNotNull(returnedTweet.getText());
    assertEquals(expectedTweet.getText(), returnedTweet.getText());
  }

  @Test
  public void deleteById() {
    // exception expected
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.deleteById(tweet.getIdStr());
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);

    returnedTweet = spyDao.deleteById(tweet.getIdStr());
    assertNotNull(returnedTweet);
    assertNotNull(returnedTweet.getText());
    assertEquals(expectedTweet.getText(), returnedTweet.getText());
  }
}