package ca.jrvs.practice.dataStructures.set;

import static org.junit.Assert.*;

import ca.jrvs.practice.dataStructures.tree.JBSTreeTest;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Before;
import org.junit.Test;

public class TreeJSetTest {

  TreeJSet<Integer> mySet;
  Set<Integer> otherSet;
  Integer[] numbers;

  @Before
  public void setUp() throws Exception {
    Comparator<Integer> comparator = (Integer i1, Integer i2) -> i1.intValue() - i2.intValue();
    mySet = new TreeJSet<>(comparator);
    otherSet = new TreeSet<>(comparator);

    numbers = new Integer[]{12, 45, 1, 5, 15, 32};

    mySet.add(numbers[0]);
    mySet.add(numbers[1]);
    mySet.add(numbers[2]);
    mySet.add(numbers[3]);
    mySet.add(numbers[4]);
    mySet.add(numbers[5]);

    otherSet.addAll(Arrays.asList(numbers));
  }

  @Test
  public void size() {
    assertTrue(mySet.size() == 6);
  }

  @Test
  public void contains() {
    assertTrue(mySet.contains(12));
    assertFalse(mySet.contains(20));
  }

  @Test
  public void add() {
    assertTrue(mySet.add(50));
    assertTrue(mySet.add(51));
    assertFalse(mySet.add(50));
    assertTrue(mySet.size() == 8);
  }

  @Test
  public void remove() {
    assertTrue(mySet.remove(15));
    assertTrue(mySet.remove(45));
    assertTrue(mySet.size() == 4);
  }

  @Test
  public void clear() {
    mySet.clear();
    assertTrue(mySet.size() == 0);
  }

  @Test
  public void orderedElements() {
    Object[] result = mySet.orderedElements();
    Arrays.sort(numbers);
    assertArrayEquals(result, numbers);
  }
}