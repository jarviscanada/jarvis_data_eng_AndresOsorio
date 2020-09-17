package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class LetterWithNumberTest {

  @Test
  public void stringWithNums() {
    String input = "abcee";
    String expected = "a1b2c3e5e5";
    assertEquals(expected, LetterWithNumber.stringWithNums(input));

    input = "valen";
    expected = "v22a1l12e5n14";
    assertEquals(expected, LetterWithNumber.stringWithNums(input));

    input = "ABCD";
    expected = "A27B28C29D30";
    assertEquals(expected, LetterWithNumber.stringWithNums(input));
  }
}