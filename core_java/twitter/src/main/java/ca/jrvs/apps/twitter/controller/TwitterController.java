package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller {

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";
  private static final String USAGE = "USAGE: TwitterCLIApp";
  private static final String POST_USAGE = USAGE + " post \"tweet_text\" \"longitude:latitude\"";
  private static final String SHOW_USAGE = USAGE + " show \"tweet_id\"";
  private static final String DELETE_USAGE = USAGE + " delete \"tweet_id1, tweet_id2, ...\"";

  private Service service;

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }

  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 2)
      throw new IllegalArgumentException(POST_USAGE);

    String text = args[0];
    if (text.isEmpty())
      throw new IllegalArgumentException("Invalid text format: " + POST_USAGE);
    String coordsString = args[1];
    String[] coords = coordsString.split(COORD_SEP);
    if (coords.length != 2)
      throw new IllegalArgumentException("Invalid coordinates format: " + POST_USAGE);

    float lg = 0;
    float lt = 0;
    try {
      lg = Float.parseFloat(coords[0]);
      lt = Float.parseFloat(coords[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid coordinates format: " + POST_USAGE, e);
    }

    Tweet tweet = new Tweet("", 0, "", text, null, new Coordinates(lg, lt), 0, 0, false, false);

    return service.postTweet(tweet);
  }

  @Override
  public Tweet showTweet(String[] args) {
    if (args.length < 1)
      throw new IllegalArgumentException(SHOW_USAGE);

    String id = args[0];

    return service.showTweet(id, new String[0]);
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length < 1)
      throw new IllegalArgumentException(DELETE_USAGE);

    String[] ids = args[0].split(COMMA);
    return service.deleteTweets(ids);
  }
}
