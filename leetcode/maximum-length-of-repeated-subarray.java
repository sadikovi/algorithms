// Dynamic programming solution to the problem.
// Time complexity is O(A * B).
// Space complexity is O(A * B) for dp[][] array;
//
// I found it a bit counter-intuitive to use dynamic programming this way, especially when
// updating `len` variable as you compute dp values. However, it kind of makes sense once you print
// dp array - each sequence will stop at certain [i, j] combination, and the transitions only happen
// for matching pairs, otherwise you start with 0. And while this is happening, you keep track of
// maximum length of a subarray.
class Solution {
  public int findLength(int[] A, int[] B) {
    if (A.length == 0 || B.length == 0) return 0;

    int[][] dp = new int[A.length][B.length];

    for (int i = 0; i < A.length; i++) {
      dp[i][0] = A[i] == B[0] ? 1 : 0;
    }

    for (int j = 0; j < B.length; j++) {
      dp[0][j] = A[0] == B[j] ? 1 : 0;
    }

    int len = 0;
    for (int i = 1; i < A.length; i++) {
      for (int j = 1; j < B.length; j++) {
        if (A[i] == B[j]) {
          dp[i][j] = dp[i-1][j-1] + 1;
          len = Math.max(len, dp[i][j]);
        }
      }
    }

    return len;
  }
}
