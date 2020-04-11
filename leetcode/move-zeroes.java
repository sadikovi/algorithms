// Time complexity is O(n), space complexity is O(1).
class Solution {
  public void moveZeroes(int[] nums) {
    int len = 0; // keeps track of number of non-zero elements at the top of the array

    // Advance to the first zero element
    while (len < nums.length && nums[len] != 0) len++;
    if (len == nums.length) return; // no zero elements, return

    // find the first non-zero element and swap it with a zero element
    // len points to the first zero element
    int non_zero = len + 1;
    while (non_zero < nums.length) {
      while (non_zero < nums.length && nums[non_zero] == 0) non_zero++;
      if (non_zero == nums.length) return; // no non-zero elements, return
      nums[len++] = nums[non_zero];
      nums[non_zero++] = 0;
    }
  }
}
