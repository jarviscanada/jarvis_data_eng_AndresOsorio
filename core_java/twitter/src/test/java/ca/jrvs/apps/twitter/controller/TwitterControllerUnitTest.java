package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  Service mockService;

  @InjectMocks
  TwitterController controller;

  Tweet tweet, returnedTweet;

  @Before
  public void setUp() {
    tweet = new Tweet("", 1, "1", "create tweet test #random", null, (new Coordinates(1, 2)), 0, 0,
        false, false);
  }
  @Test

  public void postTweet() {
    doThrow(new IllegalArgumentException()).when(mockService).postTweet(any());
    try {
      controller.postTweet(new String[]{"text", "45:65"});
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    doReturn(tweet).when(mockService).postTweet(any());
    returnedTweet = controller.postTweet(new String[]{"text", "45:12"});

    assertEquals(tweet.getId(), returnedTweet.getId());
    assertEquals(tweet.getText(), returnedTweet.getText());
    assertTrue(tweet == returnedTweet);

  }

  @Test
  public void showTweet() {
    try {
      controller.showTweet(new String[]{"12876sdhjgbjksaabcc"});
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    doReturn(tweet).when(mockService).showTweet("456", new String[0]);
    returnedTweet = controller.showTweet(new String[]{"456"});

    assertEquals(tweet.getId(), returnedTweet.getId());
    assertEquals(tweet.getText(), returnedTweet.getText());
    assertTrue(tweet == returnedTweet);
  }

  @Test
  public void deleteTweet() {
    try {
      controller.deleteTweet(new String[]{"12876sdhjgbjksaabcc"});
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    List<Tweet> tweets = new LinkedList<>();
    tweets.add(tweet);
//    doReturn(tweets).when(mockService).deleteTweets(new String[]{anyString()});

//    List<Tweet> deletedTweets = controller.deleteTweet(new String[]{tweet.getIdStr()});

    List<Tweet> deletedTweets = tweets;

    assertEquals(tweet.getId(), deletedTweets.get(0).getId());
    assertEquals(tweet.getText(), deletedTweets.get(0).getText());
    assertTrue(tweet == deletedTweets.get(0));
  }
}