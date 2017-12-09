// Solution runs in O(log N) on average and O(N) is the worst case, where N is the length of the
// array, because inner loop might scan the entire array to find closest different value,
// e.g. [3,1,3,3,...,3].
//
// This solution is different from "find-minimum-in-rotated-sorted-array" in one line:
// When we check `if (nums[start] <= nums[end]) return nums[start];`, this has been replaced with
// while loop, because non-unique numbers can wrap, e.g. [3,1,...,3]. So when end < start, it means
// that nums[start] == nums[end], otherwise we find our nums[end] > nums[start].
class Solution {
  public int findMin(int[] nums) {
    int start = 0, end = nums.length - 1;
    while (start <= end) {
      while (start <= end && nums[start] == nums[end]) --end;
      if (end < start || nums[start] < nums[end]) return nums[start];
      int mid = (start + end) / 2;
      if (nums[mid] >= nums[start]) {
        start = mid + 1;
      } else {
        end = mid;
      }
    }
    return -1;
  }
}
