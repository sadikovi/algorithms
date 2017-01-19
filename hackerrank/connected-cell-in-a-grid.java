import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  public static int regionSize(int[][] grid, int n, int m, int i, int j) {
    if (i >= n || j >= m || i < 0 || j < 0) return 0;
    if (grid[i][j] != 1) return 0;
    grid[i][j] = 0;
    return 1 +
      regionSize(grid, n, m, i - 1, j - 1) +
      regionSize(grid, n, m, i, j - 1) +
      regionSize(grid, n, m, i + 1, j - 1) +
      regionSize(grid, n, m, i + 1, j) +
      regionSize(grid, n, m, i + 1, j + 1) +
      regionSize(grid, n, m, i, j + 1) +
      regionSize(grid, n, m, i - 1, j + 1) +
      regionSize(grid, n, m, i - 1, j);
  }

  public static int largestRegion(int[][] grid, int n, int m) {
    int maxSize = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1) {
          // this also marks region as 0s therefore we do not count it again
          // the other way of doing it is maintaining boolean matrix and 
          // marking visited cells as 'true'
          maxSize = Math.max(maxSize, regionSize(grid, n, m, i, j));
        }
      }
    }
    return maxSize;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int[][] grid = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        grid[i][j] = in.nextInt();
      }
    }
    System.out.println(largestRegion(grid, n, m));
  }
}
