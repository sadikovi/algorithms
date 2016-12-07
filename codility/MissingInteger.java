import java.util.HashMap;

class Solution {
  public int solution(int[] A) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    int max = 0;

    for (int i=0; i<A.length; i++) {
      map.put(A[i], 1);
      if (max < A[i])
        max = A[i];
    }

    for (int i=1; i<=max; i++)
      if (!map.containsKey(i))
        return i;

    return max+1;
  }
}
