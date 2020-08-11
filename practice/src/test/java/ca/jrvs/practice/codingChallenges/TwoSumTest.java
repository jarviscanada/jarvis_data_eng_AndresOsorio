package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TwoSumTest {

  int[] nums = {11, 7, 2, 15};

  @Test
  public void bruteForce() {
    assertArrayEquals(TwoSum.bruteForce(nums, 9), new int[]{1, 2});
    assertArrayEquals(TwoSum.bruteForce(nums, 17), new int[]{2, 3});
    assertArrayEquals(TwoSum.bruteForce(nums, 13), new int[]{0, 2});
  }

  @Test
  public void sorting() {
    assertArrayEquals(TwoSum.sorting(nums, 9), new int[]{0, 1});
    assertArrayEquals(TwoSum.sorting(nums, 17), new int[]{0, 3});
    assertArrayEquals(TwoSum.sorting(nums, 13), new int[]{0, 2});
  }

  @Test
  public void map() {
    assertArrayEquals(TwoSum.map(nums, 9), new int[]{1, 2});
    assertArrayEquals(TwoSum.map(nums, 17), new int[]{2, 3});
    assertArrayEquals(TwoSum.map(nums, 13), new int[]{0, 2});
  }
}