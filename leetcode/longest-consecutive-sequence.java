// Solution runs in O(n * log(n)) time and O(n) space (because of sorting).
// Interestingly, beats 95.19% of submissions.
class Solution {
  public int longestConsecutive(int[] nums) {
    if (nums.length == 0) return 0;
    Arrays.sort(nums);
    int len = 1, tmp = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] - nums[i-1] == 1) {
        ++tmp;
      } else if (nums[i] != nums[i-1]) {
        tmp = 1;
      }
      len = Math.max(len, tmp);
    }
    return len;
  }
}

// Solution can be viewed as similar to graph "connected components" problem.
// Solution runs in O(n) time, assuming that set operations take O(1) and queue dequeue and
// enqueue operations take O(1) time. It takes O(n) space to store set of elements and
// queue of O(n) elements when all elements are consecutive.
class Solution {
  public int longestConsecutive(int[] nums) {
    HashSet<Integer> set = new HashSet<Integer>();
    for (int i = 0; i < nums.length; i++) {
      set.add(nums[i]);
    }

    LinkedList<Integer> queue = new LinkedList<Integer>();
    int len = 0;
    while (!set.isEmpty()) {
      int elem = set.iterator().next();
      queue.add(elem);
      int curr_len = 0;
      while (!queue.isEmpty()) {
        int tmp = queue.remove();
        set.remove(tmp);
        if (set.contains(tmp + 1)) queue.add(tmp + 1);
        if (set.contains(tmp - 1)) queue.add(tmp - 1);
        ++curr_len;
      }
      len = Math.max(curr_len, len);
    }
    return len;
  }
}
