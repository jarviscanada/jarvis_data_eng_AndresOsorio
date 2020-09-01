package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/Check-if-a-String-contains-only-digits-c61dbb86e8df48bcbcc689d421cec7a6
 */
public class OnlyDigitsInString {

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: have to check n characters in string s.
   */
  public static boolean ascii(String s) {
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if ((c < 48) || c > 57)
        return false;
    }
    return true;
  }

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: 'Integer.valueOf' method goes through all chars in string s.
   */
  public static boolean usingInteger(String s) {
    if (s.isEmpty()) return true;

    try {
      Integer.valueOf(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * Big-O:         O(n) time; O(n) space.
   * Justification: the 'matches' method in String has to go through every char in s.
   */
  public static boolean usingRegex(String s) {
    return s.matches("\\d*");
  }

}
