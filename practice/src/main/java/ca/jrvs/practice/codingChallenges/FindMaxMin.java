package ca.jrvs.practice.codingChallenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Ticket: https://www.notion.so/Find-Largest-Smallest-c0b0152307cd4ebfae5511875bd6c297
 */
public class FindMaxMin {

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: have to loop through all elements in array.
   */
  public static int loopMax(int[] a) {
    int max = a[0];

    for (int i = 1; i < a.length; i++) {
      if (a[i] > max)
        max = a[i];
    }
    return max;
  }

  /**
   * Big-O:         see loopMax.
   * Justification: see loopMax.
   */
  public static int loopMin(int[] a) {
    int min = a[0];

    for (int i = 1; i < a.length; i++) {
      if (a[i] < min)
        min = a[i];
    }
    return min;
  }

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: have to loop through all elements in array; streams don't hold elements in memory.
   */
  public static int streamMax(int[] a) {
    return Arrays.stream(a).max().getAsInt();
  }

  /**
   * Big-O:         see streamMax.
   * Justification: see streamMax.
   */
  public static int streamMin(int[] a) {
    return Arrays.stream(a).min().getAsInt();
  }

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: have to loop through all elements in array.
   */
  public static int collectionsMax(int[] a) {
    List<Integer> l = new ArrayList<>();

    Arrays.stream(a).forEach(e -> l.add(e));

    return Collections.max(l);
  }

  /**
   * Big-O:         see collectionsMax.
   * Justification: see collectionsMax.
   */
  public static int collectionsMin(int[] a) {
    List<Integer> l = new ArrayList<>();

    Arrays.stream(a).forEach(e -> l.add(e));

    return Collections.min(l);
  }

}
