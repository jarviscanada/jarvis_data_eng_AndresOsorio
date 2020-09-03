package ca.jrvs.practice.codingChallenges;

import java.util.HashSet;
import java.util.Set;

/**
 * Ticket: https://www.notion.so/Missing-Number-2da97604a7ed4dce997808ba2e58ab72
 */
public class MissingNumber {

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: have to loop 2n times.
   *
   * Susceptible to overflow.
   */
  public static int sumAll(int[] nums) {
    int expectedSum = 0;
    int actualSum = 0;

    for (int i = 1; i < nums.length + 1; i++)
      expectedSum += i;

    for (int num : nums)
      actualSum += num;

    return expectedSum - actualSum;
  }

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: Gauss' formula (n(n + 1) / 2), which calculates the sum of the first n numbers is O(1);
   *                we have to loop through nums to get the actual sum.
   *
   * Susceptible to overflow.
   */
  public static int sumAllGauss(int[] nums) {
    int expectedSum = 0;
    int actualSum = 0;
    int n = nums.length;

    expectedSum = n * (n + 1) / 2;

    for (int num : nums)
      actualSum += num;

    return expectedSum - actualSum;
  }

  /**
   * Big-O:         O(n) time; O(n) space.
   * Justification: have to add all nums to set (hash set ops are O(1)) and then loop again through set;
   *                set takes an extra O(n) space.
   */
  public static int set(int[] nums) {
    Set<Integer> set = new HashSet<>();

    for (int num : nums)
      set.add(num);

    int expectedNumCount = nums.length + 1;

    for (int num = 0; num < expectedNumCount; num++) {
      if (!set.contains(num))
        return num;
    }

    return -1;
  }
}
