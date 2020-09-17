package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class BubbleSortTest {

  BubbleSort sort = new BubbleSort();
  int[] arr = new int[]{3, 5, 1, 2, 8};
  int[] sorted = new int[]{1, 2, 3, 5, 8};

  @Test
  public void bubbleSort() {
    sort.bubbleSort(arr);
    assertArrayEquals(arr, sorted);
  }
}