package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class MergeSortedArrayTest {

  int[] nums1 = {1, 2, 3, 9, 11, 0, 0, 0, 0};
  int[] nums2 = {2, 5, 6, 23};
  int[] expected = {1, 2, 2, 3, 5, 6, 9, 11, 23};

  @Test
  public void merge() {
    MergeSortedArray.merge(nums1, 5, nums2, 4);
    assertArrayEquals(expected, nums1);
  }
}