package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Ticket: https://www.notion.so/Find-the-Duplicate-Number-190d82f560fc4a43b8c89b3c32dd9949
 */
public class DuplicateNumber {

  /**
   * Big-O:         O(nlogn) time; O(1) space.
   * Justification: Arrays.sort uses quick sort which is O(nlogn) and sorts in place.
   *
   * Because there's exactly one duplicate, then after sorting we just check adjacent entries;
   * we assume the input array is non-empty.
   */
  public static int sorting(int[] a) {
    Arrays.sort(a);

    for (int i = 0; i < (a.length - 1); i++) {
      if (a[i] == a[i + 1])
        return a[i];
    }

    return -1;
  }

  /**
   * Big-O:         O(n) time; O(n) space.
   * Justification: we store O(n) elements in a set and set operations are O(1).
   *
   * We iterate over a and put each element in a set; the input set operation will return false for a duplicate, giving us the answer;
   * we assume the input array is non-empty.
   */
  public static int set(int[] a) {
    Set<Integer> set = new HashSet<>();

    for (int i : a) {
      if (!set.add(i))
        return i;
    }

    return -1;
  }

}
