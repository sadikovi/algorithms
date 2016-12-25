public class Solution {
  // runtime is O(2^n) generating permutations (all distinct)
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    // sort sequence in ascending order, so we can detect duplicates
    Arrays.sort(nums);
    List<List<Integer>> sets = new ArrayList<List<Integer>>();
    // add empty set
    sets.add(new ArrayList<Integer>());
    List<List<Integer>> tmp = null;
    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i - 1] == nums[i]) {
        // same element, add elements to temp and reset temp
        List<List<Integer>> bu = tmp;
        tmp = new ArrayList<List<Integer>>();
        for (List<Integer> s : bu) {
          List<Integer> cp = new ArrayList<Integer>(s);
          cp.add(nums[i]);
          tmp.add(cp);
        }
      } else {
        // different element, add elements to 'sets' and reset 'temp'
        tmp = new ArrayList<List<Integer>>();
        for (List<Integer> s : sets) {
          List<Integer> cp = new ArrayList<Integer>(s);
          cp.add(nums[i]);
          tmp.add(cp);
        }
      }
      sets.addAll(tmp);
    }
    return sets;
  }
}

// Alternative solution
public class Solution {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> sets = new ArrayList<List<Integer>>();
    sets.add(new ArrayList<Integer>());
    Arrays.sort(nums);
    int i = 0;
    while (i < nums.length) {
      int count = 0;
      while ((count + i) < nums.length && nums[count + i] == nums[i]) {
        count++;
      }
      List<List<Integer>> temp = new ArrayList<List<Integer>>();
      for (List<Integer> s : sets) {
        List<Integer> cp = new ArrayList<Integer>(s);
        for (int k = 0; k < count; k++) {
          cp.add(nums[i]);
          temp.add(cp);
          cp = new ArrayList<Integer>(cp);
        }
      }
      sets.addAll(temp);
      i += count;
    }
    return sets;
  }
}
