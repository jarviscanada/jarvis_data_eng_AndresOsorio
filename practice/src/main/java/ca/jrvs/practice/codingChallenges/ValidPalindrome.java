package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/Valid-Palindrome-8909d5ce5be647f39a3152271abf1bfc
 */
public class ValidPalindrome {

  private static boolean isAlphanumeric(char c) {
    return (c != ' ' && (Character.isLetter(c) || Character.isDigit(c)));
  }

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: we have to traverse all chars of the input string (from both sides) once
   *                and all ops in each iteration are O(1).
   */
  public static boolean twoPointers(String s) {
    s = s.toLowerCase();
    char left = ' ';
    char right =' ';
    int i = 0;
    int j = s.length() - 1;

    while (i < j) {
      left = s.charAt(i);
      right = s.charAt(j);

      if (!isAlphanumeric(left)) {
        i++;
      } else if (!isAlphanumeric(right)) {
        j--;
      } else if (left != right) {
        return false;
      } else {
        i++;
        j--;
      }
    }

    return true;
  }

  /**
   * Big-O:         O(n) time; O(n) space.
   * Justification: this method calls itself at most n/2 times and performs O(1) ops each time;
   *                recursive tree with n/2 nodes (time complexity) and n/2 height (space complexity - call stack).
   */
  public static boolean recursive(String s, int left, int right) {
    if (left >= right)
      return true;

    if (left == 0)
      s = s.toLowerCase();

    if (!isAlphanumeric(s.charAt(left))) {
      left++;
    } else if (!isAlphanumeric(s.charAt(right))) {
      right--;
    } else if (s.charAt(left) != s.charAt(right)) {
      return false;
    } else {
      left++;
      right--;
    }

    return (true && recursive(s, left, right));
  }

}
