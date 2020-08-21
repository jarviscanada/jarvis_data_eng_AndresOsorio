package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class MergeSortTest {

  int[] arr = new int[]{3, 5, 1, 10, 15, 2, 8};
  int[] sorted = Arrays.copyOf(arr, arr.length);

  int[] arr2 = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
  int[] sorted2 = Arrays.copyOf(arr2, arr2.length);

  @Before
  public void setUp() {
    Arrays.sort(sorted);
    Arrays.sort(sorted2);
  }

  @Test
  public void mergeSort() {
    MergeSort.mergeSort(arr);
    assertArrayEquals(arr, sorted);

    MergeSort.mergeSort(arr2);
    assertArrayEquals(arr2, sorted2);
  }
}