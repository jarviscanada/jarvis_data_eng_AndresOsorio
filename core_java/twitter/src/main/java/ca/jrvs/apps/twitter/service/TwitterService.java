package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private CrdDao<Tweet, String> dao;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  private boolean validateText(String text) {
    return (text.length() < 140);
  }

  private boolean validateCoords(Coordinates coords) {
    float lg = coords.getCoordinates().get(0);
    float lt = coords.getCoordinates().get(1);
    float max = Float.MAX_VALUE;
    float min = Float.MIN_VALUE;
    return (lg >= min && lg <= max) && (lt >= min && lt <= max);
  }

  private boolean validateId(String id) {
    boolean allDigits = true;
    int i = 0;
    while (i < id.length() && allDigits) {
      if (!Character.isDigit(id.charAt(i++)))
        allDigits = false;
    }
    // Long.MAX_VALUE == 19 chars
    return (allDigits && (id.length() > 0 && id.length() < 20));
  }

  private void validateCreateTweet(Tweet tweet) {
    if (!validateText(tweet.getText()))
      throw new IllegalArgumentException("Tweet text exceeds 140 characters");

    if (!validateCoords(tweet.getCoordinates()))
      throw new IllegalArgumentException("Tweet coordinates are out of range");
  }

  private void validateFindTweet(String id) {
    if (!validateId(id))
      throw new IllegalArgumentException("Tweet id is invalid");
  }

  @Override
  public Tweet postTweet(Tweet tweet) {
    validateCreateTweet(tweet);
    return dao.create(tweet);
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateFindTweet(id);
    return dao.findById(id);
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> deletedTweets = new LinkedList<>();

    Arrays.stream(ids).forEach(id -> {
      validateFindTweet(id);
      deletedTweets.add(dao.deleteById(id));
    });
    return deletedTweets;
  }
}
