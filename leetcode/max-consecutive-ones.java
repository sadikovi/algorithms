public class Solution {
  public int findMaxConsecutiveOnes(int[] nums) {
    // solve as max subarray problem
    int max = 0, tmp = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        max = Math.max(max, tmp);
        tmp = 0;
      } else {
        tmp++;
      }
    }
    return Math.max(max, tmp);
  }
}
