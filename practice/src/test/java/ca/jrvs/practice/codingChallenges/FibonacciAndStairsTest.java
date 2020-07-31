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
}