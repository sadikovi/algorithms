package test;

public class Test {
  public static void zeroMatrix(int[][] matrix) {
    int n = matrix.length;
    if (n == 0) return;
    int m = matrix[0].length;
    if (m == 0) return;

    boolean[] rows = new boolean[n];
    boolean[] cols = new boolean[m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (matrix[i][j] == 0) {
          rows[i] = true;
          cols[j] = true;
        }
      }
    }

    for (int i = 0; i < n; i++) {
      if (rows[i]) {
        zeroRow(matrix, n, m, i);
      }
    }

    for (int j = 0; j < m; j++) {
      if (cols[j]) {
        zeroColumn(matrix, n, m, j);
      }
    }
  }

  private static void zeroRow(int[][] matrix, int rows, int cols, int row) {
    for (int j = 0; j < cols; j++) {
      matrix[row][j] = 0;
    }
  }

  private static void zeroColumn(int[][] matrix, int rows, int cols, int col) {
    for (int i = 0; i < rows; i++) {
      matrix[i][col] = 0;
    }
  }
}
