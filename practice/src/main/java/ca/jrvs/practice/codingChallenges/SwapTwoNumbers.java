package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/Swap-two-numbers-a336a7522dd345b8b118dc52898c874a
 */
public class SwapTwoNumbers {

  /**
   * Big-O:         O(1) time; O(1) space.
   * Justification: we just perform 3 O(1) operations and don't use extra memory at all.
   *
   * Using '+' and '-'; may cause overflow;
   * use an array so we can pass by reference.
   */
  public static void swapPlusMinus(int[] a) {
    a[0] = a[0] + a[1];
    a[1] = a[0] - a[1];
    a[0] = a[0] - a[1];
  }

  /**
   * Big-O:         see swapPlusMinus.
   * Justification: see swapPlusMinus.
   *
   * Using '*' and '/'; may cause overflow and cannot be used with zeroes;
   * use an array so we can pass by reference.
   */
  public static void swapMultDiv(int[] a) {
    a[0] = a[0] * a[1];
    a[1] = a[0] / a[1];
    a[0] = a[0] / a[1];
  }

  /**
   * Big-O:         see swapPlusMinus.
   * Justification: see swapPlusMinus.
   *
   * Using '^' (XOR) bitwise operator;
   * use an array so we can pass by reference.
   */
  public static void swapXor(int[] a) {
    a[0] = a[0] ^ a[1];
    a[1] = a[0] ^ a[1];
    a[0] = a[0] ^ a[1];
  }

}
