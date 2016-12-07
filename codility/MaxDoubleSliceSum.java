/*
A variant of the classic maximum subarray problem. We should travel the array twice.
For the first travel, we compute and record the maximum sub-array sum, which ends at each position.
At the second reverse travel, we compute and record the maximum sub-array sum, which starts at each position.
Finally, we combine two sub-arrays into one double slice, and find out the maximum sum.
*/

class Solution {
  public int solution(int[] A) {
    int[] a = new int[A.length];
    int[] b = new int[A.length];

    int ms = 0;
    for (int i=1; i<A.length-1; i++) {
      if (ms + A[i] > 0)
        ms = ms + A[i];
      else
        ms = 0;

      a[i] = ms;
    }

    int me = 0;
    for (int i=A.length-2; i>0; i--) {
      if (me + A[i] > 0)
        me = me + A[i];
      else
        me = 0;

      b[i] = me;
    }

    int sum = 0;
    for (int i=0; i<A.length-2; i++)
      if (a[i] + b[i+2] > sum)
        sum = a[i] + b[i+2];

    return sum;
  }
}
