public class Solution {
  // Beats 96% of the solutions.
  // Magic numbers 85/58/16/61/65/89/37 are indicators that number will eventually produce cycle.
  public boolean isHappy(int n) {
    while (n > 1) {
      int sum = 0;
      while (n != 0) {
        int x = n % 10;
        sum += x * x;
        n /= 10;
      }
      if (sum == 65 || sum == 89 || sum == 25 || sum == 37) return false;
      n = sum;
    }
    return n == 1;
  }
}
