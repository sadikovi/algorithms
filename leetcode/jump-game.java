// Naive recursive solution.
// Depending on array values, branching can be huge, so runtime is O(2^n) and O(n) space, because
// in the worst case recursion tree height equals to number of elements in the array: [1,1,1,...].
class Solution {
  public boolean canJump(int[] nums) {
    return jump(nums, 0);
  }

  private boolean jump(int[] nums, int i) {
    if (i >= nums.length) return false;
    if (i == nums.length - 1) return true;
    for (int j = 1; j <= nums[i]; j++) {
      if (jump(nums, i + j)) return true;
    }
    return false;
  }
}

// Dynamic programming solution (passes).
// Runs in O(n^2) time and O(1) space.
// Idea is determining if you can reach current position (index + step >= current) from any
// other position (index).
class Solution {
  public boolean canJump(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      int j = i - 1;
      while (j >= 0) {
        if (j + nums[j] >= i) break;
        --j;
      }
      if (j < 0) return false;
    }
    return true;
  }
}
