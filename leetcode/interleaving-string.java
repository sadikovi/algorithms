// You can also solve it with making substrings at each step, but it is slower.
// Current solution runs in 3ms and beats 85.36%
public class Solution {
  public boolean isInterleave(String s1, String s2, String s3) {
    if (s1.length() + s2.length() != s3.length()) return false;
    return recur(s1, 0, s2, 0, s3, 0, new HashMap<String, Boolean>());
  }

  private boolean recur(String s1, int i, String s2, int j, String s3, int k, HashMap<String, Boolean> memo) {
    if (s1.length() == i && s2.length() == j && s3.length() == k) return true;
    if (s3.length() == k) return false;
    String key = i + "-" + j + "-" + k;
    if (memo.containsKey(key)) return memo.get(key);
    char a = s3.charAt(k);
    boolean flag = false;
    if (s1.length() > i && s1.charAt(i) == a) {
      flag = flag || recur(s1, i + 1, s2, j, s3, k + 1, memo);
    }
    if (!flag && s2.length() > j && s2.charAt(j) == a) {
      flag = flag || recur(s1, i, s2, j + 1, s3, k + 1, memo);
    }
    memo.put(key, flag);
    return flag;
  }
}
