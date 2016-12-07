class Solution {
  public int solution(int[] A, int[] B) {
    int pairs = 0;
    for (int i=0; i<A.length; i++)
      if (commonPrimeDivisors(A[i], B[i]))
        pairs++;

    return pairs;
  }

  private boolean commonPrimeDivisors(int a, int b) {
    int g = gcd(a, b);

    while (a != 1) {
      int ga = gcd(a, g);
      if (ga == 1)
        break;

      a = a/ga;
    }

    if (a != 1)
      return false;

    while (b != 1) {
      int gb = gcd(b, g);
      if (gb == 1)
        break;

      b = b/gb;
    }

    return (b == 1);
  }

  private int gcd(int a, int b) {
    if (a % b == 0)
      return b;

    return gcd(b, a % b);
  }
}
