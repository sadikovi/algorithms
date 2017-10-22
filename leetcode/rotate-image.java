// O(n^2) time and O(1) space complexity.
//
// Idea of the solution is rotating a ring and 4 elements at a time, this allows to store one of
// them in `tmp` and overwrite them with a rotated value.
//
// Be careful, `i` is treated in `rotateRing` as an offset (from 0 to side length),
// not from start to end.
// Also need to be very careful with indices of 4 rotating elements.
//
class Solution {
  public void rotate(int[][] matrix) {
    if (matrix.length == 0) return;
    int n = matrix.length;
    for (int i = 0; i < n/2; i++) {
      rotateRing(matrix, i, n - i - 1);
    }
  }

  private void rotateRing(int[][] matrix, int start, int end) {
    for (int i = 0; i < end - start; i++) {
      int tmp = matrix[start][start + i];
      matrix[start][start + i] = matrix[end - i][start];
      matrix[end - i][start] = matrix[end][end - i];
      matrix[end][end - i] = matrix[start + i][end];
      matrix[start + i][end] = tmp;
    }
  }
}
