// Solution runs in O(n * m) time and O(n * m) space, beats 73.92% of submissions.
//
// Idea is based on Dynamic Programming "Edit distance" technique, because we can think of this
// problem as, instead of deleting from both strings, we select "s2" as target and perform
// substitution, deletion or insertion on "s1" string.
//
// Our cost function is based on ASCII character code, so the smaller code is the better.
// Initialization is similar: first row and first column are both initialized as rolling sums of
// ASCII codes for "s1" and "s2" strings respectively (which represents deletion of all i-1 chars
// at position i, when the other string has j=0 and vice versa).
//
class Solution {
  public int minimumDeleteSum(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    // initialize first row and first column
    for (int i = 1; i <= n; i++) {
      dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
    }
    for (int j = 1; j <= m; j++) {
      dp[0][j] = dp[0][j-1] + s2.charAt(j-1);
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        char a = s1.charAt(i-1), b = s2.charAt(j-1);
        dp[i][j] = dp[i-1][j-1] + ((a == b) ? 0 : (a + b)); // match or substitution
        dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a); // delete from s1
        dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + b); // delete from s2 (insert into s1)
      }
    }
    return dp[n][m];
  }
}
