// Solution runs in O(n^2) space
// Time complexity is either exponential or O(n^3).
// This is fairly naive backtracking algorithm, beats 69.64% of submissions
class Solution {
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> res = new ArrayList<List<String>>();
    char[][] queens = new char[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        queens[i][j] = '.';
      }
    }
    int[][] board = new int[n][n];
    solve(queens, board, 0, n, n, res);
    return res;
  }

  private void solve(char[][] queens, int[][] board, int row, int n, int left, List<List<String>> res) {
    if (left == 0) {
      List<String> ans = new ArrayList<String>();
      for (int i = 0; i < n; i++) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < n; j++) {
          sb.append(queens[i][j]);
        }
        ans.add(sb.toString());
      }
      res.add(ans);
    } else {
      for (int j = 0; j < n; j++) {
        if (board[row][j] == 0) {
          queens[row][j] = 'Q';
          mark(board, n, row, j);
          solve(queens, board, row+1, n, left-1, res);
          queens[row][j] = '.';
          unmark(board, n, row, j);
        }
      }
    }
  }

  private void mark(int[][] board, int n, int p, int q) {
    // mark row and column
    for (int i = 0; i < n; i++) board[i][q]++;
    for (int j = 0; j < n; j++) board[p][j]++;
    // mark diagonals
    for (int i = p, j = q; i < n && j < n; i++, j++) board[i][j]++;
    for (int i = p, j = q; i >= 0 && j < n; i--, j++) board[i][j]++;
    for (int i = p, j = q; i < n && j >= 0; i++, j--) board[i][j]++;
    for (int i = p, j = q; i >= 0 && j >= 0; i--, j--) board[i][j]++;
  }

  private void unmark(int[][] board, int n, int p, int q) {
    // unmark row and column
    for (int i = 0; i < n; i++) board[i][q]--;
    for (int j = 0; j < n; j++) board[p][j]--;
    // unmark diagonals
    for (int i = p, j = q; i < n && j < n; i++, j++) board[i][j]--;
    for (int i = p, j = q; i >= 0 && j < n; i--, j++) board[i][j]--;
    for (int i = p, j = q; i < n && j >= 0; i++, j--) board[i][j]--;
    for (int i = p, j = q; i >= 0 && j >= 0; i--, j--) board[i][j]--;
  }
}
