import java.util.Arrays;

class Solution {
  public int solution(int[] A) {
    Arrays.sort(A);

    int temp1 = A[A.length-1]*A[A.length-2]*A[A.length-3];
    if (temp1 < A[0]*A[1]*A[2]) temp1 = A[0]*A[1]*A[2];
    if (temp1 < A[0]*A[1]*A[A.length-1]) temp1 = A[0]*A[1]*A[A.length-1];

    return temp1;
  }
}
