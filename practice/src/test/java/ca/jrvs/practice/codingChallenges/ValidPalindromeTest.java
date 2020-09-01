package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidPalindromeTest {

  String s1 = "A man, a plan, a canal: Panama";
  String s2 = "race a car";
  String s3 = "race car";

  @Test
  public void twoPointers() {
    assertTrue(ValidPalindrome.twoPointers(s1));
    assertFalse(ValidPalindrome.twoPointers(s2));
    assertTrue(ValidPalindrome.twoPointers(s3));
  }

  @Test
  public void recursive() {
    assertTrue(ValidPalindrome.recursive(s1, 0, s1.length() - 1));
    assertFalse(ValidPalindrome.recursive(s2, 0, s2.length() - 1));
    assertTrue(ValidPalindrome.recursive(s3, 0, s3.length() - 1));
  }
}