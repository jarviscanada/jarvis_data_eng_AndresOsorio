package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/Count-Primes-6f576c2191af4432bab3837c0a260912
 */
public class CountPrimes {

  /**
   * Big-O:         O(n^1.5) time; O(1) space.
   * Justification: have to consider sqrt(n) numbers for every number: (n * (n^0.5)) = n^1.5
   *
   * A prime number n is any number > 1 that is only divisible by itself and 1;
   * naively, we would check if n is divisible by any of the numbers < n O(n);
   * however, any number n is only divisible by numbers <= (n / 2), therefore we only consider (n / 2) numbers;
   * further, if n is divisible by some number p, then n = (p * q) and since p <= q, we can derive that p <= sqrt(n);
   * therefore we only need to consider sqrt(n) numbers to verify whether n is prime or not (O(sqrt(n)).
   *
   * The Sieve of Eratosthenes is one of the most efficient ways to find all prime numbers up to n;
   * it runs in O(nloglogn) and uses an array to mark numbers prime or non-prime.
   */
  public static int count(int n) {
    int count = 0;
    for (int i = 2; i < n; i++) {
      if (isPrime(i))
        count++;
    }
    return count;
  }

  /**
   * Big-O:         O(n^0.5) time; O(1) space.
   * Justification: have to consider sqrt(num) numbers.
   */
  private static boolean isPrime(int num) {
    if (num <= 1)
      return false;

    // use (i * i <= num) instead of (i <= sqrt(num)) as the former is less expensive
    for (int i = 2; (i * i) <= num; i++) {
      if (num % i == 0)
        return false;
    }
    return true;
  }

}
