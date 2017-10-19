// Simple (naive) solution to use additional storage to keep track of seen numbers.
// time = O(n), space = O(n)
public class Solution {
  public int findDuplicate(int[] nums) {
    boolean[] set = new boolean[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      if (set[nums[i]]) return nums[i];
      set[nums[i]] = true;
    }
    return -1;
  }
}

// Use similar technique of two pointers like for linked list cycle.
// Create two pointers, slow and fast, slow has a step of 1, and fast has a step of 2. When they
// meet there is a cycle (must exist by problem definition). Now we need to find actual duplicate
// number. Similar to linked list cycle, reset fast pointer to the beginning, and iterate both
// pointers until they meet. Once they meet - duplicate number is found, return either of them.
// time = O(n), space = O(1)
public class Solution {
  public int findDuplicate(int[] nums) {
    int slow = nums[0], fast = nums[0];
    while (true) {
      slow = nums[slow];
      fast = nums[nums[fast]];
      if (slow == fast) {
        fast = nums[0];
        while (slow != fast) {
          slow = nums[slow];
          fast = nums[fast];
        }
        return slow;
      }
    }
  }
}

// Another technique that exploits the fact that numbers start from 1 to n and array has length of
// n + 1. Each number will correspond to it's position, meaning that n number will be at nth
// position in array (n + 1 length). We swap elements until new coming element updates position
// where element already equals index - this means duplicate.
// Solution runs in O(n) time and O(1) space
class Solution {
  public int findDuplicate(int[] nums) {
    // array has length >= 2
    int curr = nums[0];
    // this is basically while (true), but we will always terminate,
    // because there is always a duplicate
    while (curr > 0) {
      if (nums[curr] != curr) {
        int tmp = nums[curr];
        nums[curr] = curr;
        curr = tmp;
      } else {
        return curr;
      }
    }
    return -1;
  }
}
