// Java version of the solution:
// https://discuss.leetcode.com/topic/21882/my-16ms-c-dp-solution-with-short-explanation
public class Solution {
  public int nthUglyNumber(int n) {
    if (n <= 0) return 0;
    if (n == 1) return 1;
    int t2 = 0, t3 = 0, t5 = 0;
    int[] dp = new int[n];
    dp[0] = 1;
    for (int i = 1; i < n; i++) {
      dp[i] = Math.min(dp[t2] * 2, Math.min(dp[t3] * 3, dp[t5] * 5));
      if (dp[i] == dp[t2] * 2) t2++;
      if (dp[i] == dp[t3] * 3) t3++;
      if (dp[i] == dp[t5] * 5) t5++;
    }
    return dp[n - 1];
  }
}

// Solution with heaps (slower):
class Solution {
  public int nthUglyNumber(int n) {
    LinkedList<Long> h2 = new LinkedList<Long>();
    LinkedList<Long> h3 = new LinkedList<Long>();
    LinkedList<Long> h5 = new LinkedList<Long>();
    h2.add(1L);
    long val = 0;
    for (int i = 1; i <= n; i++) {
      long v2 = h2.isEmpty() ? Integer.MAX_VALUE : h2.peek();
      long v3 = h3.isEmpty() ? Integer.MAX_VALUE : h3.peek();
      long v5 = h5.isEmpty() ? Integer.MAX_VALUE : h5.peek();
      val = Math.min(v2, Math.min(v3, v5));
      if (v2 == val) {
        h2.remove();
        h2.add(2L * val);
        h3.add(3L * val);
      } else if (v3 == val) {
        h3.remove();
        h3.add(3L * val);
      } else {
        h5.remove();
      }
      h5.add(5L * val);
    }
    return (int) val;
  }
}
