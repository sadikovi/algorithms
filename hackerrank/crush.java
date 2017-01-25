import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // O(NM) => O(M + N), is solved with prefix sums
  private static long crush(long[] arr, int m, int[] a, int[] b, int[] k) {
    for (int i = 0; i < m; i++) {
      arr[a[i] - 1] += k[i];
      if (b[i] < arr.length) {
        arr[b[i]] -= k[i];
      }
    }

    for (int i = 1; i < arr.length; i++) {
      arr[i] += arr[i - 1];
    }

    long max = 0;
    for (long elem : arr) {
      max = Math.max(max, elem);
    }
    return max;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int[] a = new int[m];
    int[] b = new int[m];
    int[] k = new int[m];
    for (int i = 0; i < m; i++) {
      a[i] = in.nextInt();
      b[i] = in.nextInt();
      k[i] = in.nextInt();
    }

    System.out.println(crush(new long[n], m, a, b, k));
  }
}
