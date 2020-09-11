package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class RemoveElementsInPaceTest {

  int[] nums, newNums;

  @Test
  public void remove() {
    nums = new int[]{3, 2, 2, 3};
    newNums = new int[]{2, 2};

    assertEquals(2, RemoveElementsInPace.remove(nums, 3));

    for (int i = 0; i < 2; i++)
      assertEquals(newNums[i], nums[i]);

    nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
    newNums = new int[]{0, 1, 3, 0, 4};

    assertEquals(5, RemoveElementsInPace.remove(nums, 2));

    for (int i = 0; i < 2; i++)
      assertEquals(newNums[i], nums[i]);
  }
}