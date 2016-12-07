class Solution {
  public int solution(int[] A) {
    int[] count = new int[A.length+1];

    for (int i=0; i<A.length; i++) {
      if (A[i] > A.length) {
        return 0;
      } else {
        if (count[A[i]] == 1)
          return 0;
        count[A[i]] = 1;
      }
    }

    return 1;
  }
}

// second solution
import java.util.HashSet;

class Solution {
  public int solution(int[] A) {
    HashSet<Integer> set = new HashSet<Integer>();
    int N = A.length;
    int sum = (N+1)*N/2;

    for (int i=0; i<N; i++) {
      if (set.contains(A[i])) return 0;
      set.add(A[i]);
      sum -= A[i];
    }

    return (sum == 0) ? 1 : 0;
  }
}
