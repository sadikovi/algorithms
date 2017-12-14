// Solution runs in O(n^2) time and uses O(n) space
// Beats 47.67% of submissions
class Solution {
  public int integerBreak(int n) {
    int max = 0;
    int[] memo = new int[n+1];
    for (int i = 1; i < n; i++) {
      max = Math.max(max, helper(n - i, memo) * i);
    }
    return max;
  }

  private int helper(int n, int[] memo) {
    if (n <= 1) return 1;
    if (memo[n] > 0) return memo[n];
    int product = 1;
    for (int i = 1; i <= n; i++) {
      product = Math.max(product, helper(n - i, memo) * i);
    }
    memo[n] = product;
    return product;
  }
}

// DP solution O(n^2) time and O(n) space
class Solution {
  public int integerBreak(int n) {
    int[] dp = new int[n+2];
    dp[0] = 1; dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      for (int j = 1; j < i; j++) {
        dp[i] = Math.max(dp[i], Math.max(dp[j], j) * Math.max(dp[i-j], i-j));
      }
    }
    return dp[n];
  }
}
