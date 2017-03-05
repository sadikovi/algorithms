// Permutation problem, similar to permutation of strings. Runs in O(2^n), where n is array length,
// similar the one for strings.
// The main gotcha is avoiding array copy for each iteration, that is why we propagate indices
// 'start' and 'end' for each recursion.
public class Solution {
  public List<List<Integer>> permute(int[] nums) {
    return permute(nums, 0, nums.length - 1);
  }

  private List<List<Integer>> permute(int[] nums, int start, int end) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (start == end) {
      List<Integer> list = new ArrayList<Integer>();
      list.add(nums[start]);
      res.add(list);
    } else if (start < end) {
      List<List<Integer>> out = permute(nums, start + 1, end);
      for (List<Integer> arr : out) {
        for (int i = 0; i <= arr.size(); i++) {
          List<Integer> ext = new ArrayList<Integer>();
          for (int p = 0; p < i; p++) ext.add(arr.get(p));
          ext.add(nums[start]);
          for (int p = i; p < arr.size(); p++) ext.add(arr.get(p));
          res.add(ext);
        }
      }
    }
    return res;
  }
}
