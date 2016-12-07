class Solution {
  public int[] solution(int N, int[] P, int[] Q) {
    int[] res = new int[P.length];
    int[] sieve = new int[N+1];
    int[] p = new int[N+1];

    for (int i=2; i*i <= N; i++) {
      int k=i*i;
      while (k<=N) {
        if (sieve[k] >= 0) {
          if (definePrime(k/i))
            sieve[k] = 1;
          else
            sieve[k] = -1;
        }
        k += i;
      }
    }

    for (int i=1; i<N+1; i++) {
      if (sieve[i] > 0)
        p[i] = p[i-1]+1;
      else
        p[i] = p[i-1];
    }

    for (int i=0; i<P.length; i++)
      res[i] = p[Q[i]] - p[P[i]-1];

    return res;
  }

  private boolean definePrime(int n) {
    int i = 2;
    while (i*i<= n) {
      if (n % i == 0)
        return false;
      i++;
    }
    return true;
  }
}
