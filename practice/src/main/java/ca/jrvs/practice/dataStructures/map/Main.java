package ca.jrvs.practice.dataStructures.map;

import ca.jrvs.practice.dataStructures.list.Employee;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    Map<Employee, List<String>> map = new HashMap<>();

    Employee juan = new Employee(1, "Juan", 20, 25000);
    List<String> juanEmployers = Arrays.asList("Starbucks", "Cineplex", "Tim Hortons");

    Employee ana = new Employee(2, "Ana", 25, 20000);
    List<String> anaEmployers = Arrays.asList("Ardenes", "Zara", "McDonalds");

    Employee rob = new Employee(3, "Rob", 35, 65000);
    List<String> robEmployers = Arrays.asList("RBC", "CIBC", "CIA", "ONU");

    map.put(juan, juanEmployers);
    map.put(ana, anaEmployers);
    map.put(rob, robEmployers);

    System.out.println(juan.hashCode());
    System.out.println(map.get(juan).toString());
    System.out.println(map.get(rob).toString());
  }

}
