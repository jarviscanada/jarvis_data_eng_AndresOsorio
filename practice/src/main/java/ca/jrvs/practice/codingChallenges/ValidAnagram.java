package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

/**
 * Ticket: https://www.notion.so/Valid-Anagram-38c7a6bbc31b4fe8b6cc2c844d36cd3f
 */
public class ValidAnagram {

  /**
   * Big-O:         O(nlgn) time; O(n) space.
   * Justification: Arrays.sort() is O(nlogn), all other operations are < O(nlong).
   *
   * If t is an anagram of s then they will be the same string after sorting.
   */
  public static boolean sorting(String s, String t) {
    if (s.length() != t.length())
      return false;

    if (s.isEmpty())
      return true;

    char[] sArray = s.toCharArray();
    char[] tArray = t.toCharArray();

    Arrays.sort(sArray);
    Arrays.sort(tArray);

    return Arrays.equals(sArray, tArray);
  }

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: it is O(n) time bc map operations used are O(1);
   *                it is O(1) space bc although we use extra space, the map's size stays constant no matter how large n is.
   *
   * Use a map to put chars of strings as keys and the number of their occurrences as values;
   * increment value of char if present in s and decrement it if present in t; if t is an anagram
   * of s then the map must contain a value of 0 for each char in the end.
   */
  public static boolean map(String s, String t) {
    if (s.length() != t.length())
      return false;

    if (s.isEmpty())
      return true;

    Map<Character, Integer> map = new Hashtable<>();

    for (int i = 0; i < s.length(); i++) {
      char one = s.charAt(i);
      char two = t.charAt(i);

      if (!map.containsKey(one))
        map.put(one, 1);
      else
        map.put(one, map.get(one) + 1);

      if (!map.containsKey(two))
        map.put(two, -1);
      else
        map.put(two, map.get(two) - 1);
    }

    for (Character key : map.keySet()) {
      if (map.get(key) != 0)
        return false;
    }

    return true;
  }

}
