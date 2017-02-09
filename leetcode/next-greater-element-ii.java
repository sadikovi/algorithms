// Naive solution, runs in O(n^2) time
public class Solution {
  public int[] nextGreaterElements(int[] nums) {
    int[] res = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      int index = (i + 1) % nums.length;
      while (index != i && nums[i] >= nums[index]) {
        index = (index + 1) % nums.length;
      }
      res[i] = index == i ? -1 : nums[index];
    }
    return res;
  }
}

// Solution from leetcode, runs in O(n) time using stack
// see link: https://discuss.leetcode.com/topic/77881/typical-ways-to-solve-circular-array-problems-java-solution
public class Solution {
  public int[] nextGreaterElements(int[] nums) {
    if (nums == null || nums.length == 0) return nums;
    Stack<Integer> stack = new Stack<Integer>();
    int[] res = new int[nums.length];

    for (int i = nums.length - 1; i >= 0; i--) {
      stack.push(i);
    }

    for (int i = nums.length - 1; i >= 0; i--) {
      res[i] = -1;
      while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
        stack.pop();
      }

      if (!stack.isEmpty()) {
        res[i] = nums[stack.peek()];
      }

      stack.push(i);
    }
    return res;
  }
}
