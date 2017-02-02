public class Solution {
  public int[][] generateMatrix(int n) {
    int[][] matrix = new int[n][n];
    int num = 1;
    for (int i = 0; i <= n/2; i++) {
      num = fillLayer(matrix, i, n - 1 - i, num);
    }
    return matrix;
  }

  private int fillLayer(int[][] matrix, int start, int end, int num) {
    if (start == end) {
      matrix[start][start] = num;
      return ++num;
    }
    for (int j = start; j < end; j++) {
      matrix[start][j] = num;
      num++;
    }
    for (int i = start; i < end; i++) {
      matrix[i][end] = num;
      num++;
    }
    for (int j = end; j > start; j--) {
      matrix[end][j] = num;
      num++;
    }
    for (int i = end; i > start; i--) {
      matrix[i][start] = num;
      num++;
    }
    return num;
  }
}
