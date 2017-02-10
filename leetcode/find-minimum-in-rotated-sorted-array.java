public class Solution {
  public int findMin(int[] nums) {
    if (nums == null || nums.length == 0) return -1;
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      if (nums[low] <= nums[high])
        return nums[low];
      int mid = (low + high) / 2;
      if (nums[mid] >= nums[low]) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    return nums[low];
  }
}
