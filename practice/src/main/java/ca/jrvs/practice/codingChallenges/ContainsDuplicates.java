package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Ticket: https://www.notion.so/Contains-Duplicate-5f279e990704498fbedb1ade993422ba
 */
public class ContainsDuplicates {

  /**
   * Big-O:         O(nlogn) time; O(1) space.
   * Justification: Arrays.sort uses quicksort, which sorts in place in O(nlogn) time.
   *
   * A brute force linear search could also be used but it would result in O(n^2) time since
   * we would need to compare each element with all the others.
   */
  public static boolean sorting(int[] nums) {
    Arrays.sort(nums);

    for (int i = 0; i < (nums.length - 1); i++) {
      if (nums[i] == nums[i + 1])
        return true;
    }
    return false;
  }

  /**
   * Big-O:         O(n) time; O(n) space.
   * Justification: we iterate over nums and add each element to a set, while checking if the set already has that element;
   *                HashSet operations are O(1).
   */
  public static boolean set(int[] nums) {
    Set<Integer> seen = new HashSet<>();

    for (int i : nums) {
      if (!seen.add(i))
        return true;
    }
    return false;
  }

}
