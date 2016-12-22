// Solution beats 37.90% of submissions
public class Solution {
  public int strStr(String haystack, String needle) {
    if (haystack == null || needle == null) return -1;
    for (int i = 0; i <= haystack.length() - needle.length(); i++) {
      int j = 0;
      while (j < needle.length() && haystack.charAt(i + j) == needle.charAt(j)) {
        j++;
      }
      if (j == needle.length()) return i;
    }
    return -1;
  }
}

// Solution that beats 100% submissions on leetcode using standard Java string operations
public class Solution {
  public int strStr(String haystack, String needle) {
    if (haystack.length() < needle.length()) return -1;
    for (int i = 0; i <= haystack.length() - needle.length(); i++) {
      String sbs = haystack.substring(i, needle.length() + i);
      if (sbs.equals(needle)) return i;
    }
    return -1;
  }
}
