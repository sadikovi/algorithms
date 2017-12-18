// Solution that I came up with, beats 52.01% of submissions.
// I am not quite sure what the runtime is, but I think it is O(2^n), though some branches
// terminate quickly because they exceed n.
class Solution {
  public int minSteps(int n) {
    if (n == 1) return 0;
    int[] min = new int[1];
    min[0] = Integer.MAX_VALUE;
    steps(n, 1, 1, 1, min);
    return min[0];
  }

  private void steps(int n, int current, int copied, int steps, int[] min) {
    if (n == current) {
      min[0] = Math.min(min[0], steps);
    } else if (n > current) {
      steps(n, current + copied, copied, steps + 1, min);
      if (copied < current) {
        steps(n, current, current, steps + 1, min);
      }
    }
  }
}

// Leetcode official solution, time O(sqrt(n)) and space O(1).
// https://leetcode.com/articles/2-keys-keyboard/
// Beats 99.43% of submissions
class Solution {
  public int minSteps(int n) {
    int ans = 0, d = 2;
    while (n > 1) {
      while (n % d == 0) {
        ans += d;
        n /= d;
      }
      d++;
    }
    return ans;
  }
}
