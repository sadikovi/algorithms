public class Solution {
  // We store new state as part of the value as second bit
  // Method 'state' returns old value as n & 1, update state is n | (1|0) << 1
  // Final board update is n >> 1 to get new state
  public void gameOfLife(int[][] board) {
    if (board.length == 0 || board[0].length == 0) return;
    int n = board.length;
    int m = board[0].length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        board[i][j] |= updateState(board, n, m, i, j) << 1;
      }
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        board[i][j] = board[i][j] >> 1;
      }
    }
  }

  private int state(int[][] board, int rows, int cols, int i, int j) {
    if (i < 0 || j < 0 || i >= rows || j >= cols) return 0;
    return board[i][j] & 1;
  }

  private int updateState(int[][] board, int rows, int cols, int i, int j) {
    int curr = state(board, rows, cols, i, j);
    int live =
      state(board, rows, cols, i - 1, j - 1) +
      state(board, rows, cols, i - 1, j) +
      state(board, rows, cols, i - 1, j + 1) +
      state(board, rows, cols, i, j + 1) +
      state(board, rows, cols, i + 1, j + 1) +
      state(board, rows, cols, i + 1, j) +
      state(board, rows, cols, i + 1, j - 1) +
      state(board, rows, cols, i, j - 1);
    if (curr == 1 && live < 2) return 0;
    if (curr == 1 && live > 3) return 0;
    if (curr == 0 && live == 3) return 1;
    return curr;
  }
}
