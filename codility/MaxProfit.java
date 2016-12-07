class Solution {
  public int solution(int[] A) {
    int[] a = new int[A.length];
    for (int i=0; i<A.length; i++) {
      if (i==0)
        a[i] = 0;
      else
        a[i] = A[i] - A[i-1];
    }

    int me = 0;
    int mc = 0;
    for (int i=0; i<a.length; i++) {
      if (me+a[i] > 0)
        me += a[i];
      else
        me = 0;

      if (mc < me)
        mc = me;
    }

    return mc;
  }
}
