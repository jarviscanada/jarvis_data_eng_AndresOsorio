package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class InsertionSortTest {

  int[] arr = new int[]{3, 5, 1, 2, 8};
  int[] sorted = new int[]{1, 2, 3, 5, 8};

  @Test
  public void insertionSortImperative() {
    InsertionSort.insertionSortImperative(arr);
    assertArrayEquals(arr, sorted);
  }
}