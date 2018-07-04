// Solution runs in O(log n) time and O(log n) space, because it halves search space for
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

// Alternative solution
// Runs in O(log n) time and O(log n) space, beats 100% of submissions.
class Solution {
  public double myPow(double x, int n) {
    return n < 0 ? pow(1/x, -((long) n)) : pow(x, (long) n);
  }

  private double pow(double x, long n) {
    if (n == 0) return 1;
    if (n == 1) return x;
    long rem = n % 2;
    double res = pow(x, n /2);
    return rem == 0 ? res * res : res * res * x;
  }
}
