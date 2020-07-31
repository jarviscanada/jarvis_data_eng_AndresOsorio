package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class FibonacciAndStairsTest {

  @Test
  public void fibRecursive() {
    assertTrue(FibonacciAndStairs.fibRecursive(0) == 0);
    assertTrue(FibonacciAndStairs.fibRecursive(1) == 1);
    assertTrue(FibonacciAndStairs.fibRecursive(2) == 1);
    assertTrue(FibonacciAndStairs.fibRecursive(3) == 2);
    assertTrue(FibonacciAndStairs.fibRecursive(8) == 21);
    assertTrue(FibonacciAndStairs.fibRecursive(10) == 55);
  }

  @Test
  public void fibMemoized() {
    assertTrue(FibonacciAndStairs.fibMemoized(0) == 0);
    assertTrue(FibonacciAndStairs.fibMemoized(1) == 1);
    assertTrue(FibonacciAndStairs.fibMemoized(2) == 1);
    assertTrue(FibonacciAndStairs.fibMemoized(3) == 2);
    assertTrue(FibonacciAndStairs.fibMemoized(8) == 21);
    assertTrue(FibonacciAndStairs.fibMemoized(10) == 55);
  }

  @Test
  public void stairsRecursive() {
    assertTrue(FibonacciAndStairs.stairsRecursive(1) == 1);
    assertTrue(FibonacciAndStairs.stairsRecursive(2) == 2);
    assertTrue(FibonacciAndStairs.stairsRecursive(3) == 3);
    assertTrue(FibonacciAndStairs.stairsRecursive(4) == 5);
    assertTrue(FibonacciAndStairs.stairsRecursive(5) == 8);
    assertTrue(FibonacciAndStairs.stairsRecursive(6) == 13);
    assertTrue(FibonacciAndStairs.stairsRecursive(10) == 89);
  }

  @Test
  public void stairsMemoized() {
    assertTrue(FibonacciAndStairs.stairsMemoized(1) == 1);
    assertTrue(FibonacciAndStairs.stairsMemoized(2) == 2);
    assertTrue(FibonacciAndStairs.stairsMemoized(3) == 3);
    assertTrue(FibonacciAndStairs.stairsMemoized(4) == 5);
    assertTrue(FibonacciAndStairs.stairsMemoized(5) == 8);
    assertTrue(FibonacciAndStairs.stairsMemoized(6) == 13);
    assertTrue(FibonacciAndStairs.stairsMemoized(10) == 89);
  }
}