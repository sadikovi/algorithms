package test;

public class Test {
  public static int negate(int num) {
    return (~num) + 1;
  }

  public static int abs(int num) {
    return (num < 0) ? negate(num) : num;
  }

  public static int add(int a, int b) {
    return a + b;
  }

  public static int subtract(int a, int b) {
    return a + negate(b);
  }

  public static int multiply(int a, int b) {
    if (a == 0 || b == 0) return 0;
    boolean isPos = a > 0 && b > 0 || a < 0 && b < 0;
    a = abs(a);
    b = abs(b);
    int product = 0; int i = 0;
    while (b != 0) {
      if ((b & 0x1) == 1) {
        product += a << i;
      }
      i++;
      b >>>= 1;
    }
    return isPos ? product : negate(product);
  }

  // Division is slower than one in CTCI
  public static int divide(int a, int b) {
    if (b == 0) throw new IllegalArgumentException();
    if (a == 0) return 0;
    if (b == 1) return a;
    boolean isPos = a > 0 && b > 0 || a < 0 && b < 0;
    a = abs(a);
    b = abs(b);
    int res = 0; int t = 0;
    while (t >= 0) {
      t = multiply(b, res + 1);
      if (t > a) return res;
      res++;
    }
    return isPos ? res : negate(res);
  }
}
