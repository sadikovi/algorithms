import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // Algorithm takes O(m) time complexity and O(1) space complexity,
  // where constant is array of 100 elements
  // we do not count resulting list
  public static List<Integer> missingNumbers(int[] a, int[] b) {
    if (a.length == b.length) return new ArrayList<Integer>();

    int min = Integer.MAX_VALUE;
    for (int i = 0; i < b.length; i++) {
      min = Math.min(min, b[i]);
    }

    int[] delta = new int[101];
    for (int i = 0; i < b.length; i++) {
      delta[b[i] - min] += 1;
    }

    for (int i = 0; i < a.length; i++) {
      delta[a[i] - min] -= 1;
    }

    List<Integer> res = new ArrayList<Integer>();
    for (int i = 0; i < delta.length; i++) {
      if (delta[i] > 0) {
        res.add(i + min);
      }
    }
    return res;
  }

  // Runtime is O(m*log(m)), space complexity is O(1) or depends on sorting algorithm
  // Sorting takes m*log(m) time and for-loop takes at most O(m)
  // we do not count resulting list
  public static List<Integer> missingNumbersNaive(int[] a, int[] b) {
    if (a.length == b.length) return new ArrayList<Integer>();
    Arrays.sort(a);
    Arrays.sort(b);
    List<Integer> res = new ArrayList<Integer>();
    int j = 0;
    for (int i = 0; i < a.length; i++) {
      if (j < b.length && a[i] != b[j]) {
        while (j < b.length && a[i] != b[j]) {
          if (res.size() == 0 || res.get(res.size() - 1) != b[j]) {
            res.add(b[j]);
          }
          j++;
        }
      }
      j++;
    }

    if (j < b.length) {
      while (j < b.length) {
        if (res.size() == 0 || res.get(res.size() - 1) != b[j]) {
          res.add(b[j]);
        }
        j++;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }
    int m = in.nextInt();
    int[] b = new int[m];
    for (int i = 0; i < m; i++) {
      b[i] = in.nextInt();
    }

    List<Integer> res = missingNumbers(a, b);
    for (int i = 0; i < res.size(); i++) {
      System.out.print(res.get(i) + " ");
    }
    System.out.println();
  }
}
