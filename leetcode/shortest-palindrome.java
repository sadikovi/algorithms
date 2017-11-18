// Recursive solution (2 pointers) runs in O(n^2) time and O(n) space
// Solution is an adaptation of the official C++ solution.
class Solution {
  public String shortestPalindrome(String s) {
    int i = 0;
    for (int j = s.length() - 1; j >= 0; j--) {
      if (s.charAt(i) == s.charAt(j)) {
        ++i;
      }
    }
    if (i == s.length()) return s;
    String rem = reverse(s.substring(i, s.length()));
    return rem + shortestPalindrome(s.substring(0, i)) + s.substring(i);
  }

  private String reverse(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = s.length() - 1; i >= 0; i--) {
      sb.append(s.charAt(i));
    }
    return sb.toString();
  }
}

// KMP solution runs in O(n) and uses O(n) space.
// Solution is an adaptation of the official C++ solution.
// Idea is using Knuth–Morris–Pratt algorithm, specifically lookup table.
class Solution {
  public String shortestPalindrome(String s) {
    String rev = reverse(s);
    String snew = s + "#" + rev;
    int[] f = new int[snew.length()];
    for (int i = 1; i < f.length; i++) {
      int t = f[i - 1];
      while (t > 0 && snew.charAt(t) != snew.charAt(i)) {
        t = f[t - 1];
      }
      if (snew.charAt(t) == snew.charAt(i)) {
        ++t;
      }
      f[i] = t;
    }
    return rev.substring(0, s.length() - f[snew.length() - 1]) + s;
  }

  private String reverse(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = s.length() - 1; i >= 0; i--) {
      sb.append(s.charAt(i));
    }
    return sb.toString();
  }
}
