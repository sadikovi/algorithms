// Runtime of the solution is roughly O(s^2 * p), s^2 part comes from doing prepend for each
// character is 's', which results in at most s^2.
public class Solution {
  public boolean isMatch(String s, String p) {
    return isMatch(s, p, new HashMap<String, Boolean>());
  }

  public boolean isMatch(String s, String p, HashMap<String, Boolean> memo) {
    if (p.length() == 0) return s.length() == 0;
    String key = s + "-" + p;
    if (memo.containsKey(key)) return memo.get(key);

    char t = p.charAt(0);
    if (p.length() > 1 && p.charAt(1) == '*') {
      for (int i = 0; i < s.length(); i++) {
        if (isMatch(s, prepend(p.substring(2), t, i + 1), memo)) {
          return true;
        }
      }
      return isMatch(s, p.substring(2), memo);
    } else if (s.length() > 0) {
      boolean match = (t == '.' || s.charAt(0) == t) &&
        isMatch(s.substring(1), p.substring(1), memo);
      memo.put(key, match);
      return match;
    } else {
      return false;
    }
  }

  private String prepend(String p, char t, int num) {
    if (num == 0) return p;
    StringBuilder sb = new StringBuilder();
    while (num > 0) {
      sb.append(t);
      num--;
    }
    sb.append(p);
    return sb.toString();
  }
}
