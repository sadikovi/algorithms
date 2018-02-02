// Solution runs:
// - precompute: O(n^2) time and O(n^2) space
// - query: O(1) time and O(1) space
//
// Idea is storing matrix of rolling sums for each element:
// For matrix like this:
//  [1, 2]
//  [3, 4]
// We store sums like this:
//  [0, 0,  0]
//  [0, 1,  3]
//  [0, 4, 10]
//
// Then we can use offsets to compute actual sum by removing elements [i-1][j] and [i][j-1],
// but adding elements [i-1][j-1] because we subtracted it twice.
//
class NumMatrix {
  private int[][] sums;

  public NumMatrix(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      this.sums = new int[1][1];
    } else {
      this.sums = new int[matrix.length + 1][matrix[0].length + 1];
    }
    compute(matrix, sums);
  }

  private void compute(int[][] matrix, int[][] sums) {
    for (int i = 1; i < sums.length; i++) {
      for (int j = 1; j < sums[0].length; j++) {
        sums[i][j] = matrix[i-1][j-1] + sums[i-1][j] + sums[i][j-1] - sums[i-1][j-1];
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    return sums[row2 + 1][col2 + 1] - sums[row1][col2 + 1] - sums[row2 + 1][col1] + sums[row1][col1];
  }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
