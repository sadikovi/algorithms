// Solve sudoku algorithm using backtracking and boolean array to track choices made at
// each step. Runtime 16ms, beats 81.12% of submitted solutions.
public class Solution {
  // return set of available numbers on sudoku board
  // each number is 0-based index, e.g. '1' -> 0, '5' -> 4, '9' -> 8
  // at the beginning all numbers are marked as 'false', which means
  // they are available
  // to get index use (char - 49), to reconstruct number use (index + '1')
  private boolean[] nums() {
    return new boolean[9];
  }

  // return minimum index for sub-box based on index of element
  // works for both rows and columns, since they are identical in terms
  // of traversal order
  // sub-boxes are: [(0), 1, 2] [(3), 4, 5] [(6), 7, 8], returned element
  // is marked by (.), the same in vertical direction
  private int boxMin(int index) {
    if (index / 3 == 0) return 0;
    if (index / 6 == 0) return 3;
    return 6;
  }

  // return maximum index for sub-box based on index of element
  // sub-boxes are: [0, 1, (2)] [3, 4, (5)] [6, 7, (8)]
  // returned element is marked by (.)
  private int boxMax(int index) {
    if (index / 3 == 0) return 2;
    if (index / 6 == 0) return 5;
    return 8;
  }

  // using boolean array of numbers for sudoku board, mark numbers that are already
  // used based on sudoku rules, meaning, that no duplicate numbers within row/column/sub-box
  // numbers that are already used are marked as 'true', available numbers are 'false'
  private boolean[] choices(char[][] board, int i, int j) {
    boolean[] set = nums();
    // search row
    for (int p = 0; p < 9; p++) {
      if (board[i][p] != '.') set[board[i][p] - 49] = true;
    }
    // search column
    for (int p = 0; p < 9; p++) {
      if (board[p][j] != '.') set[board[p][j] - 49] = true;
    }
    // search sub-box
    for (int p = boxMin(i); p <= boxMax(i); p++) {
      for (int q = boxMin(j); q <= boxMax(j); q++) {
        if (board[p][q] != '.') set[board[p][q] - 49] = true;
      }
    }
    return set;
  }

  // recursive call to solve one element of a board
  // uses backtracking to correct value
  private boolean solve(char[][] board) {
    int p = -1;
    int q = -1;
    // search for empty cell on sudoku board
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == '.') {
          p = i;
          q = j;
          break;
        }
      }
    }

    // if cell found, select valid numbers that marked as 'false' in array
    // if all values are marked as 'true', it means that choice made on previous
    // iteration is wrong, so we return 'false' to previous stack trace
    // if there are many choices, we try them in the loop
    if (p >= 0 && q >= 0) {
      boolean[] choices = choices(board, p, q);
      for (int y = 0; y < choices.length; y++) {
        if (!choices[y]) {
          board[p][q] = (char) ('1' + y);
          // board at this step is solved, return true
          if (solve(board)) return true;
        }
      }
      board[p][q] = '.';
      return false;
    } else {
      // no empty cell found - return true meaning board is solved
      return true;
    }
  }

  public void solveSudoku(char[][] board) {
    solve(board);
  }
}
