public class Solution {
  public boolean canPartition(int[] nums) {
    int sum = 0;
    for (int n : nums)
      sum += n;
    if (sum % 2 != 0) return false;
    return subsetSum(nums, sum / 2);
  }

  public boolean subsetSum(int[] nums, int target) {
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;
    for (int num : nums) {
      for (int partialSum = target; partialSum >= num; partialSum--) {
        dp[partialSum] = dp[partialSum] || dp[partialSum - num];
      }
    }
    return dp[target];
  }
}
