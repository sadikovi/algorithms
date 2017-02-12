// Classic prefix sums problem, O(n) time and O(n) space complexity on init
// and O(1) time to answer query
public class NumArray {
  private int[] sums;

  public NumArray(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      nums[i] += nums[i - 1];
    }
    sums = nums;
  }

  public int sumRange(int i, int j) {
    return i == 0 ? sums[j] : sums[j] - sums[i - 1];
  }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
