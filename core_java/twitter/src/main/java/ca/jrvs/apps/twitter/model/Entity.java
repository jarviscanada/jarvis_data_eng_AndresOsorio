package ca.jrvs.apps.twitter.model;

import java.util.List;

public class Entity {

  private List<HashTag> hashTags;
  private List<UserMention> userMentions;

  public Entity(List<HashTag> hashTags, List<UserMention> userMentions) {
    this.hashTags = hashTags;
    this.userMentions = userMentions;
  }

  public List<HashTag> getHashTags() {
    return hashTags;
  }

  public void setHashTags(List<HashTag> hashTags) {
    this.hashTags = hashTags;
  }

  public List<UserMention> getUserMentions() {
    return userMentions;
  }

  public void setUserMentions(List<UserMention> userMentions) {
    this.userMentions = userMentions;
  }
}
