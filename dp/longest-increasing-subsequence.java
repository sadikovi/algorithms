// DP solution O(n^2)
public class Solution {
  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int[] dp = new int[nums.length];
    dp[0] = 1; // initial length
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i])
          dp[i] = Math.max(dp[j], dp[i]);
      }
      dp[i] += 1;
    }
    int max = 0;
    for (int i : dp)
      max = Math.max(max, i);
    return max;
  }
}

// DP solution O(nlgn), taken from here:
// https://discuss.leetcode.com/topic/28738/java-python-binary-search-o-nlogn-time-with-explanation
public class Solution {
  public int lengthOfLIS(int[] nums) {
    int size = 0;
    int[] tails = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      int l = 0, h = size;
      while (l != h) {
        int mid = (l + h) / 2;
        if (tails[mid] < nums[i])
          l = mid + 1;
        else
          h = mid;
      }
      tails[l] = nums[i];
      size = Math.max(l + 1, size);
    }
    return size;
  }
}
