package ca.jrvs.practice.codingChallenges;

public class AToI {

  /**
   * Big-O: O(n)
   * Justification: the Java Integer.parseInt method interates
   *                through the whole string to be able to parse into an int
   */
  public static int convertJavaApi(String str) {
    int i = 0;
    str = str.trim();

    if (str.length() == 0 || str == null)
      return 0;

    if (!Character.isDigit(str.charAt(0)) && (str.charAt(0) != '-' && str.charAt(0) != '+'))
      return 0;

    StringBuilder string = new StringBuilder();

    if (str.charAt(0) == '-' || str.charAt(0) == '+') {
      string.append(str.charAt(i++));
      if (str.length() == 1)
        return 0;
      if (!Character.isDigit(str.charAt(i)))
        return 0;
    }

    while (i < str.length() && Character.isDigit(str.charAt(i))) {
      char c = str.charAt(i++);
      string.append(c);
    }

    int result = 0;

    String stringResult = string.toString();
    try {
      result = Integer.parseInt(stringResult);
    } catch (NumberFormatException e) {
      if (str.charAt(0) == '-')
        result = Integer.MIN_VALUE;
      else
        result = Integer.MAX_VALUE;
    }

    return result;
  }

  /**
   * Big-O: O(n)
   * Justification: have to iterate through the input string of size n
   *                and perform O(1) operations in each iteration
   */
  public static int convert(String str) {
    str = str.trim();

    if (str.length() == 0 || str == null)
      return 0;

    long result = 0;
    int i = 0, sign = 0;

    if (str.charAt(0) == '-') {
      if (str.length() == 1)
        return 0;
      sign = -1;
      i++;
    }

    if (str.charAt(0) == '+') {
      if (str.length() == 1)
        return 0;
      sign = 1;
      i++;
    }

    while (i < str.length() && isDigit(str.charAt(i))) {
      int c = str.charAt(i++);
      result *= 10;
      result += (c - '0');

      if ((sign == 1 || sign == 0) && result > Integer.MAX_VALUE)
        return Integer.MAX_VALUE;

      if (sign == -1 && -result < Integer.MIN_VALUE)
        return Integer.MIN_VALUE;
    }

    if (sign == -1)
      result *= -1;

    return (int)result;
  }

  private static boolean isDigit(char a) {
    return (a >= 48 && a <= 57);
  }

}
