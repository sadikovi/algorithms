// Solution runs in O(m * n) time and uses O(m + n) space (beats 83.93%)
// Idea is keeping bitsets for each row and column and map "0" element to "true" for both row and
// column bitsets (boolean arrays).
class Solution {
  public void setZeroes(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return;

    boolean[] rows = new boolean[matrix.length];
    boolean[] cols = new boolean[matrix[0].length];

    // collect zero positions
    for (int i = 0; i < rows.length; i++) {
      for (int j = 0; j < cols.length; j++) {
        if (matrix[i][j] == 0) {
          rows[i] = true;
          cols[j] = true;
        }
      }
    }

    // set rows and columns to zero
    for (int i = 0; i < rows.length; i++) {
      if (rows[i]) {
        for (int j = 0; j < cols.length; j++) {
          matrix[i][j] = 0;
        }
      }
    }
    for (int j = 0; j < cols.length; j++) {
      if (cols[j]) {
        for (int i = 0; i < rows.length; i++) {
          matrix[i][j] = 0;
        }
      }
    }
  }
}
