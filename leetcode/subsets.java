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
