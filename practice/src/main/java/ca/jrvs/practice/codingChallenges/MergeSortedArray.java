package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;

/**
 * Ticket: https://www.notion.so/Merge-Sorted-Array-e9ec871e189042d8bda46ff3a212d9f6
 */
public class MergeSortedArray {

  /**
   * Big-O:         O(m + n) time; O(m) space, where m and n are the sizes of nums1 and nums2 respectively.
   * Justification: we iterate both arrays once to populate the final one.
   *
   * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
   * The number of elements initialized in nums1 and nums2 are m and n respectively.
   * You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2.
   */
  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    int[] nums1Copy = Arrays.copyOf(nums1, m);

    int i = 0, j = 0, k = 0;

    while (i < m && j < n) {
      if (nums1Copy[i] <= nums2[j])
        nums1[k++] = nums1Copy[i++];
      else
        nums1[k++] = nums2[j++];
    }

    while (i < m)
      nums1[k++] = nums1Copy[i++];

    while (j < n)
      nums1[k++] = nums2[j++];
  }

}
