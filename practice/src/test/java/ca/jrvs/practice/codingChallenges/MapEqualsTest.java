package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import ca.jrvs.practice.dataStructures.list.Employee;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class MapEqualsTest {

  MapEquals mapComparator;
  Map<String, Employee> m1, m2;

  @Before
  public void setUp() throws Exception {
    mapComparator = new MapEquals();
    m1 = new HashMap<>();
    m2 = new HashMap<>();

    Employee e1 = new Employee(1, "Valen", 22, 25000);
    Employee e2 = new Employee(2, "Bob", 29, 300000);
    Employee e3 = new Employee(1, "Valen", 22, 25000);
    Employee e4 = new Employee(2, "Bob", 29, 300000);

    m1.put(new String("first"), e1);
    m1.put(new String("second"), e2);
    m2.put(new String("first"), e3);
    m2.put(new String("second"), e4);
  }

  @Test
  public void nodeEquals() {
    assertTrue(mapComparator.nodeEquals(m1, m2));
  }

  @Test
  public void mapEquals() {
    assertTrue(mapComparator.mapEquals((HashMap)m1, (HashMap)m2));
  }
}