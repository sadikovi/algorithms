// Solution runs in O(n * m) time and O(n * m) space,
// where n is the length of s and m is the length of t.
//
// Idea is similar to edit distance, except we can only remove characters and we need to accumulate
// subsequences. Explanation of the cost function from leetcode:
// - if the current character in S doesn't equal to current character T, then we have the same
//   number of distinct subsequences as we had without the new character.
// - if the current character in S equal to the current character T, then the distinct number of
//   subsequences: the number we had before plus the distinct number of subsequences we had with
//   less longer T and less longer S.
//
class Solution {
  public int numDistinct(String s, String t) {
    int n = s.length() + 1, m = t.length() + 1;
    int[][] dp = new int[n][m];

    for (int i = 0; i < n; i++) dp[i][0] = 1;

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        dp[i][j] = dp[i-1][j];
        if (s.charAt(i-1) == t.charAt(j-1)) {
          dp[i][j] += dp[i-1][j-1];
        }
      }
    }
    return dp[n-1][m-1];
  }
}
