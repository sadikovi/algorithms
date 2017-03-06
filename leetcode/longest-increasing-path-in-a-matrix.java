public class Solution {
  public int longestIncreasingPath(int[][] matrix) {
    int n = matrix.length;
    if (n == 0) return 0;
    int m = matrix[0].length;
    if (m == 0) return 0;

    int totalMax = 0;
    int[][] memo = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        totalMax = Math.max(totalMax, maxIncreasingPath(matrix, n, m, i, j, null, memo));
      }
    }
    return totalMax;
  }

  private int maxIncreasingPath(int[][] matrix, int n, int m, int i, int j, Integer prev, int[][] memo) {
    if (i < 0 || j < 0 || i >= n || j >= m) return 0;
    int elem = matrix[i][j];
    if (prev != null && prev >= elem) return 0;
    if (memo[i][j] != 0) return memo[i][j];
    int p1 = maxIncreasingPath(matrix, n, m, i + 1, j, elem, memo);
    int p2 = maxIncreasingPath(matrix, n, m, i - 1, j, elem, memo);
    int p3 = maxIncreasingPath(matrix, n, m, i, j + 1, elem, memo);
    int p4 = maxIncreasingPath(matrix, n, m, i, j - 1, elem, memo);
    int max = 1 + Math.max(p1, Math.max(p2, Math.max(p3, p4)));
    memo[i][j] = max;
    return max;
  }
}
