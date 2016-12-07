class Solution {
  public int solution(int N, int M) {
    return N / gcd(N, M);
  }

  private int gcd(int n, int m) {
    if (n % m == 0)
      return m;

    return gcd(m, n % m);
  }
}
