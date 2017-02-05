// You can also solve it with making substrings at each step, but it is slower.
// Current solution runs in 1ms and beats 91.83%
public class Solution {
  public boolean isInterleave(String s1, String s2, String s3) {
    if (s1.length() + s2.length() != s3.length()) return false;
    int[][] memo = new int[s1.length() + 1][s2.length() + 1];
    return recur(s1, 0, s2, 0, s3, 0, memo);
  }

  private boolean recur(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
    if (s1.length() == i && s2.length() == j && s3.length() == k) return true;
    if (s3.length() == k) return false;
    if (memo[i][j] != 0) return memo[i][j] == 1;
    char a = s3.charAt(k);
    boolean flag = false;
    if (i < s1.length() && s1.charAt(i) == a) {
        flag = flag || recur(s1, i + 1, s2, j, s3, k + 1, memo);
    }
    if (!flag && j < s2.length() && s2.charAt(j) == a) {
        flag = flag || recur(s1, i, s2, j + 1, s3, k + 1, memo);
    }
    memo[i][j] = flag ? 1 : -1;
    return flag;
  }
}
