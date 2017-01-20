import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  /**
   * The gotcha of this problem if figuring out how to swap in O(1) time and how to maintain index
   * after swapping. We know that permutation is largest when first element is n, second is n - 1,
   * etc. Once we update element at 0 to be 'n' we need to swap elements. For this purpose we
   * maintain index 'tmp' where each element maps to an original index. After each permutation
   * swap, we need to update 'tmp' indices.
   */
  private static void largestPermutation(int[] arr, int k) {
    int[] tmp = new int[arr.length + 1];
    for (int i = 0; i < arr.length; i++) {
      tmp[arr[i]] = i;
    }
    int n = arr.length;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == n) {
        n--;
      } else if (arr[i] < n && k > 0) {
        arr[tmp[n]] = arr[i];
        tmp[arr[i]] = tmp[n];
        arr[i] = n;
        tmp[n] = i;
        n--;
        k--;
      }
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int k = in.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = in.nextInt();
    }

    largestPermutation(arr, k);
    for (int a : arr) {
      System.out.print(a + " ");
    }
    System.out.println();
  }
}
