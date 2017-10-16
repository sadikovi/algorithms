// Permutation problem, similar to permutation of strings. Runs in O(n!), where n is array length,
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

// Slightly faster solution (based on Leetcode runtime distribution, beats 67.76%)
// We use only one index to indicate current position, everything to the left has already been
// permuted, to the right - yet to be processed.
class Solution {
  public List<List<Integer>> permute(int[] nums) {
    return permute(nums, nums.length - 1);
  }

  private List<List<Integer>> permute(int[] nums, int pos) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (pos == 0 && pos < nums.length) {
      List<Integer> tmp = new ArrayList<Integer>();
      tmp.add(nums[pos]);
      res.add(tmp);
    } else if (pos < nums.length) {
      List<List<Integer>> arr = permute(nums, pos - 1);
      for (List<Integer> t : arr) {
        for (int i = 0; i <= t.size(); i++) {
          List<Integer> cp = new ArrayList<Integer>(t);
          cp.add(i, nums[pos]);
          res.add(cp);
        }
      }
    }
    return res;
  }
}
