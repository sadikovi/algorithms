// Solution runs in O(log n) time and O(1) space.
class Solution {
  public int findMin(int[] nums) {
    if (nums == null || nums.length == 0) return -1;
    int start = 0, end = nums.length - 1;
    while (start <= end) {
      int m = (start + end) / 2;
      if (nums[m] < nums[end]) {
        end = m;
      } else {
        start = m + 1;
      }
    }
    return nums[end];
  }
}
