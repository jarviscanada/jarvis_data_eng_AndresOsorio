package ca.jrvs.apps.twitter.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Coordinates {

  private final String type = "Point";
  private List<Float> coordinates;

  public Coordinates(){

  }

  public Coordinates(float longitude, float latitude) {
    coordinates = new ArrayList<>(Arrays.asList(longitude, latitude));
  }

  public String getType() {
    return type;
  }

  public List<Float> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<Float> coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public String toString() {
    return "Coordinates{" +
        "type='" + type + '\'' +
        ", values=" + coordinates +
        '}';
  }
}
