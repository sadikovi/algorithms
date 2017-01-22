package test;

public class Test {
  public static void rotate(int[][] matrix) {
    // assumption is that matrix is at least 1x1
    int n = matrix.length;
    // matrix is empty, just exit
    if (n == 0) return;
    // we only iterate over half of the diagonal elements
    for (int i = 0; i < n/2; i++) {
      rotateLayer(matrix, i, n - 1 - i);
    }
  }

  private static void rotateLayer(int[][] matrix, int start, int end) {
    // iterate over each (end - start) columns in the first row
    for (int j = start; j < end; j++) {
      int buffer = matrix[start][j];
      matrix[start][j] = matrix[j][end];
      matrix[j][end] = matrix[end][end - j + start];
      matrix[end][end - j + start] = matrix[end - j + start][start];
      matrix[end - j + start][start] = buffer;
    }
  }
}
