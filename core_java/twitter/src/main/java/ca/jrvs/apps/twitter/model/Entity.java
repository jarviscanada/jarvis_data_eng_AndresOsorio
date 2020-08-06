package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {

  @JsonProperty("hashtags")
  private List<HashTag> hashTags;
  @JsonProperty("user_mentions")
  private List<UserMention> userMentions;

  public Entity() {

  }

  public Entity(List<HashTag> hashTags, List<UserMention> userMentions) {
    this.hashTags = hashTags;
    this.userMentions = userMentions;
  }

  @JsonProperty("hashtags")
  public List<HashTag> getHashTags() {
    return hashTags;
  }

  @JsonProperty("hashtags")
  public void setHashTags(List<HashTag> hashTags) {
    this.hashTags = hashTags;
  }

  @JsonProperty("user_mentions")
  public List<UserMention> getUserMentions() {
    return userMentions;
  }

  @JsonProperty("user_mentions")
  public void setUserMentions(List<UserMention> userMentions) {
    this.userMentions = userMentions;
  }

  @Override
  public String toString() {
    return "Entity{" +
        "hashTags=" + hashTags +
        ", userMentions=" + userMentions +
        '}';
  }
}
