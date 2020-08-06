package ca.jrvs.apps.twitter.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Coordinates {

  private final String type = "Point";
  private List<Double> coordinates;

  public Coordinates(){

  }

  public Coordinates(double longitude, double latitude) {
    coordinates = new ArrayList<>(Arrays.asList(longitude, latitude));
  }

  public String getType() {
    return type;
  }

  public List<Double> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<Double> coordinates) {
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
