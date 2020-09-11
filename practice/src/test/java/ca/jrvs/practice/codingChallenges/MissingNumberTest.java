package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class MissingNumberTest {

  int[] nums1 = {3, 0, 1};
  int[] nums2 = {9, 6, 4, 2, 3, 5, 7, 0, 1};

  @Test
  public void sumAll() {
    assertEquals(2, MissingNumber.sumAll(nums1));
    assertEquals(8, MissingNumber.sumAll(nums2));
  }

  @Test
  public void sumAllGauss() {
    assertEquals(2, MissingNumber.sumAllGauss(nums1));
    assertEquals(8, MissingNumber.sumAllGauss(nums2));
  }

  @Test
  public void set() {
    assertEquals(2, MissingNumber.set(nums1));
    assertEquals(8, MissingNumber.set(nums2));
  }
}