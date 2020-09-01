package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidAnagramTest {

  String s = "anagram", t = "nagaram", s2 = "rat", t2 = "car";

  @Test
  public void sorting() {
    assertTrue(ValidAnagram.sorting(s, t));
    assertFalse(ValidAnagram.sorting(s2, t2));
  }

  @Test
  public void map() {
    assertTrue(ValidAnagram.map(s, t));
    assertFalse(ValidAnagram.map(s2, t2));
  }
}