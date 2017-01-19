import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  private static boolean colSorted(char[][] matrix, int n, int j) {
    for (int i = 1; i < n; i++) {
      if (matrix[i][j] < matrix[i - 1][j]) return false;
    }
    return true;
  }
  // Runtime is O(n^3) in worst case, because of sorting each row using insertion sort
  private static boolean sorted(char[][] matrix, int n) {
    for (int i = 0; i < n; i++) {
      // Use insertion sort, because of the constaint on using swap
      for (int j = 0; j < n; j++) {
        int k = j;
        while (k > 0 && matrix[i][k - 1] > matrix[i][k]) {
          char t = matrix[i][k - 1];
          matrix[i][k - 1] = matrix[i][k];
          matrix[i][k] = t;
          k--;
        }
      }
      // Alternative is sorting each row:
      // Arrays.sort(matrix[i]);
    }
    // check that columns are in order
    for (int j = 0; j < n; j++) {
      if (!colSorted(matrix, n, j)) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int i = 0; i < t; i++) {
      int n = in.nextInt();
      in.nextLine();
      char[][] matrix = new char[n][n];
      for (int j = 0; j < n; j++) {
        String str = in.nextLine();
        for (int k = 0; k < n; k++) {
          matrix[j][k] = str.charAt(k);
        }
      }
      System.out.println(sorted(matrix, n) ? "YES" : "NO");
    }
  }
}
