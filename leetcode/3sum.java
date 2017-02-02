public class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> list = new ArrayList<List<Integer>>();
    for (int i = 0; i < nums.length - 2; i++) {
      if (i == 0 || nums[i] != nums[i - 1]) {
        int target = -nums[i];
        int start = i + 1;
        int end = nums.length - 1;
        while (start < end) {
          if (nums[start] + nums[end] == target) {
            List<Integer> l = new ArrayList<Integer>();
            l.add(nums[i]);
            l.add(nums[start]);
            l.add(nums[end]);
            list.add(l);
            while (start < end && nums[start] == nums[start + 1]) start++;
            while (start < end && nums[end] == nums[end - 1]) end--;
            start++;
            end--;
          } else if (nums[start] + nums[end] > target) {
            end--;
          } else {
            start++;
          }
        }
      }
    }
    return list;
  }
}
