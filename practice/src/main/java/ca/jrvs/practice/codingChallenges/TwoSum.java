package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Ticket: https://www.notion.so/Two-Sum-b2e09f7ce06540a6bc762ba8bdc66e92
 */
public class TwoSum {

  /**
   * Big-O:         O(n^2) time; O(1) space.
   * Justification: have to compare each element to the whole list.
   */
  public static int[] bruteForce(int[] nums, int target) {
    int x, y = 0;

    for (int i = 0; i < (nums.length - 1); i++) {
      x = nums[i];
      for (int j = 1; j < nums.length; j++) {
        y = nums[j];
        if (x + y == target && i != j)
          return new int[]{i, j};
      }
    }
    return new int[0];
  }

  /**
   * Big-O:         O(nlog(n)) time; O(1) space.
   * Justification: we do at most n iterations when looking for the two indices by traversing inwards from both ends of the array,
   *                but any sorting algorithm is at least O(nlog(n)).
   */
  public static int[] sorting(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    Arrays.sort(nums);

    while (left < right) {
      int leftVal = nums[left];
      int rightVal = nums[right];

      if (leftVal + rightVal == target)
        return new int[]{left, right};

      if (leftVal + rightVal > target)
        right--;
      else
        left++;
    }
    return new int[0];
  }

  /**
   * Big-O:         O(n) time; O(n) space.
   * Justification: we iterate through the array at most n times to
   *                populate and check the map; since HashMap basic operations (put, get)
   *                are O(1) (containsKey() is just a get() that throws away the retrieved value)
   *                then the whole function is O(n).
   */
  public static int[] map(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      int x = target - nums[i];
      if (map.containsKey(x) && map.get(x) != i)
        return new int[]{map.get(x), i};

      map.put(nums[i], i);
    }
    return new int[0];
  }

}
