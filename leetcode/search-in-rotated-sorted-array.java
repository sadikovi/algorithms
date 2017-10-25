// Solution runs in O(log n) time and O(1) space.
// Idea is finding actual min index first, and run binary search based on indices with
// that min offset, but look up element based on actual index ((index + offset) % nums.length).
class Solution {
  public int search(int[] nums, int target) {
    int start = 0, end = nums.length - 1;
    // Need to consider several approaches.
    int min = min(nums);
    if (min < 0) return -1;
    int i = min, j = min + nums.length - 1;
    while (i <= j) {
      int mid = (i + j) / 2; // sorted offset
      int m = mid % nums.length; // actual offset
      if (nums[m] == target) return m;
      if (nums[m] > target) {
        j = mid - 1;
      } else {
        i = mid + 1;
      }
    }
    return -1;
  }

  private int min(int[] nums) {
    int i = 0, j = nums.length - 1;
    while (i <= j) {
      if (i == j) return i;
      int mid = (i + j)/2;
      if (nums[mid] < nums[j]) {
        j = mid;
      } else {
        i = mid + 1;
      }
    }
    return -1;
  }
}
