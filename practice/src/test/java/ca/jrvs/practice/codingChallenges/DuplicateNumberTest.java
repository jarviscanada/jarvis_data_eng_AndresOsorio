package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class DuplicateNumberTest {

  int[] nums1 = {1,3,4,2,2};
  int[] nums2 = {3,1,3,4,2};
  int[] nums3 = {1,1};
  int[] nums4 = {4,3,4};

  @Test
  public void sorting() {
    assertEquals(2, DuplicateNumber.sorting(nums1));
    assertEquals(3, DuplicateNumber.sorting(nums2));
    assertEquals(1, DuplicateNumber.sorting(nums3));
    assertEquals(4, DuplicateNumber.sorting(nums4));
  }

  @Test
  public void set() {
    assertEquals(2, DuplicateNumber.set(nums1));
    assertEquals(3, DuplicateNumber.set(nums2));
    assertEquals(1, DuplicateNumber.set(nums3));
    assertEquals(4, DuplicateNumber.set(nums4));
  }
}