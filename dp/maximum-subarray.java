public class Solution {
  public int maxSubArray(int[] nums) {
    int max = Integer.MIN_VALUE, curr = 0;
    for (int i = 0; i < nums.length; i++) {
      curr += nums[i];
      max = Math.max(max, curr);
      if (curr < 0) curr = 0;
    }
    return max;
  }
}