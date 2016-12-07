import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static long candies(int[] arr, int n) {
    int[] dp = new int[n];
    dp[0] = 1;
    for (int i = 1; i < n; i++) {
      if (arr[i] > arr[i - 1]) {
        dp[i] = dp[i - 1] + 1;
      } else {
        dp[i] = 1;
      }
    }
    for (int i = n - 2; i >= 0; i--) {
      if (arr[i] > arr[i + 1]) {
        dp[i] = Math.max(dp[i], dp[i + 1] + 1);
      }
    }

    long sum = 0;
    for (int k : dp) {
      sum += k;
    }
    return sum;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = in.nextInt();
    }
    System.out.println(candies(arr, n));
  }
}
