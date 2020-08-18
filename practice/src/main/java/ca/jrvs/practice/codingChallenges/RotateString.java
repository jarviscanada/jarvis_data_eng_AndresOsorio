package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/Rotate-String-dba6bfb48f9946f6bbf19fccb3a85086
 */
public class RotateString {

  /**
   * Big-O: O(n^2)
   * Justification: all rotations of A are contained in A + A;
   *                the 'contains()' uses the 'indexOf()' method which is O(n);
   *                then for the index returned it has to check that the next (n - 1) chars in A + A
   *                are the chars are the same last (n - 1) chars in B, which is also O(n);
   *                best case = no repeated chars in A; indexOf() (O(n)) + check rest of (n - 1) chars in B follow in A + A (O(n)) == O(n);
   *                worst case = repeated chars in A; indexOf() multiple times (O(n)) and for each time check (n - 1) chars in B follow in A + A == O(n^2)
   */
  public static boolean rotate1(String A, String B) {
    if (A.length() != B.length())
      return false;

    if (A.length() == 0)
      return true;

    return (A + A).contains(B);
  }

  /**
   * Big-O: O(n^2)
   * Justification: for every rotation i, we have to check up to n elements in A and B
   */
  public static boolean rotate2(String A, String B) {
    if (A.length() != B.length())
      return false;

    if (A.length() == 0)
      return true;

    search:
    for (int i = 0; i < A.length(); ++i) {
      for (int j = 0; j < A.length(); ++j) {
        if (A.charAt((i + j) % A.length()) != B.charAt(j))
          continue search;
      }
      return true;
    }
    return false;
  }
}
