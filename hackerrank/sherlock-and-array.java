import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static boolean equalExists(int[] arr, int n) {
    int[] sums = new int[n + 1];
    for (int i = 1; i < sums.length; i++) {
      sums[i] = sums[i - 1] + arr[i - 1];
    }

    for (int i = 1; i < sums.length; i++) {
      if (sums[i - 1] == sums[n] - sums[i]) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int T = in.nextInt();
    for (int t = 1; t <= T; t++) {
      int n = in.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = in.nextInt();
      }

      System.out.println((equalExists(arr, n)) ? "YES" : "NO");
    }
  }
}
