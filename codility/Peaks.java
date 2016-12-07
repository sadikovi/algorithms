class Solution {
  public int solution(int[] A) {
    int[] a = new int[A.length];
    int peak = -1;

    for (int i=0; i<A.length; i++) {
      if (i > 0 && i < A.length-1)
        if (A[i] > A[i-1] && A[i] > A[i+1])
          peak = i;
      a[i] = peak;
    }

    if (peak < 0)
      return 0;

    for (int i=2; i<A.length; i++) {
      if (A.length % i == 0) {
        int n = 0;
        for (int j=0; (j+i)<=A.length; j+=i) {
          if (a[j+i-1] >= j && a[j+i-1] < j+i)
            n++;
          else
            break;
        }

        if (n == A.length/i)
          return n;
      }
    }

    return 1;
  }
}

// another solution
// uses the same logic, though iterates through the half of the values of the group size
class Solution {
  public int solution(int[] A) {
    int[] a = new int[A.length];
    int peak = -1;

    for (int i=0; i<A.length; i++) {
      if (i > 0 && i < A.length-1)
        if (A[i] > A[i-1] && A[i] > A[i+1])
          peak = i;
      a[i] = peak;
    }

    if (peak < 0)
      return 0;

    int num = 1;
    int i = 2;
    while (i*i <= A.length) {
      if (A.length % i == 0) {
        int n = 0;
        int k = A.length/i;
        for (int j=0; (j+k)<=A.length; j+=k) {
          if (a[j+k-1] >= j && a[j+k-1] < j+k)
            n++;
          else
            break;
        }

        if (n == i && num < n)
          num = n;

        n = 0;
        for (int j=0; (j+i)<=A.length; j+=i) {
          if (a[j+i-1] >= j && a[j+i-1] < j+i)
            n++;
          else
            break;
        }

        if (n == A.length/i && num < n)
          num = n;
      }

      i++;
    }

    return num;
  }
}
