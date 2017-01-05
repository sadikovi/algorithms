import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  private static int canMove(char[][] forest, int n, int m, int i, int j) {
    boolean isValid = i >= 0 && i < n && j >= 0 && j < m;
    return (isValid && (forest[i][j] == '.' || forest[i][j] == '*' || forest[i][j] == 'M')) ? 1 : 0;
  }

  private static boolean findStar(char[][] forest, int n, int m, int i, int j, int k) {
    if (k < 0) return false;
    if (canMove(forest, n, m, i, j) == 0) return false;

    char t = forest[i][j];
    forest[i][j] = '!'; // mark visited cell
    if (t == '*') return k == 0;
    int numDirections =
      canMove(forest, n, m, i - 1, j) +
      canMove(forest, n, m, i + 1, j) +
      canMove(forest, n, m, i, j - 1) +
      canMove(forest, n, m, i, j + 1);
    k = (numDirections > 1) ? k - 1 : k;
    return
      findStar(forest, n, m, i - 1, j, k) ||
      findStar(forest, n, m, i + 1, j, k) ||
      findStar(forest, n, m, i, j - 1, k) ||
      findStar(forest, n, m, i, j + 1, k);
  }

  private static boolean isGuessCorrect(char[][] forest, int n, int m, int k) {
    // find 'M' position
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (forest[i][j] == 'M') {
          return findStar(forest, n, m, i, j, k);
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int p = 0; p < t; p++) {
      int n = in.nextInt();
      int m = in.nextInt();
      in.nextLine(); // move pointer to the next line
      char[][] forest = new char[n][m];
      for (int i = 0; i < n; i++) {
        String str = in.nextLine();
        for (int j = 0; j < m; j++) {
          forest[i][j] = str.charAt(j);
        }
      }
      int k = in.nextInt();
      System.out.println(isGuessCorrect(forest, n, m, k) ? "Impressed" : "Oops!");
    }
  }
}
