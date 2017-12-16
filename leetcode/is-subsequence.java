// Solution for simple check if s is a subsequence of t.
// Runs in O(s * t) time and O(1) space. Beats 79.96% of submissions.
class Solution {
  public boolean isSubsequence(String s, String t) {
    int curr = -1;
    for (int i = 0; i < s.length(); i++) {
      char a = s.charAt(i);
      int j = curr + 1;
      while (j < t.length() && t.charAt(j) != a) ++j;
      if (j == t.length()) return false;
      curr = j;
    }
    return true;
  }
}

// Solution for follow-up question. We build index of each character in t with its list of
// positions in increasing order. For every character in s we check if such character exists in t
// and if next smallest position is less than curr position.
// Runs in O(t + s * log(t)) time and O(t) space.
class Solution {
  public boolean isSubsequence(String s, String t) {
    HashMap<Character, List<Integer>> index = new HashMap<Character, List<Integer>>();
    for (int i = 0; i < t.length(); i++) {
      char a = t.charAt(i);
      if (!index.containsKey(a)) {
        index.put(a, new ArrayList<Integer>());
      }
      index.get(a).add(i);
    }

    int curr = -1;
    for (int i = 0; i < s.length(); i++) {
      char a = s.charAt(i);
      List<Integer> seq = index.get(a);
      if (seq == null) return false;
      curr = findNext(seq, curr);
      if (curr == -1) return false;
    }
    return true;
  }

  private int findNext(List<Integer> seq, int curr) {
    int start = 0, end = seq.size() - 1, tmp = -1;
    while (start <= end) {
      int m = (start + end) / 2;
      if (seq.get(m) <= curr) {
        start = m + 1;
      } else {
        tmp = seq.get(m);
        end = m - 1;
      }
    }
    return (tmp > curr) ? tmp : -1;
  }
}
