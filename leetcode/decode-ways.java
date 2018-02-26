// Solution runs in O(n) time, uses O(1) space.
// Dynamic programming question.
class Solution {
  public int numDecodings(String s) {
    int dp1 = 1, dp2 = 0, res = 0;
    for (int i = 0; i < s.length(); i++) {
      res = 0;
      if (s.charAt(i) >= '1' && s.charAt(i) <= '9') {
        res += dp1;
      }
      if (i >= 1 && (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) <= '6')) {
        res += dp2;
      }
      dp2 = dp1;
      dp1 = res;
    }
    return res;
  }
}
