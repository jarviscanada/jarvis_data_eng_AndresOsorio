package ca.jrvs.practice.codingChallenges;

import java.util.HashSet;
import java.util.Set;

/**
 * Ticket: https://www.notion.so/Duplicate-Characters-8b7ebecb62944cb1af93db30246abd82
 */
public class DuplicateCharacters {

  /**
   * Big-O:         O(n) time; O(n) space.
   * Justification: we store O(n) elements in a set and set operations are O(1).
   *
   * We iterate over input and put each element in a set; the input set operation will return false for a duplicate, giving us the answer.
   */
  public static Set<Character> findDuplicates(String s) {
    Set<Character> seen = new HashSet<>();
    Set<Character> result = new HashSet<>();

    int i = 0;
    while(i < s.length()) {
      char c = s.charAt(i++);
      if (c != ' ' && !seen.add(c))
        result.add(c);
    }

    return result;
  }

}
