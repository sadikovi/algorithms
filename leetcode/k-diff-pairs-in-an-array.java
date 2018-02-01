// Solution runs in O(NlogN) time and O(n) space.
// Beats 97.18% of all submissions.
//
// Idea is to sort elements and search for any counterpart on the right side of the array, because
// smaller counterpart would have been already discovered on previous iterations.
// We also skip duplicates to remove duplicated pairs that start with the same number.
//
class Solution {
  public int findPairs(int[] nums, int k) {
    if (k < 0) return 0;
    Arrays.sort(nums);
    int count = 0, i = 0;
    while (i < nums.length) {
      int pos = search(nums, i+1, nums.length - 1, nums[i] + k);
      if (pos != -1) {
        count++;
      }
      while (i < nums.length - 1 && nums[i] == nums[i+1]) i++;
      i++;
    }
    return count;
  }

  private int search(int[] nums, int start, int end, int t) {
    while (start <= end) {
      int m = (start + end) / 2;
      if (nums[m] == t) return m;
      if (nums[m] > t) {
        end = m - 1;
      } else {
        start = m + 1;
      }
    }
    return -1;
  }
}
