package ca.jrvs.practice.sorting;

public class MergeSort {

  private static void merge(int[] arr, int[] l, int[] r) {
    int leftSize = l.length;
    int rightSize = r.length;
    int i = 0, j = 0, k = 0;

    while (i < leftSize && j < rightSize) {
      if (l[i] <= r[j])
        arr[k++] = l[i++];
      else
        arr[k++] = r[j++];
    }

    while (i < leftSize)
      arr[k++] = l[i++];

    while (j < rightSize)
      arr[k++] = r[j++];
  }

  public static void mergeSort(int[] arr) {
    int size = arr.length;

    if (size >= 2) {
      int mid = size / 2;
      int[] l = new int[mid];
      int[] r = new int[size - mid];

      for (int i = 0; i < mid; i++)
        l[i] = arr[i];

      for (int i = mid; i < size; i++)
        r[i - mid] = arr[i];

      mergeSort(l);
      mergeSort(r);

      merge(arr, l, r);
    }
  }

}
