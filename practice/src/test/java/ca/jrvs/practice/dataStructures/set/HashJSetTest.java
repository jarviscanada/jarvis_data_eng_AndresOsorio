package ca.jrvs.practice.dataStructures.set;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class HashJSetTest {

  JSet<String> mySet;
  Set<String> otherSet;
  String[] strings;

  @Before
  public void setUp() throws Exception {
    mySet = new HashJSet<>();
    otherSet = new HashSet<>();

    strings = new String[]{"Orange", "Apple", "Banana"};

    mySet.add(strings[0]);
    mySet.add(strings[1]);
    mySet.add(strings[2]);

    otherSet.addAll(Arrays.asList(strings));
  }

  @Test
  public void size() {
    assertTrue(mySet.size() == 3);
  }

  @Test
  public void contains() {
    assertTrue(mySet.contains("Orange"));
  }

  @Test
  public void add() {
    boolean inserted = mySet.add("Cherry");
    assertTrue(inserted);
    assertFalse(mySet.add("Cherry"));
  }

  @Test
  public void remove() {
    assertTrue(mySet.remove("Orange"));
    assertFalse(mySet.remove("Orange"));
  }

  @Test
  public void clear() {
    mySet.clear();
    assertTrue(mySet.size() == 0);
  }
}