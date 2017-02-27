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
