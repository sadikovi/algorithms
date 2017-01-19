import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static int sieve(int n) {
    boolean[] isPrime = new boolean[n + 1];
    for (int i = 2; i < isPrime.length; i++) {
      isPrime[i] = true;
    }

    for (int factor = 2; (long) factor * factor <= n; factor++) {
      if (isPrime[factor]) {
        for (int j = factor; factor * j <= n; j++) {
          isPrime[factor * j] = false;
        }
      }
    }

    int primes = 0;
    for (int i = 2; i < isPrime.length; i++) {
      if (isPrime[i]) primes++;
    }
    return primes;
  }

  public static int findPrimes(int n) {
    if (n <= 0) return 0;
    int[] dp = new int[n + 1];
    dp[0] = 1;
    for (int i = 1; i <= n; i++) {
      if (i < 4) {
        dp[i] = 1;
      } else if (i == 4) {
        dp[i] = 2;
      } else {
        dp[i] = dp[i - 1] + dp[i - 4];
      }
    }
    return sieve(dp[n]);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int i = 0; i < t; i++) {
      int n = in.nextInt();
      System.out.println(findPrimes(n));
    }
  }
}
