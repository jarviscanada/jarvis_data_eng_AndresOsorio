package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/Remove-Element-9233e4ab2f774a0790d7ef2bdeaf760d
 */
public class RemoveElementsInPace {

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: we iterate over input array once and in each iteration do O(1) ops in place.
   *
   * Remove all occurrences of a given value in a given array in place;
   * return the length 'm' of the array with elements removed;
   * modify the input array so that the first 'm' elements are the elements of input array not to be removed (compacted at the front);
   * the order of those first 'm' elements does not matter;
   * elements beyond the mth element don't matter.
   *
   * Use 2 pointers; a slow one and a fast one; start both at 0; compare fast to the given value;
   * if nums[fast] == value then we advance fast but not slow; else we replace nums[slow] by nums[fast] and advance both;
   * this moves all elements we don't want to remove to the front and slow will the new length.
   */
  public static int remove(int[] nums, int value) {
    int slow = 0;
    int fast = 0;

    while (fast < nums.length) {
      if (nums[fast] == value)
        fast++;
      else
        nums[slow++] = nums[fast++];
    }

    return slow;
  }

}
