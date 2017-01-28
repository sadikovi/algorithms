public class Solution {
  // Beats 96% of the solutions.
  // Magic numbers 85/58/16/61 are indicators that number will eventually produce cycle.
  public boolean isHappy(int n) {
    if (n <= 0) return false;
    int orig = n;
    while (n != 1) {
      int tmp = n;
      n = 0;
      while (tmp != 0) {
        int sq = tmp % 10;
        n += sq * sq;
        tmp /= 10;
      }
      if (n == orig || n == 85 || n == 58 || n == 16 || n == 61) {
        return false;
      }
    }
    return true;
  }
}
