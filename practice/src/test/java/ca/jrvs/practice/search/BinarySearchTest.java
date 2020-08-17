package ca.jrvs.practice.search;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTest {

  BinarySearch search = new BinarySearch();
  String[] arr = new String[]{"valen", "mike", "luna", "pete"};

  @Before
  public void setUp() throws Exception {
    Arrays.sort(arr);
  }

  @Test
  public void binarySearchRecursion() {
    assertNotEquals(search.binarySearchIteration(arr, "luna"), Optional.empty());
    assertEquals(search.binarySearchIteration(arr, ""), Optional.empty());

    assertEquals(search.binarySearchIteration(arr, "luna"), Optional.of(0));
    assertEquals(search.binarySearchIteration(arr, "mike"), Optional.of(1));
    assertEquals(search.binarySearchIteration(arr, "pete"), Optional.of(2));
    assertEquals(search.binarySearchIteration(arr, "valen"), Optional.of(3));
  }

  @Test
  public void binarySearchIteration() {
    assertNotEquals(search.binarySearchRecursion(arr, "luna"), Optional.empty());
    assertEquals(search.binarySearchRecursion(arr, ""), Optional.empty());

    assertEquals(search.binarySearchRecursion(arr, "luna"), Optional.of(0));
    assertEquals(search.binarySearchRecursion(arr, "mike"), Optional.of(1));
    assertEquals(search.binarySearchRecursion(arr, "pete"), Optional.of(2));
    assertEquals(search.binarySearchRecursion(arr, "valen"), Optional.of(3));
  }
}