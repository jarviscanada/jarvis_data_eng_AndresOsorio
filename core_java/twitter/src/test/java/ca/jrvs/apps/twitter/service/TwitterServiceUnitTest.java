package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao<Tweet, String> mockDao;

  @InjectMocks
  TwitterService service;

  Tweet tweet, returnedTweet;

  @Before
  public void setUp() {
    tweet = new Tweet("", 1, "1", "create tweet test #random", null, (new Coordinates(1, 2)), 0, 0,
        false, false);
  }

  @Test
  public void postTweet() {
    doReturn(tweet).when(mockDao).create(any());
    returnedTweet = service.postTweet(tweet);

    assertEquals(tweet.getId(), returnedTweet.getId());
    assertEquals(tweet.getText(), returnedTweet.getText());
    assertTrue(tweet == returnedTweet);
  }

  @Test
  public void showTweet() {
    doReturn(tweet).when(mockDao).findById(anyString());
    returnedTweet = service.showTweet(tweet.getIdStr(), new String[0]);

    assertEquals(tweet.getId(), returnedTweet.getId());
    assertEquals(tweet.getText(), returnedTweet.getText());
    assertTrue(tweet == returnedTweet);
  }

  @Test
  public void deleteTweets() {
    doReturn(tweet).when(mockDao).deleteById(anyString());
    List<Tweet> deletedTweets = service.deleteTweets(new String[]{tweet.getIdStr()});

    assertEquals(tweet.getId(), deletedTweets.get(0).getId());
    assertEquals(tweet.getText(), deletedTweets.get(0).getText());
    assertTrue(tweet == deletedTweets.get(0));
  }
}