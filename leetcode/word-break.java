// Solution based on memoization, beats 86.64% of submissions.
// Runs in O(n * m) time and O(n^2) space, where n is length of the string and m is the size of
// dictionary.
class Solution {
  public boolean wordBreak(String s, List<String> wordDict) {
    return recur(s, wordDict, new HashMap<String, Boolean>());
  }

  private boolean recur(String s, List<String> wordDict, HashMap<String, Boolean> memo) {
    if (memo.containsKey(s)) return memo.get(s);
    boolean found = s.isEmpty();
    for (String word : wordDict) {
      found = found ||
        s.startsWith(word) && recur(s.substring(word.length()), wordDict, memo);
    }
    memo.put(s, found);
    return found;
  }
}

// Solution based on dynamic programming, beats 43.35% of submissions.
// Runs in O(n^2 + m) time and O(n + m) space.
class Solution {
  public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> set = new HashSet<String>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 1; i < dp.length; i++) {
      for (int j = 0; j < i; j++) {
        dp[i] = dp[i] || dp[j] && set.contains(s.substring(j, i));
      }
    }
    return dp[s.length()];
  }
}
