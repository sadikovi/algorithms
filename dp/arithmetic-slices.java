public class Solution {
  public int numberOfArithmeticSlices(int[] A) {
    int n = A.length;
    if (n == 0) return 0;
    int[] dp = new int[n];
    dp[n - 1] = Integer.MAX_VALUE;
    for (int i = 1; i < n; i++) {
      dp[i - 1] = A[i] - A[i - 1];
    }

    int total = 0, cnt = 0, len = 1;
    for (int i = 1; i < dp.length; i++) {
      if (dp[i - 1] == dp[i]) {
        cnt += len;
        len++;
      } else {
        total += cnt;
        cnt = 0;
        len = 1;
      }
    }
    return total;
  }
}
