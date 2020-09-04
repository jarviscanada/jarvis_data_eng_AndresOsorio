package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class SwapTwoNumbersTest {

  int[] a;

  @Test
  public void swapPlusMinus() {
    a = new int[]{10, 20};
    SwapTwoNumbers.swapPlusMinus(a);
    assertEquals(20, a[0]);
    assertEquals(10, a[1]);

    a = new int[]{78, 34};
    SwapTwoNumbers.swapPlusMinus(a);
    assertEquals(34, a[0]);
    assertEquals(78, a[1]);

    a = new int[]{2, 1};
    SwapTwoNumbers.swapPlusMinus(a);
    assertEquals(1, a[0]);
    assertEquals(2, a[1]);
  }

  @Test
  public void swapMultDiv() {
    a = new int[]{10, 20};
    SwapTwoNumbers.swapMultDiv(a);
    assertEquals(20, a[0]);
    assertEquals(10, a[1]);

    a = new int[]{78, 34};
    SwapTwoNumbers.swapMultDiv(a);
    assertEquals(34, a[0]);
    assertEquals(78, a[1]);

    a = new int[]{2, 1};
    SwapTwoNumbers.swapMultDiv(a);
    assertEquals(1, a[0]);
    assertEquals(2, a[1]);
  }

  @Test
  public void swapXor() {
    a = new int[]{10, 20};
    SwapTwoNumbers.swapXor(a);
    assertEquals(20, a[0]);
    assertEquals(10, a[1]);

    a = new int[]{78, 34};
    SwapTwoNumbers.swapXor(a);
    assertEquals(34, a[0]);
    assertEquals(78, a[1]);

    a = new int[]{2, 1};
    SwapTwoNumbers.swapXor(a);
    assertEquals(1, a[0]);
    assertEquals(2, a[1]);
  }
}