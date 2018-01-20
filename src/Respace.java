import java.util.HashSet;

// CTCI: 17.13
// CTCI only has brute force and memoization (right -> left) solutions. This solution is dynamic
// programming (left -> right), uses O(n) space and runs in O(n^2) time.
// To find actual splits (spaces) we maintain separate array with positions.
//
// Example:
// Respace.respace(Array("looked", "just", "like", "her", "brother"), "jesslookedjustliketimherbrother")
// res0: String = jess looked just like tim her brother
// Respace.respace(Array("reset", "computer", "it", "did", "not", "boot"), "resetthecomputeritdidnotbootmike")
// res3: String = reset the computer it did not boot mike
public class Respace {
  public static String respace(String[] words, String text) {
    HashSet<String> dict = new HashSet<String>();
    for (String w : words) {
      dict.add(w);
    }
    int[] dp = new int[text.length() + 1]; // contains unrecognized characters
    int[] ps = new int[text.length() + 1]; // contains split positions
    for (int i = 1; i <= text.length(); i++) {
      dp[i] = Integer.MAX_VALUE;
      ps[i] = -1;
      for (int j = 0; j < i; j++) {
        String t = text.substring(j, i);
        int rnk = dp[j] + (dict.contains(t) ? 0 : t.length());
        if (dp[i] > rnk) {
          dp[i] = rnk;
          ps[i] = j;
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    sb.append(text);
    for (int i = ps.length - 1; i > 0; i--) {
      if (ps[i] != ps[i - 1]) {
        sb.insert(ps[i], " ");
      }
    }
    return sb.toString();
  }
}
