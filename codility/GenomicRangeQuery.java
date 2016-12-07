class Solution {
  public int[] solution(String S, int[] P, int[] Q) {
    int[] seq = new int[P.length];
    int[][] fs = new int[3][S.length()];

    for (int i=0; i<S.length(); i++) {
      short a = 0; short c = 0; short g = 0;
      if (S.charAt(i) == 'A') a = 1;
      if (S.charAt(i) == 'C') c = 1;
      if (S.charAt(i) == 'G') g = 1;

      fs[0][i] = ((i==0)?0:fs[0][i-1]) + a;
      fs[1][i] = ((i==0)?0:fs[1][i-1]) + c;
      fs[2][i] = ((i==0)?0:fs[2][i-1]) + g;
    }

    for (int k=0; k<P.length; k++) {
      if (P[k] == Q[k]) {
        if (S.charAt(P[k]) == 'A') seq[k] = 1;
        if (S.charAt(P[k]) == 'C') seq[k] = 2;
        if (S.charAt(P[k]) == 'G') seq[k] = 3;
        if (S.charAt(P[k]) == 'T') seq[k] = 4;
      } else {
        if (P[k] > 0) {
          if ((fs[0][Q[k]] - fs[0][P[k]-1]) > 0) {
            seq[k] = 1;
          } else if ((fs[1][Q[k]] - fs[1][P[k]-1]) > 0) {
            seq[k] = 2;
          } else if ((fs[2][Q[k]] - fs[2][P[k]-1]) > 0) {
            seq[k] = 3;
          } else {
            seq[k] = 4;
          }
        } else {
          if (fs[0][Q[k]] > 0) seq[k] = 1;
          else if (fs[1][Q[k]] > 0) seq[k] = 2;
          else if (fs[2][Q[k]] > 0) seq[k] = 3;
          else seq[k] = 4;
        }
      }
    }
    return seq;
  }
}

// solution #2
class Solution {
  public int[] solution(String S, int[] P, int[] Q) {
    // some util parameters
    int N = S.length();
    int M = P.length;
    // create 4 arrays for each nucleotide
    int[] A = new int[N + 1];
    int[] C = new int[N + 1];
    int[] G = new int[N + 1];
    int[] T = new int[N + 1];

    for (int i=0; i<N; i++) {
      char t = S.charAt(i);
      A[i+1] = A[i];
      C[i+1] = C[i];
      G[i+1] = G[i];
      T[i+1] = T[i];
      if (t == 'A') {
        A[i+1] += 1;
      } else if (t == 'C') {
        C[i+1] += 1;
      } else if (t == 'G') {
        G[i+1] += 1;
      } else if (t == 'T') {
        T[i+1] += 1;
      }
    }

    // loop through each query and compute result
    int[] res = new int[M];
    for (int i=0; i<M; i++) {
      int start = P[i];
      int end = Q[i] + 1;
      // check nucleotides in order: A, C, G, T
      if (A[end] - A[start] > 0) {
        res[i] = 1;
      } else if (C[end] - C[start] > 0) {
        res[i] = 2;
      } else if (G[end] - G[start] > 0) {
        res[i] = 3;
      } else {
        res[i] = 4;
      }
    }
    return res;
  }
}
