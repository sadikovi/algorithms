// Solution runs in O(d log d + s^2) time, and uses O(d) space (for sort).
// I assume that each word in the dictionary will have most "s" length.
// Idea is sorting the dictionary by increasing in length and lexicographical order.
// Then we scan target word and dictionary word to determine the same order of characters.
class Solution {
  public String findLongestWord(String s, List<String> d) {
    Collections.sort(d, new Comparator<String>() {
      @Override
      public int compare(String a, String b) {
        if (a.length() == b.length()) return a.compareTo(b);
        return b.length() - a.length();
      }
    });
    for (String w : d) {
      if (match(s, w)) return w;
    }
    return "";
  }

  private boolean match(String s, String w) {
    if (s.length() < w.length()) return false;
    int i = 0, j = 0;
    while (i < s.length() && j < w.length()) {
      char a = s.charAt(i);
      char b = w.charAt(j);
      if (a == b) {
        i++;
        j++;
      } else {
        while (i < s.length() && s.charAt(i) != b) i++;
      }
    }
    return j >= w.length();
  }
}
