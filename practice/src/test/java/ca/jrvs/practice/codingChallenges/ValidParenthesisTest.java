package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidParenthesisTest {

  @Test
  public void validParentheses() {
    String exp = "(({[[]]}))";
    assertTrue(ValidParenthesis.validParentheses(exp));

    exp = "(()){)[]";
    assertFalse(ValidParenthesis.validParentheses(exp));

    exp = "";
    assertTrue(ValidParenthesis.validParentheses(exp));

    exp = "{[((({[]})))]}";
    assertTrue(ValidParenthesis.validParentheses(exp));
  }
}