// Solution runs in O(s1 * s2) time and uses O(s1 * s2) space.
// Beats 90.59% of all submissions
// Here we use recursion with memoization.
class Solution {
  public boolean isInterleave(String s1, String s2, String s3) {
    if (s1.length() + s2.length() != s3.length()) return false;
    return helper(s1, 0, s2, 0, s3, 0, new int[s1.length() + 1][s2.length() + 1]);
  }

  private boolean helper(String s1, int p1, String s2, int p2, String s3, int p3, int[][] memo) {
    if (p1 == s1.length() && p2 == s2.length() && p3 == s3.length()) return true;
    if (p1 >= s1.length() && p2 >= s2.length() || p3 >= s3.length()) return false;
    if (memo[p1][p2] != 0) return memo[p1][p2] == 1;
    boolean res =
      p1 < s1.length() && s1.charAt(p1) == s3.charAt(p3) && helper(s1, p1+1, s2, p2, s3, p3+1, memo) ||
      p2 < s2.length() && s2.charAt(p2) == s3.charAt(p3) && helper(s1, p1, s2, p2+1, s3, p3+1, memo);
    memo[p1][p2] = res ? 1 : -1;
    return res;
  }
}
