public class Solution {
  public int divide(int dividend, int divisor) {
    if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
    boolean positive = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0;
    long a = Math.abs((long) dividend);
    long b = Math.abs((long) divisor);

    int res = 0;
    while (a >= b) {
      long tmp = b, i = 1;
      while (a >= (tmp << 1)) {
        tmp <<= 1;
        i <<= 1;
      }
      a -= tmp;
      res += i;
    }
    return positive ? res : -res;
  }
}
