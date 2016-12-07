class Solution {
  public int solution(int[] A) {
    int pairs = 0;
    int cars = 0;
    boolean start = false;

    for (int i=0; i<A.length; i++) {
      if (!start && A[i] == 0) {
        start = true;
        cars++;
        continue;
      }

      if (start) {
        if (A[i] == 1)
          pairs += cars;
        else
          cars++;
      }

      if (pairs > 1000000000)
        return -1;
    }

    return pairs;
  }
}
