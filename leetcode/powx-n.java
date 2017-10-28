// Solution runs in O(log n) time and O(log n) space, because it halves search space in half for
// every recursive call.
class Solution {
  public double myPow(double x, int n) {
    if (n == 0) return 1;
    x = (n < 0) ? 1 / x : x;
    long p = Math.abs((long) n);
    return pow(x, p);
  }

  private double pow(double x, long n) {
    if (n == 0) return 1;
    double res = pow(x, n / 2);
    return (n % 2 == 0) ? (res * res) : (res * res * x);
  }
}
