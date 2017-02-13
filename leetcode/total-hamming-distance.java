// Naive solution, runs in O(k * N^2), where k is size of integer and N is length of array
// Uses O(1) space
public class Solution {
  public int totalHammingDistance(int[] nums) {
    int total = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        total += distance(nums[i], nums[j]);
      }
    }
    return total;
  }

  private int distance(int a, int b) {
    int c = a ^ b, cnt = 0;
    while (c > 0) {
      if ((c & 1) > 0) cnt++;
      c >>>= 1;
    }
    return cnt;
  }
}

// Better approach, runs in O(k * N), where k is size of integer (30) and N is length of array
// Uses O(1) space
public class Solution {
  public int totalHammingDistance(int[] nums) {
    int total = 0, shift = 30;
    while (shift >= 0) {
      int ones = 0;
      for (int num : nums) {
        if ((num & (1 << shift)) > 0) ones++;
      }
      shift--;
      int zeros = nums.length - ones;
      total += ones * zeros;
    }
    return total;
  }
}

// Better approach (faster than previous one), runs in O(N) and uses O(1) space
public class Solution {
  public int totalHammingDistance(int[] nums) {
    int total = 0, shift = 30;
    while (shift >= 0) {
      int ones = 0;
      for (int i = 0; i < nums.length; i++) {
        ones += (nums[i]) & 1;
        nums[i] >>= 1;
      }
      shift--;
      total += ones * (nums.length - ones);
    }
    return total;
  }
}
