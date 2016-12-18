import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // Iterative solution O(n^2) time complexity
  public static void findCaveats2(char[][] grid) {
    for (int i = 1; i < grid.length - 1; i++) {
      for (int j = 1; j < grid.length - 1; j++) {
        char t = grid[i - 1][j];
        char b = grid[i + 1][j];
        char l = grid[i][j - 1];
        char r = grid[i][j + 1];
        char c = grid[i][j];
        if (c > t && c > b && c > l && c > r) {
          grid[i][j] = 'X';
        }
      }
    }

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        System.out.print(grid[i][j]);
      }
      System.out.println();
    }
  }

  // Recursive solution, O(n^2) time and O(n^2) space complexity (worst)
  public static void findCaveats(char[][] grid) {
    HashSet<Long> visited = new HashSet<Long>();
    findCaveatsRecur(grid, grid.length, grid.length, 0, 0, visited);
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        System.out.print(grid[i][j]);
      }
      System.out.println();
    }
  }

  private static long pack(int i, int j) {
    long t = (long) i;
    return t << 31 | j;
  }

  private static void findCaveatsRecur(char[][] grid, int n, int m, int i, int j, HashSet<Long> visited) {
    if (i < 0 || j < 0) return;
    if (i >= n || j >= m) return;
    // have scanned this sector already
    if (visited.contains(pack(i, j))) return;
    // assess on being a caveat
    if (i > 0 && i < n - 1 && j > 0 && j < m - 1) {
      char t = grid[i - 1][j];
      char b = grid[i + 1][j];
      char l = grid[i][j - 1];
      char r = grid[i][j + 1];
      char c = grid[i][j];
      if (c > t && c > b && c > l && c > r) {
        grid[i][j] = 'X';
      }
    }

    visited.add(pack(i, j));

    findCaveatsRecur(grid, n, m, i - 1, j, visited);
    findCaveatsRecur(grid, n, m, i + 1, j, visited);
    findCaveatsRecur(grid, n, m, i, j - 1, visited);
    findCaveatsRecur(grid, n, m, i, j + 1, visited);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    char[][] grid = new char[n][n];
    for(int i = 0; i < n; i++){
      grid[i] = in.next().toCharArray();
    }
    findCaveats2(grid);
  }
}
