import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static boolean isin(int[][] g, int[][] p, int i, int j) {
    if (p.length > g.length - i) return false;
    if (p[0].length > g[0].length - j) return false;
    for (int l = 0; l < p.length; l++) {
      for (int r = 0; r < p[l].length; r++) {
        if (g[i + l][j + r] != p[l][r]) {
          return false;
        }
      }
    }
    return true;
  }

  private static boolean containsInGrid(int[][] g, int[][] p) {
    if (p.length > g.length || p[0].length > g[0].length) return false;

    for (int i = 0; i < g.length; i++) {
      for (int j = 0; j < g[i].length; j++) {
        if (g[i][j] == p[0][0]) {
          if (isin(g, p, i, j)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int a0 = 0; a0 < t; a0++) {
      int R = in.nextInt();
      int C = in.nextInt();
      int[][] G = new int[R][C];
      for (int i = 0; i < R; i++) {
        String str = in.next();
        for (int j = 0; j < C; j++) {
          G[i][j] = (int) str.charAt(j);
        }
      }
      int r = in.nextInt();
      int c = in.nextInt();
      int[][] P = new int[r][c];
      for(int i = 0; i < r; i++) {
        String str = in.next();
        for (int j = 0; j < c; j++) {
          P[i][j] = (int) str.charAt(j);
        }
      }

      String res = containsInGrid(G, P) ? "YES" : "NO";
      System.out.println(res);
    }
  }
}
