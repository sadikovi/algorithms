public class Solution {
  public int findTargetSumWays(int[] nums, int S) {
    int sum = 0;
    for (int i : nums)
      sum += i;
    if (sum < S || (sum + S) % 2 > 0) return 0;
    return subsetSum(nums, (S + sum) / 2);
  }

  private int subsetSum(int[] nums, int s) {
    int[] dp = new int[s + 1];
    dp[0] = 1;
    for (int i : nums) {
      for (int j = s; j >= i; j--) {
        dp[j] += dp[j - i];
      }
    }
    return dp[s];
  }
}
