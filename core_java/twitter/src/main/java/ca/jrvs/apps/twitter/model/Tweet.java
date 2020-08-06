package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {

  @JsonProperty("created_at")
  private String createdAt;
  @JsonProperty("id")
  private long id;
  @JsonProperty("id_str")
  private String idStr;
  @JsonProperty("text")
  private String text;
  @JsonProperty("entities")
  private Entity entities;
  @JsonProperty("coordinates")
  private Coordinates coordinates;
  @JsonProperty("retweet_count")
  private int retweetCount;
  @JsonProperty("favorite_count")
  private int favoriteCount;
  @JsonProperty("favorited")
  private boolean favorited;
  @JsonProperty("retweeted")
  private boolean retweeted;

  /**
   * Default constructor for jackson objectMapper; deserialization
   */
  public Tweet() {

  }

  public Tweet(String createdAt, long id, String idStr, String text,
      Entity entities, Coordinates coordinates, int retweetCount, int favoriteCount,
      boolean favorited, boolean retweeted) {
    this.createdAt = createdAt;
    this.id = id;
    this.idStr = idStr;
    this.text = text;
    this.entities = entities;
    this.coordinates = coordinates;
    this.retweetCount = retweetCount;
    this.favoriteCount = favoriteCount;
    this.favorited = favorited;
    this.retweeted = retweeted;
  }

  @JsonProperty("created_at")
  public String getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("created_at")
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  @JsonProperty("id")
  public long getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(long id) {
    this.id = id;
  }

  @JsonProperty("id_str")
  public String getIdStr() {
    return idStr;
  }

  @JsonProperty("id_str")
  public void setIdStr(String idStr) {
    this.idStr = idStr;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @JsonProperty("text")
  public void setText(String text) {
    this.text = text;
  }

  @JsonProperty("entities")
  public Entity getEntities() {
    return entities;
  }

  @JsonProperty("entities")
  public void setEntities(Entity entities) {
    this.entities = entities;
  }

  @JsonProperty("coordinates")
  public Coordinates getCoordinates() {
    return coordinates;
  }

  @JsonProperty("coordinates")
  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  @JsonProperty("retweet_count")
  public int getRetweetCount() {
    return retweetCount;
  }

  @JsonProperty("retweet_count")
  public void setRetweetCount(int retweetCount) {
    this.retweetCount = retweetCount;
  }

  @JsonProperty("favorite_count")
  public int getFavoriteCount() {
    return favoriteCount;
  }

  @JsonProperty("favorite_count")
  public void setFavoriteCount(int favoriteCount) {
    this.favoriteCount = favoriteCount;
  }

  @JsonProperty("favorited")
  public boolean isFavorited() {
    return favorited;
  }

  @JsonProperty("favorited")
  public void setFavorited(boolean favorited) {
    this.favorited = favorited;
  }

  @JsonProperty("retweeted")
  public boolean isRetweeted() {
    return retweeted;
  }

  @JsonProperty("retweeted")
  public void setRetweeted(boolean retweeted) {
    this.retweeted = retweeted;
  }

  @Override
  public String toString() {
    return "Tweet{" +
        "createdAt='" + createdAt + '\'' +
        ", id=" + id +
        ", idStr='" + idStr + '\'' +
        ", text='" + text + '\'' +
        ", entities=" + entities +
        ", coordinates=" + coordinates +
        ", retweetCount=" + retweetCount +
        ", favoriteCount=" + favoriteCount +
        ", favorited=" + favorited +
        ", retweeted=" + retweeted +
        '}';
  }
}
