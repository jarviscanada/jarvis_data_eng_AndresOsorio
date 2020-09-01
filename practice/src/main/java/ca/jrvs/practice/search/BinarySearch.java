package ca.jrvs.practice.search;

import java.util.Optional;

public class BinarySearch {

  private <E extends Comparable> Optional<Integer> binarySearchRecursionHelper(E[] arr, int left, int right, E target) {
    if (left > right)
      return Optional.empty();

    int mid = (left + (right - left) / 2);

    if (target.compareTo(arr[mid]) == 0)
      return Optional.of(mid);

    if (target.compareTo(arr[mid]) < 0)
      return binarySearchRecursionHelper(arr, left, mid - 1, target);

    // target.compareTo(arr[mid]) > 0
    return binarySearchRecursionHelper(arr, mid + 1, right, target);
  }

  /**
   * find the the target index in a sorted array
   *
   * @param arr input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E extends Comparable> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
    return binarySearchRecursionHelper(arr, 0, arr.length - 1, target);
  }

  /**
   * find the the target index in a sorted array
   *
   * @param arr input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E extends Comparable> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    int left = 0;
    int right = arr.length - 1;
    int mid = 0;

    while (left <= right) {
      mid = (left + (right  - left) / 2);

      if (target.compareTo(arr[mid]) == 0)
        return Optional.of(mid);

      if (target.compareTo(arr[mid]) < 0)
        right = mid - 1;
      else
        left = mid + 1;
    }

    return Optional.empty();
  }

}
