// DP solution that uses O(n) time and O(1) space
// similar to counting-steps problem
public class Solution {
  public int rob(int[] nums) {
    int a = 0, b = 0;
    for (int i = 0; i < nums.length; i++) {
      int c = Math.max(a + nums[i], b);
      a = b;
      b = c;
    }
    return b;
  }
}

// DP solution that uses O(n) time and O(n) space
// classic dynamic programming approach with array as cache
public class Solution {
  public int rob(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n + 2];
    for (int i = 2; i < dp.length; i++) {
      dp[i] = Math.max(dp[i - 2] + nums[i - 2], dp[i - 1]);
    }
    return dp[dp.length - 1];
  }
}
