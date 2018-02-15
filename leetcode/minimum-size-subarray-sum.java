// Three solutions to this problem in the reduced time-space complexity:
// O(n^2) => O(n*log(n)) => O(n)

// Solution runs in O(n^2) time and O(n) space
// Based on usage of prefix sums, so sum computation takes O(1)
class Solution {
  public int minSubArrayLen(int s, int[] nums) {
    int len = Integer.MAX_VALUE;
    int[] sums = new int[nums.length + 1];
    for (int i = 1; i < sums.length; i++) {
      sums[i] = sums[i-1] + nums[i-1];
    }
    for (int i = 0; i < nums.length; i++) {
      for (int j = i; j < nums.length; j++) {
        if (sums[j+1] - sums[i] >= s) {
          len = Math.min(len, j - i + 1);
        }
      }
    }
    return (len == Integer.MAX_VALUE) ? 0 : len;
  }
}

// Solution runs in O(n*log(n)) time and O(n) space
// Based on solution above, but instead of performing linear scan to find the right bound, we do
// a binary search on the right side of the array to find element that either results in reaching
// target sum "s" or at least not smaller one (k = i + 1).
class Solution {
  public int minSubArrayLen(int s, int[] nums) {
    int[] sums = new int[nums.length + 1];
    for (int i = 1; i < sums.length; i++) {
      sums[i] = sums[i - 1] + nums[i - 1];
    }

    int len = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
      int k = search(sums, i+1, sums.length - 1, s + sums[i]);
      if (k < sums.length) {
        len = Math.min(len, k - i);
      }
    }
    return len == Integer.MAX_VALUE ? 0 : len;
  }

  private int search(int[] sums, int i, int j, int target) {
    while (i <= j) {
      int mid = (i + j) / 2;
      if (sums[mid] == target) return mid;
      if (sums[mid] > target) {
        j = mid - 1;
      } else {
        i = mid + 1;
      }
    }
    return i;
  }
}

// Solution runs in O(n) time and O(1) space
// Idea is do a linear scan and track the sum as we move along the array. When sum is less than
// target sum we can only increment right index to increase it, if sum is greater then or equal to
// target sum, then we update answer and decrement left index to search for shorter sequences.
class Solution {
  public int minSubArrayLen(int s, int[] nums) {
    if (nums.length == 0) return 0;
    int start = 0, end = 0, sum = nums[0], len = Integer.MAX_VALUE;
    while (start <= end && end < nums.length) {
      if (sum >= s) {
        len = Math.min(len, end - start + 1);
        sum -= nums[start++];
      } else {
        ++end;
        if (end < nums.length) {
            sum += nums[end];
        }
      }
    }
    return len == Integer.MAX_VALUE ? 0 : len;
  }
}
