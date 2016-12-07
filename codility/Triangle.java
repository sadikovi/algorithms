import java.util.Arrays;

class Solution {
  public int solution(int[] A) {
    if (A.length < 3) return 0;
    Arrays.sort(A);
    for (int i=0; i<A.length-2; i++)
      if (A[i] > 0 && A[i] > A[i+2] - A[i+1])
        return 1;
    return 0;
  }
}

// solution #2
import java.util.Arrays;

class Solution {
  public int solution(int[] A) {
    int n = A.length;
    if (n >= 3) {
      Arrays.sort(A);
      for (int i=0; i<n-2; i++) {
        if (A[i] > A[i+2] - A[i+1])
          return 1;
      }
    }

    return 0;
  }
}
