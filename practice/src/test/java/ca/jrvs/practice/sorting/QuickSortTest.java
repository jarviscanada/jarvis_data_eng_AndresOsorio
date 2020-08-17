package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuickSortTest {

  int[] arr = new int[]{3, 5, 1, 10, 15, 2, 8};
  int[] sorted = new int[]{1, 2, 3, 5, 8, 10, 15};

  @Test
  public void quickSort() {
    QuickSort.quickSort(arr, 0, arr.length - 1);
    assertArrayEquals(arr, sorted);
  }
}