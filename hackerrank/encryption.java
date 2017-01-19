import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // O(n) time complexity (need to loop over all characters eventually)
  // and O(n) space complexity (need to store data in StringBuilder),
  // loop over low -> high, is pretty much constant, because of the condition
  // low * high <= L, and difference is small number
  private static String encrypt(String s) {
    int n = s.length();
    int low = (int) Math.floor(Math.sqrt(n));
    int high = (int) Math.ceil(Math.sqrt(n));

    // We iterate over rows only, because if columns is set to low as well, then we would get at
    // most low * low <= n (equal when square), and we know that rows <= cols, which means we only
    // need to increment rows until it is the same as columns, because of the condition
    // rows * cols >= n.
    int rows = 0;
    int cols = high;
    for (int i = low; i <= high; i++) {
      if (1L * cols * i >= n) {
        rows = i;
        break;
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < cols; j++) {
      for (int i = 0; i < rows; i++) {
        int index = cols * i + j;
        if (index < n) {
          sb.append(s.charAt(index));
        }
      }
      sb.append(" ");
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String s = in.next();
    System.out.println(encrypt(s));
  }
}
