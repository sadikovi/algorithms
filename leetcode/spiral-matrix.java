// Solution runs in O(n * m) time and O(n) space for output result (O(min(n, m)) recursion)
// Main gotcha is to handle matrices/states that are row or column vectors - we just add those
// separately.
class Solution {
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> res = new ArrayList<Integer>();
    if (matrix.length == 0 || matrix[0].length == 0) return res;
    spiral(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1, res);
    return res;
  }

  private void spiral(int[][] matrix, int rs, int re, int cs, int ce, List<Integer> res) {
    if (rs > re || cs > ce) return;
    if (cs == ce) {
      for (int i = rs; i <= re; i++) res.add(matrix[i][cs]);
    } else if (rs == re) {
      for (int j = cs; j <= ce; j++) res.add(matrix[rs][j]);
    } else {
      for (int j = cs; j < ce; j++) res.add(matrix[rs][j]);
      for (int i = rs; i < re; i++) res.add(matrix[i][ce]);
      for (int j = ce; j > cs; j--) res.add(matrix[re][j]);
      for (int i = re; i > rs; i--) res.add(matrix[i][cs]);
    }
    spiral(matrix, rs+1, re-1, cs+1, ce-1, res);
  }
}
