// Solution runs in O(n * m) for matrix n x m.
// Space is O(n * m) for memoization and potential recursive stack.
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

// Alternative solution, this uses matrix "visited" to keep track of traversed cells.
class Solution {
  public int longestIncreasingPath(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return 0;
    int max = 0;
    int[][] memo = new int[matrix.length][matrix[0].length];
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        max = Math.max(max, longest(matrix, i, j, visited, memo));
      }
    }
    return max;
  }

  private int longest(int[][] matrix, int i, int j, boolean[][] visited, int[][] memo) {
    if (visited[i][j]) return 0;
    if (memo[i][j] != 0) return memo[i][j];

    visited[i][j] = true;
    int max = 1;
    if (valid(matrix, i+1, j) && matrix[i+1][j] > matrix[i][j]) {
      max = Math.max(max, 1 + longest(matrix, i+1, j, visited, memo));
    }
    if (valid(matrix, i-1, j) && matrix[i-1][j] > matrix[i][j]) {
      max = Math.max(max, 1 + longest(matrix, i-1, j, visited, memo));
    }
    if (valid(matrix, i, j+1) && matrix[i][j+1] > matrix[i][j]) {
      max = Math.max(max, 1 + longest(matrix, i, j+1, visited, memo));
    }
    if (valid(matrix, i, j-1) && matrix[i][j-1] > matrix[i][j]) {
      max = Math.max(max, 1 + longest(matrix, i, j-1, visited, memo));
    }
    visited[i][j] = false;
    memo[i][j] = max;
    return max;
  }

  private boolean valid(int[][] matrix, int i, int j) {
    return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
  }
}
