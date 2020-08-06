package ca.jrvs.apps.twitter.model;

import java.util.HashMap;
import java.util.Map;

public class Coordinates {

  final private String type = "Point";
  private Map<String, Double> values;

  public Coordinates(double longitude, double latitude) {
    values = new HashMap<>(2);
    values.put("longitude", longitude);
    values.put("latitude", latitude);
  }

  public String getType() {
    return type;
  }

  public Map<String, Double> getValues() {
    return values;
  }

  public void setValues(Map<String, Double> values) {
    this.values = values;
  }
}
