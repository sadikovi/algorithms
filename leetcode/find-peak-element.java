// Naive O(n) solution to find peak element. Simply iterate over elements and check if any of
// them match condition {nums[i-1] < nums[i] > nums[i+1]}.
class Solution {
  public int findPeakElement(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      long prev = (i == 0) ? Long.MIN_VALUE : nums[i - 1];
      long next = (i == nums.length - 1) ? Long.MIN_VALUE : nums[i + 1];
      if (nums[i] > prev && nums[i] > next) return i;
    }
    return -1;
  }
}

// O(log n) solution to find peak element. Idea is selecting mid point and checking condition.
// If mid point passes condition, return index, otherwise check which side is more likely to have
// peak depending on whether or not mid point is greater than left or right.
class Solution {
  public int findPeakElement(int[] nums) {
    int start = 0, end = nums.length - 1;
    while (start <= end) {
      int mid = (start + end) / 2;
      long prev = mid > 0 ? nums[mid - 1] : Long.MIN_VALUE;
      long next = mid < nums.length - 1 ? nums[mid + 1] : Long.MIN_VALUE;
      if (nums[mid] > prev && nums[mid] > next) return mid;
      if (nums[mid] < prev) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return -1;
  }
}
