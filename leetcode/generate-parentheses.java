// Concise solution, taken from the forum:
// https://discuss.leetcode.com/topic/4485/concise-recursive-c-solution
class Solution {
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<String>();
    recur(res, "", n, 0);
    return res;
  }

  private void recur(List<String> res, String str, int n, int m) {
    if (n == 0 && m == 0) {
      res.add(str);
      return;
    }
    if (m > 0) recur(res, str + ")", n, m - 1);
    if (n > 0) recur(res, str + "(", n - 1, m + 1);
  }
}

// My recursive solution:
class Solution {
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<String>();
    if (n > 0) {
      for (int i = 0; i < n; i++) {
        List<String> left = generateParenthesis(n - 1 - i);
        if (left.isEmpty()) left.add("");
        List<String> right = generateParenthesis(i);
        if (right.isEmpty()) right.add("");
        for (String l : left) {
          for (String r : right) {
            res.add("(" + l + ")" + r);
          }
        }
      }
    }
    return res;
  }
}
