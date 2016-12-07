import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  public static long profit(int[] prices, int n) {
    long profit = 0L;
    int maxSoFar = 0;
    for (int i = prices.length - 1; i > -1; i--) {
      if (prices[i] >= maxSoFar) {
        maxSoFar = prices[i];
      }
      profit += maxSoFar - prices[i];
    }
    return profit;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    for (int c = 0; c < t; c++) {
      int n = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }
      System.out.println(profit(arr, n));
    }
  }
}
