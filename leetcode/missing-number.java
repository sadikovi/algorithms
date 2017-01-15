public class Solution {
  public int missingNumber(int[] nums) {
    // to prevent overflow
    int sum = (int) ((nums.length + 1L) * nums.length / 2);
    for (int i = 0; i < nums.length; i++) {
      sum -= nums[i];
    }
    return sum;
  }
}
