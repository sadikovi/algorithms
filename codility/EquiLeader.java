class Solution {
  public int solution(int[] A) {
    int size = 0;
    int value = 0;

    for (int i=0; i<A.length; i++) {
      if (size == 0) {
        size++;
        value = A[i];
      } else {
        if (value == A[i])
          size++;
        else
          size--;
      }
    }

    int count = 0;
    for (int i=0; i<A.length; i++)
      if (value == A[i])
        count++;

    // not a leader
    if (count <= A.length/2)
      return 0;

    int lcnt = 0;
    int ss = 0;
    for (int s=0; s<A.length-1; s++) {
      if (A[s] == value)
        lcnt++;

      if (lcnt > (s+1)/2 && (count-lcnt) > (A.length-s-1)/2)
        ss++;
    }

    return ss;
  }
}
