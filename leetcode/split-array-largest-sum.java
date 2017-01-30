public class Solution {
  /* Recursive solution
  public int splitArray(int[] nums, int m) {
    for (int i = 1; i < nums.length; i++) {
      nums[i] += nums[i - 1];
    }
    return splitArray(nums, 0, nums.length - 1, m);
  }

  private int splitArray(int[] arr, int start, int end, int m) {
    if (m == 0 || arr.length < m) return 0;
    if (m == 1) return (start == 0) ? arr[end] : (arr[end] - arr[start - 1]);
    int minSum = Integer.MAX_VALUE;
    for (int i = start; i <= end - (m - 1); i++) {
      int sum = (start == 0) ? arr[i] : (arr[i] - arr[start - 1]);
      int rightSum = splitArray(arr, i + 1, end, m - 1);
      minSum = Math.min(minSum, Math.max(sum, rightSum));
    }
    return minSum;
  }
  */

  // DP solution, taken from forum
  // https://discuss.leetcode.com/topic/61405/dp-java
  public int splitArray(int[] nums, int m) {
    int L = nums.length;
    int[] S = new int[L + 1];
    S[0] = 0;
    for(int i = 0; i < L; i++)
      S[i + 1] = S[i] + nums[i];

    int[] dp = new int[L];
    for (int i = 0; i < L; i++)
      dp[i] = S[L] - S[i];

    for (int s = 1; s < m; s++) {
      for (int i = 0; i < L-s; i++) {
        dp[i] = Integer.MAX_VALUE;
        for (int j = i + 1; j <= L-s; j++) {
          int t = Math.max(dp[j], S[j] - S[i]);
          if (t <= dp[i])
            dp[i] = t;
          else
            break;
        }
      }
    }

    return dp[0];
  }
}
