import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// My solution is quite different from editorial, e.g. I have to maintain additional array to
// lookup i - k + 1 element to maintain k - 1 window for current ith value
public class Solution {
  private static String decode(String num, int n, int k) {
    if (num == null || num.length() == 0 || num.length() != n + k - 1) return num;
    StringBuilder sb = new StringBuilder();
    int[] res = new int[n];
    int prev = 0;
    for (int i = 0; i < n; i++) {
      int b = num.charAt(i) - '0';
      res[i] = prev ^ b;
      if (i >= k - 1) {
        prev ^= res[i - k + 1];
      }
      prev ^= res[i];
      sb.append(res[i]);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int k = in.nextInt();
    in.nextLine();
    String num = in.nextLine();
    System.out.println(decode(num, n, k));
  }
}
