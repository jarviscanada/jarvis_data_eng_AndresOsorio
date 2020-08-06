package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;

public class UserMention {

  private long id;
  @JsonProperty("id_str")
  private String idStr;
  private int[] indices;
  private String name;
  @JsonProperty("screen_name")
  private String screenName;

  public UserMention() {

  }

  public UserMention(long id, String idStr, int[] indices, String name, String screenName) {
    this.id = id;
    this.idStr = idStr;
    this.indices = indices;
    this.name = name;
    this.screenName = screenName;
  }

  public long getId() {
    return id;
  }

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

  public int[] getIndices() {
    return indices;
  }

  public void setIndices(int[] indices) {
    this.indices = indices;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("screen_name")
  public String getScreenName() {
    return screenName;
  }

  @JsonProperty("screen_name")
  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  @Override
  public String toString() {
    return "UserMention{" +
        "id=" + id +
        ", idStr='" + idStr + '\'' +
        ", indices=" + Arrays.toString(indices) +
        ", name='" + name + '\'' +
        ", screenName='" + screenName + '\'' +
        '}';
  }
}
