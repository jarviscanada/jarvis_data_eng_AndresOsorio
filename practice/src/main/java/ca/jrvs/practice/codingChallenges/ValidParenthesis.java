package ca.jrvs.practice.codingChallenges;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Ticket: https://www.notion.so/Valid-Parentheses-84fa3ccd340a4539aae216500bea670b
 */
public class ValidParenthesis {

  private static Map<Character, Character> parenthesis;
  private static Deque<Character> stack;

  /**
   * Big-O:         O(n) time; O(n) space.
   * Justification: have to traverse given expression with n chars; stack and map operations are O(1).
   */
  public static boolean validParentheses(String exp) {
    stack = new LinkedList<>();
    parenthesis = new HashMap<>();
    parenthesis.put(']', '[');
    parenthesis.put(')', '(');
    parenthesis.put('}', '{');

    char c = ' ';
    int i = 0;

    while (i < exp.length()) {
      c = exp.charAt(i++);

      // if the top of the stack is an opening parenthesis for the current parenthesis
      // then we pop it bc both chars form a valid parenthesis sub-expression
      if (!stack.isEmpty() && stack.peekFirst() == parenthesis.get(c))
        stack.removeFirst();
      else
        stack.push(c);
    }

    // stack should be empty after iteration if the expression is valid
    return stack.isEmpty();
  }

}
