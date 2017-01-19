import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static boolean permutationExists(int[] a, int[] b, int k) {
    Arrays.sort(a);
    Arrays.sort(b); // either sort or traverse in reveresed order
    int i = 0; int j = a.length - 1;
    while (i < a.length && j >= 0) {
      if (a[i++] + b[j--] < k) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int q = in.nextInt();
    for (int p = 0; p < q; p++) {
      int n = in.nextInt();
      int k = in.nextInt();
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = in.nextInt();
      }
      int[] b = new int[n];
      for (int i = 0; i < n; i++) {
        b[i] = in.nextInt();
      }

      System.out.println(permutationExists(a, b, k) ? "YES" : "NO");
    }
  }
}
