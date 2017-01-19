import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static boolean isValid(String str) {
    int[] arr = new int[128];
    for (char t : str.toCharArray()) {
      arr[t]++;
    }

    long min = Integer.MAX_VALUE;
    long max = Integer.MIN_VALUE;
    int unique = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > 0) {
        min = Math.min(min, arr[i]);
        max = Math.max(max, arr[i]);
        unique++;
      }
    }

    // all elements have the same count, just return true
    if (max == min) return true;

    int minCount = 0;
    int maxCount = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == min) minCount++;
      if (arr[i] == max) maxCount++;
    }

    if (minCount + maxCount == unique) {
      return (maxCount == 1 && (max - min) == 1) || (minCount == 1 && min == 1);
    }

    return false;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String str = in.next();
    System.out.println(isValid(str) ? "YES" : "NO");
  }
}
