// Slow solution, based on prefix sums. Runs in O(n^3) time and O(n) space.
class Solution {
  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    int[] sums = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      sums[i+1] = sums[i] + nums[i];
    }
    int[] res = new int[3];
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i+k; j < nums.length; j++) {
        for (int p = j+k; p < nums.length - k + 1; p++) {
          int tmp = sums[i+k] - sums[i] + sums[j+k] - sums[j] + sums[p+k] - sums[p];
          if (tmp > sum) {
            res[0] = i;
            res[1] = j;
            res[2] = p;
            sum = tmp;
          }
        }
      }
    }
    return res;
  }
}

// Fast solution based on dynamic programming. Runs in O(n) time and O(n) space.
//
// Idea is computing prefix sums for left and right intervals and then going through all possible
// combinations for middle interval. Need to be very careful with indices.
// This is my adaptation of the code.
//
// Full explanation:
// https://discuss.leetcode.com/topic/105577/c-java-dp-with-explanation-o-n
//
class Solution {
  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    // prefix sums
    int[] sums = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      sums[i+1] = sums[i] + nums[i];
    }
    int[] res = new int[3];
    int[] posLeft = new int[nums.length];
    int[] posRight = new int[nums.length];

    // build windows of length k from left
    int sum = sums[k] - sums[0];
    for (int i = k; i < nums.length; i++) {
      if (sums[i+1] - sums[i+1-k] > sum) {
        sum = sums[i+1] - sums[i+1-k];
        posLeft[i] = i+1-k;
      } else {
        posLeft[i] = posLeft[i-1];
      }
    }

    // build windows of length k from right
    posRight[nums.length-k] = nums.length-k;
    sum = sums[nums.length] - sums[nums.length-k];
    for (int i = nums.length-k-1; i >= 0; i--) {
      if (sums[i+k] - sums[i] >= sum) {
        posRight[i] = i;
        sum = sums[i+k] - sums[i];
      } else {
        posRight[i] = posRight[i+1];
      }
    }

    // test all possible middle intervals
    int max = 0;
    for (int i = k; i <= nums.length - 2*k; i++) {
      int left = posLeft[i-1];
      int right = posRight[i+k];
      int total = sums[i+k] - sums[i] + sums[left+k] - sums[left] + sums[right+k] - sums[right];
      if (total > max) {
        max = total;
        res[0] = left;
        res[1] = i;
        res[2] = right;
      }
    }

    return res;
  }
}
