import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // used for reference:
  // http://www.hihuyue.com/hihuyue/codepractise/ctci/ctci-chater8-dynamic-programming-coin-change
  public static long makeChange(int[] coins, int money) {
    int n = coins.length;
    long[] ways = new long[money + 1];
    ways[0] = 1;
    for (int i = 0; i < n; ++i) {
      for (int j = coins[i]; j <= money; ++j) {
        ways[j] += ways[j - coins[i]];
      }
    }
    return ways[money];
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int coins[] = new int[m];
    for(int coins_i=0; coins_i < m; coins_i++){
      coins[coins_i] = in.nextInt();
    }
    System.out.println(makeChange(coins, n));
  }
}
