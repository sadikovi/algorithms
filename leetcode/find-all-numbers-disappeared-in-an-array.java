public class Solution {
  // Solution takes O(n) time and O(1) space complexity
  public List<Integer> findDisappearedNumbers(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      int index = Math.abs(nums[i]) - 1;
      if (nums[index] > 0) {
        nums[index] = -nums[index];
      }
    }

    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0) {
        list.add(i + 1);
      }
    }
    return list;
  }

  // Solution takes O(n) time and O(n) space complexity
  public List<Integer> findDisappearedNumbers2(int[] nums) {
    boolean[] arr = new boolean[nums.length];
    for (int i = 0; i < nums.length; i++) {
      arr[nums[i] - 1] = true;
    }

    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < arr.length; i++) {
      if (!arr[i]) {
        list.add(i + 1);
      }
    }
    return list;
  }
}
