public class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> sets = new ArrayList<List<Integer>>();
    sets.add(new ArrayList<Integer>());

    List<List<Integer>> tmp;
    for (int i = 0; i < nums.length; i++) {
      tmp = new ArrayList<List<Integer>>();
      for (List<Integer> set : sets) {
        List<Integer> clone = new ArrayList<Integer>(set);
        clone.add(nums[i]);
        tmp.add(clone);
      }
      sets.addAll(tmp);
    }
    return sets;
  }
}

// Recursive solution for the problem
public class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    return subsets(nums, nums.length - 1);
  }

  private List<List<Integer>> subsets(int[] nums, int end) {
    List<List<Integer>> all = new ArrayList<List<Integer>>();
    if (end < 0) {
      all.add(new ArrayList<Integer>());
    } else {
      List<List<Integer>> out = subsets(nums, end - 1);
      all.addAll(out);
      for (List<Integer> l : out) {
        List<Integer> copy = new ArrayList<Integer>(l);
        copy.add(nums[end]);
        all.add(copy);
      }
    }
    return all;
  }
}
