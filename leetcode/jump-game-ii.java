// Leetcode greedy-based solution in O(n) time, see link:
// https://discuss.leetcode.com/topic/28470/concise-o-n-one-loop-java-solution-based-on-greedy
public class Solution {
  public int jump(int[] nums) {
    int jumps = 0, curEnd = 0, curFarthest = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      curFarthest = Math.max(curFarthest, i + nums[i]);
      if (i == curEnd) {
        jumps++;
        curEnd = curFarthest;
      }
    }
    return jumps;
  }
}

// DP solution for the problem, runs in O(n * max)
public class Solution {
  public int jump(int[] nums) {
    int[] dp = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      for (int j = 1; j <= nums[i]; j++) {
        if (i + j < nums.length) {
          if (dp[i + j] == 0) {
            dp[i + j] = dp[i] + 1;
          } else {
            dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
          }
        }
      }
    }
    return dp[nums.length - 1];
  }
}

// Recursive solution for the problem, runs in O(max ^ n)
public class Solution {
  public int jump(int[] nums) {
    int[] min = new int[1];
    recurJump(nums, 0, 0, min);
    return min[0];
  }

  private void recurJump(int[] nums, int i, int jumps, int[] min) {
    if (i < 0 || i >= nums.length) return;
    if (i == nums.length - 1) {
      min[0] = (min[0] == 0) ? jumps : Math.min(jumps, min[0]);
      return;
    }

    for (int j = 1; j <= nums[i]; j++) {
      recurJump(nums, i + j, jumps + 1, min);
    }
  }
}
