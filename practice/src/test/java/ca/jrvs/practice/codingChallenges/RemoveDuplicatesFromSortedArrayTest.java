package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class RemoveDuplicatesFromSortedArrayTest {

  int[] nums1 = {1, 1, 2};
  int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

  @Test
  public void removeDuplicates() {
    int newLength = RemoveDuplicatesFromSortedArray.removeDuplicates(nums1);
    assertEquals(2, newLength);
    for (int i = 1; i < 2; i++)
      assertEquals(i, nums1[i - 1]);

    newLength = RemoveDuplicatesFromSortedArray.removeDuplicates(nums2);
    assertEquals(5, newLength);
    for (int i = 0; i < 5; i++)
      assertEquals(i, nums2[i]);
  }
}