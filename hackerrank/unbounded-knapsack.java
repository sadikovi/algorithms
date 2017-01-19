import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static int knapsack(int[] arr, int k) {
    if (k <= 0 || arr.length == 0) return 0;
    int[] dp = new int[k + 1];
    for (int i = 0; i < dp.length; i++) {
      for (int p : arr) {
        if (i >= p) {
          int res = dp[i - p] + p;
          if (res > dp[i]) {
            dp[i] = res;
          }
        }
      }
    }
    return dp[k];
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int i = 0; i < t; i++) {
      int n = in.nextInt();
      int k = in.nextInt();
      int[] arr = new int[n];
      for (int j = 0; j < n; j++) {
        arr[j] = in.nextInt();
      }
      System.out.println(knapsack(arr, k));
    }
  }
}
