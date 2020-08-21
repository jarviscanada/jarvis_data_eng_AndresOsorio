package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class RotateStringTest {

  @Test
  public void rotate1() {
    String A = "abcde";
    String B = "cdeab";
    assertTrue(RotateString.rotate1(A, B));

    B = "bcdea";
    assertTrue(RotateString.rotate1(A, B));

    B = "abced";
    assertFalse(RotateString.rotate1(A, B));

    A = "aaabbbccc";
    B = "cccbbbaaa";
    assertFalse(RotateString.rotate1(A, B));
  }

  @Test
  public void rotate2() {
    String A = "abcde";
    String B = "cdeab";
    assertTrue(RotateString.rotate2(A, B));

    B = "bcdea";
    assertTrue(RotateString.rotate2(A, B));

    B = "abced";
    assertFalse(RotateString.rotate2(A, B));

    A = "aaabbbccc";
    B = "cccbbbaaa";
    assertFalse(RotateString.rotate2(A, B));
  }
}