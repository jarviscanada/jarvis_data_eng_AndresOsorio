package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountPrimesTest {

  @Test
  public void count() {
    assertEquals(0, CountPrimes.count(1));
    assertEquals(0, CountPrimes.count(2));
    assertEquals(1, CountPrimes.count(3));
    assertEquals(2, CountPrimes.count(5));
    assertEquals(4, CountPrimes.count(10));
    assertEquals(5, CountPrimes.count(13));
    assertEquals(8, CountPrimes.count(20));
  }
}