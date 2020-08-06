package ca.jrvs.apps.twitter.model;

public class UserMention {

  private long id;
  private String idStr;
  private int[] indices;
  private String name;
  private String screen_name;

  public UserMention(long id, String idStr, int[] indices, String name, String screen_name) {
    this.id = id;
    this.idStr = idStr;
    this.indices = indices;
    this.name = name;
    this.screen_name = screen_name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getIdStr() {
    return idStr;
  }

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

  public String getScreen_name() {
    return screen_name;
  }

  public void setScreen_name(String screen_name) {
    this.screen_name = screen_name;
  }
}
