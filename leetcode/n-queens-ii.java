// Solution runs in exponential time (?) and O(n^2) space (recursive call stack + boolean array of
// length "n" at each level). Again, I am not sure about running time here.
// Solution beats 72.61% of all submissions.
//
// Idea is rather than keeping board information we only keep a column for current position "q".
// Then based on previous positions we compute all cells in that column that would be covered by
// previous (to the left) queens (up - upper corner diagonal, down - lower corner diagonal).
// Then we recursively backtrack and test every available combination.
class Solution {
  public int totalNQueens(int n) {
    // if (n <= 0) return 0; -- leetcode judge does not handle this case
    return solve(0, new int[n]);
  }

  private int solve(int q, int[] queens) {
    if (q == queens.length) return 1;
    // mark taken positions
    boolean[] taken = new boolean[queens.length];
    for (int i = 0; i < q; i++) {
      taken[queens[i]] = true;
      int up = queens[i] - (q - i);
      int down = queens[i] + (q - i);
      if (up >= 0 && up < taken.length) taken[up] = true;
      if (down >= 0 && down < taken.length) taken[down] = true;
    }

    int solutions = 0;
    for (int j = 0; j < taken.length; j++) {
      if (!taken[j]) {
        queens[q] = j;
        solutions += solve(q+1, queens);
        queens[q] = Integer.MIN_VALUE;
      }
    }
    return solutions;
  }
}
