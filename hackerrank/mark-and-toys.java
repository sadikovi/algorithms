import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // O(nlogn) time complexity and O(1) space complexity
  private static int maxToys(int[] prices, int k) {
    Arrays.sort(prices);
    int toys = 0;
    for (int i = 0; i < prices.length; i++) {
      if (k - prices[i] >= 0) {
        toys++;
        k -= prices[i];
      }
    }
    return toys;
  }

  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    int k = stdin.nextInt();
    int[] prices = new int[n];
    for (int i = 0; i < n; i++) {
      prices[i]=stdin.nextInt();
    }

    int answer = maxToys(prices, k);
    // Compute the final answer from n, k, prices
    System.out.println(answer);
  }
}
