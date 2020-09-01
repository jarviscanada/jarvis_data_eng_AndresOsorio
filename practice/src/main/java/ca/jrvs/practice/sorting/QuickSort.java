package ca.jrvs.practice.sorting;

public class QuickSort {

  private static void swap(int[] arr, int from, int to) {
    int tmp = arr[from];
    arr[from] = arr[to];
    arr[to] = tmp;
  }

  private static int partition(int[] arr, int begin, int end) {
    int pivot = arr[end];
    int i = begin;

    for (int j = begin; j < end; j++) {
      if (arr[j] <= pivot) {
        if (i != j)
          swap(arr, i, j);
        i++;
      }
    }

    if (i != end)
      swap(arr, i, end);

    return i;
  }

  public static void quickSort(int[] arr, int begin, int end) {
    if (begin < end) {
      int partitionIndex = partition(arr, begin, end);

      quickSort(arr, begin, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, end);
    }
  }

}
