// Solution runs in O(n) time and updates the input array in-place.
// Space complexity is O(1).
//
// Test cases:
//
// [1,1,1,2,2,3]
// [0,0,1,1,1,1,2,3,3]
// [0]
// [0,0]
// [0,1]
// [0,1,2,3,4,5,6]
// [1,1,1,1,1,1,2,3,4,4]
// [1,1,1,1,1]

public class Solution {
  public int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int index = 0, counter = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > nums[i - 1]) {
        nums[++index] = nums[i];
        counter = 1;
      } else if (counter < 2) {
        // condition implies that nums[i] == nums[i - 1]
        nums[++index] = nums[i];
        counter++;
      }
    }
    return ++index;
  }
}

// Another solution, similar logic, keep in mind that we update the counters after the check.
class Solution {
  public int removeDuplicates(int[] nums) {
    int idx = 0, dup = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != nums[idx]) {
        nums[++idx] = nums[i];
        dup = 0;
      } else if (nums[i] == nums[idx] && dup < 1) {
        nums[++idx] = nums[i];
        dup++;
      }
    }

    return idx + 1;
  }
}
