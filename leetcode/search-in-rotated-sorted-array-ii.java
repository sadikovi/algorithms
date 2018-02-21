// Solution runs in O(log n) time on average and O(n) time in the worst-case, uses O(log n) space.
// There are several solutions on leetcode, but I am not sure if we could solve it the same way as
// previous problem, e.g. find mid point and reconstruct array.
class Solution {
  public boolean search(int[] nums, int target) {
    return search(nums, 0, nums.length - 1, target);
  }

  private boolean search(int[] nums, int start, int end, int target) {
    if (start > end) return false;
    int m = (start + end) / 2;
    if (nums[m] == target) return true;
    if (nums[m] > target && target >= nums[start]) {
      return search(nums, start, m - 1, target);
    } else if (nums[m] < target && target <= nums[end]) {
      return search(nums, m + 1, end, target);
    } else {
      return search(nums, start, m - 1, target) || search(nums, m + 1, end, target);
    }
  }
}
