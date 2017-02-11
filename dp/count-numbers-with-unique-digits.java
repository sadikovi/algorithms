public class Solution {
  public int countNumbersWithUniqueDigits(int n) {
    int dp = 1;
    int x = 1;
    for (int i = 1; i <= n; i++) {
      if (i >= 2) x *= 9 - i + 2;
      dp = 9 * x + dp;
    }
    return dp;
  }
}
