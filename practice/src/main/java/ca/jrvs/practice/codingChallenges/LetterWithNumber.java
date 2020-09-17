package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/Print-letter-with-number-a2f680405dbe43e1a51b5d9f2dd04e80
 */
public class LetterWithNumber {

  /**
   * Big-O:         O(n) time; O(n) space.
   * Justification: have to iterate over all chars in input string and store the original
   *                string plus the number corresponding to each char in the string;
   *                all operations in each iteration are O(1).
   *
   * For each letter in s, print its corresponding index in the alphabet; abc == a1b2c3;
   * 'a' - 'a' == 0; 'b' - 'a' == 1, etc.;
   * uppercase letter indexes come after lower case letter indexes; ABC == A27B28C29.
   */
  public static String stringWithNums(String s) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      result.append(c);
      if (c < 'a')
        result.append(c - 'A' + 1 + 26);
      else
        result.append(c - 'a' + 1);
    }

    return result.toString();
  }

}
