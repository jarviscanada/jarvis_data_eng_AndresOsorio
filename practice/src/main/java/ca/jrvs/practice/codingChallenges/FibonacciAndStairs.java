package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/Fibonacci-Number-Climbing-Stairs-50fbac6de8e84045b3a9a03d1238cc86
 */
public class FibonacciAndStairs {

  /**
   * Big-O: O(2^n)
   * Justification: values are not cached, instead they are recomputed every time they are needed
   *                and therefore the number of recursive calls increases exponentially as the input increases;
   *                this creates a recursion tree with 2^n nodes (calls) and height n (O(n) space complexity).
   */
  public static int fibRecursive(int n) {
    if (n == 0)
      return 0;

    if (n < 3)
      return  1;

    return fibRecursive(n - 2) + fibRecursive(n - 1);
  }

  /**
   * Big-O: O(n)
   * Justification: values are cached (memoized) whenever they're computed; this removes the need to
   *                recompute values over and over again and the method only calls itself at most 2n times.
   */
  public static int fibMemoized(int n) {
    int[] computed = new int[n];
    return fibMemoizedHelper(n, computed);
  }

  private static int fibMemoizedHelper(int n, int[] computed) {
    if (n == 0)
      return 0;

    if (n < 3)
      return  1;

    if (computed[n - 1] != 0)
      return  computed[n - 1];

    computed[n - 1] = fibMemoizedHelper(n - 2, computed) + fibMemoizedHelper(n - 1, computed);
    return computed[n - 1];
  }

}
