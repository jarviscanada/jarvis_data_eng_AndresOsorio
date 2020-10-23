package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContainsDuplicatesTest {

  int[] nums1 = {1,2,3,1};
  int[] nums2 = {1,2,3,4};
  int[] nums3 = {1,1,1,3,3,4,3,2,4,2};

  @Test
  public void sorting() {
    assertTrue(ContainsDuplicates.sorting(nums1));
    assertFalse(ContainsDuplicates.sorting(nums2));
    assertTrue(ContainsDuplicates.sorting(nums3));
  }

  @Test
  public void set() {
    assertTrue(ContainsDuplicates.set(nums1));
    assertFalse(ContainsDuplicates.set(nums2));
    assertTrue(ContainsDuplicates.set(nums3));
  }
}