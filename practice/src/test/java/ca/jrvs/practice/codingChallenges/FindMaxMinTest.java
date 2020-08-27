package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindMaxMinTest {

  int[] a1 = {1, 2, 3, 4, 5};
  int[] a2 = {8, 122, 4, 7};

  @Test
  public void loopMax() {
    assertEquals(5, FindMaxMin.loopMax(a1));
    assertEquals(122, FindMaxMin.loopMax(a2));
  }

  @Test
  public void loopMin() {
    assertEquals(1, FindMaxMin.loopMin(a1));
    assertEquals(4, FindMaxMin.loopMin(a2));

  }

  @Test
  public void streamMax() {
    assertEquals(5, FindMaxMin.streamMax(a1));
    assertEquals(122, FindMaxMin.streamMax(a2));
  }

  @Test
  public void streamMin() {
    assertEquals(1, FindMaxMin.streamMin(a1));
    assertEquals(4, FindMaxMin.streamMin(a2));
  }

  @Test
  public void collectionsMax() {
    assertEquals(5, FindMaxMin.collectionsMax(a1));
    assertEquals(122, FindMaxMin.collectionsMax(a2));
  }

  @Test
  public void collectionsMin() {
    assertEquals(1, FindMaxMin.collectionsMin(a1));
    assertEquals(4, FindMaxMin.collectionsMin(a2));
  }
}