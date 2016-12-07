import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static int[] find(int[] a, int n) {
    int maxSum = Integer.MIN_VALUE;
    int sum = 0;
    int nc = Integer.MIN_VALUE;

    for (int i=0; i<a.length; i++) {
      sum += a[i];
      if (maxSum < sum) {
        maxSum = sum;
      }

      if (sum < 0) {
        sum = 0;
      }

      if (nc < 0) {
        nc = Math.max(nc, a[i]);
      } else if (a[i] > 0) {
        nc += a[i];
      }
    }
    return new int[]{maxSum, nc};
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int i = 0; i < t; i++) {
      int n = in.nextInt();
      int[] arr = new int[n];
      for (int k = 0; k < n; k++) {
        arr[k] = in.nextInt();
      }
      int[] res = find(arr, n);
      System.out.println(res[0] + " " + res[1]);
    }
  }
}
