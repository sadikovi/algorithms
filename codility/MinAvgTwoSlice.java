class Solution {
  public int solution(int[] A) {
    int[] fs = new int[A.length];
    for (int i=0; i<A.length; i++) {
      if (i==0) fs[i] = A[i];
      if (i>0) fs[i] = fs[i-1] + A[i];
    }

    double avg = fs[1]/2.0;
    int startingPos = 0;

    for (int j=2; j< fs.length; j++) {
      // 2 elements in slice
      double tempAvg2 = (fs[j]-fs[j-2])/2.0;
      if (tempAvg2 < avg) {
        avg = tempAvg2;
        startingPos = j-1;
      }

      // 3 elements in slice
      double tempAvg3 = (fs[j]-((j>2)?fs[j-3]:0))/3.0;
      if (tempAvg3 < avg) {
        avg = tempAvg3;
        startingPos = j-2;
      }
    }
    return startingPos;
  }
}

// solution 2
// might be a bit faster than the previous one
class Solution {
  public int solution(int[] A) {
    double minAvg = (A[0] + A[1]) / 2.0;
    int pos = 0;

    for (int i=0; i<A.length-1; i++) {
      if (minAvg > (A[i] + A[i+1]) / 2.0) {
        minAvg = (A[i] + A[i+1]) / 2.0;
        pos = i;
      }

      if (i < A.length-2) {
        if (minAvg > (A[i] + A[i+1] + A[i+2]) / 3.0) {
          minAvg = (A[i] + A[i+1] + A[i+2]) / 3.0;
          pos = i;
        }
      }
    }

    return pos;
  }
}

// solution 3
class Solution {
  public int solution(int[] A) {
    int[] sum = new int[A.length];
    for (int i=0; i<A.length; i++) {
      if (i == 0) {
        sum[i] = A[i];
      } else {
        sum[i] = sum[i-1] + A[i];
      }
    }

    double minAvg = sum[1] / 2.0;
    int ind = 0;
    for (int i=2; i<A.length; i++) {
      double avg = (sum[i] - sum[i-2]) / 2.0;
      if (minAvg > avg) {
        minAvg = avg;
        ind = i-1;
      }

      if (i == 2) {
        avg = sum[2] / 3.0;
      } else if (i > 2) {
        avg = (sum[i] - sum[i-3]) / 3.0;
      }

      if (minAvg > avg) {
        minAvg = avg;
        ind = i-2;
      }
    }
    return ind;
  }
}
