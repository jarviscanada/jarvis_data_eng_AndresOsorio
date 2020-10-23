package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/Duplicates-from-Sorted-Array-f8bca8e4605f4e819077c872fe7bbab9
 */
public class RemoveDuplicatesFromSortedArray {

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: we travers through the array once and modifications are done in place.
   *
   * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length;
   * nums must be modified in place such that the first N elements are the unique elements of the array, where N is the new length.
   *
   * We use 2 pointers; the slow one starts at index 0, while the fast at index 1; the array is sorted so duplicates will be next to each other;
   * in each iteration we compare both pointers, when nums[slow] != nums[fast] that means we've reached the end of the duplicates for a particular number;
   * therefore we increment the slow pointer and replace nums[slow] with nums[fast] (which is the next unique element of the array);
   * since the slow pointer represents the indexes of the unique elements, the new length will be (slow + 1).
   */
  public static int removeDuplicates(int[] nums) {
    if (nums.length == 0)
      return 0;

    int slow = 0;

    for (int fast = 1; fast < nums.length; fast++) {
      if (nums[slow] != nums[fast])
        nums[++slow] = nums[fast];
    }

    return (slow + 1);
  }

}
