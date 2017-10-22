// Dynamic programming solution, runs in O(n^2) time and O(n) space.
// Idea is computing min based on previous i-1 elements, only searching half of the interval,
// because dp[i - j] + dp[j] = dp[i] or previous dp[i], whichever is smaller.
// Passes the judge, but this is very slow solution.
class Solution {
  public int numSquares(int n) {
    int[] dp = new int[n + 1];
    int k = 1;
    while (k * k > 0 && k * k <= n) {
      if (k * k == n) return 1;
      dp[k * k] = 1;
      ++k;
    }
    for (int i = 2; i <= n; i++) {
      if (dp[i] == 1) continue;
      dp[i] = Integer.MAX_VALUE;
      for (int j = 1; j <= i/2; j++) {
        dp[i] = Math.min(dp[j] + dp[i - j], dp[i]);
      }
    }
    return dp[n];
  }
}

// Fast dynamic programming solution, runs in O(n sqrt(n)) time and O(n) space.
// Idea is that ith element can be computed as dp[some perfect square] + dp[i - perfect square] + 1.
class Solution {
  public int numSquares(int n) {
    if (n <= 0) return 0;
    int[] dp = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      dp[i] = Integer.MAX_VALUE;
      for (int j = 1; j*j <= i; j++) {
        dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
      }
    }
    return dp[n];
  }
}
