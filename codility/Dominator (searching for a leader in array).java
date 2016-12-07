class Solution {
  public int solution(int[] A) {
      if (A.length == 0) return -1;

      int size = 1;
      int value = A[0];
      for (int i=1; i<A.length; i++) {
        if (size == 0) {
          size++;
          value = A[i];
        } else {
          if (value != A[i]) {
            size--;
          } else {
            size++;
          }
        }
      }

      int candidate = -1;
      if (size > 0) candidate = value;

      int count = 0;
      int pos = -1;
      for (int k=0; k<A.length; k++) {
        if (candidate == A[k]) {
          count++;
          pos = k;
        }
      }

      if (count > A.length/2) return pos;
      return -1;
  }
}
