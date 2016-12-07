import java.util.HashMap;

class Solution {
  public int[] solution(int[] A) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> resmap = new HashMap<Integer, Integer>();
    int[] res = new int[A.length];

    for (int i=0; i<A.length; i++) {
      int count = 1;
      if (map.containsKey(A[i]))
        count += map.get(A[i]);
      map.put(A[i], count);
    }

    for (int N : map.keySet()) {
      int count = 0;
      int i = 1;
      while (i * i <= N) {
        if (N % i == 0) {
          if (map.containsKey(i))
            count += (int)map.get(i);
          if (map.containsKey(N/i) && N/i != i)
            count += (int)map.get(N/i);
        }
        i++;
      }
      resmap.put(N, A.length-count);
    }

    for (int j=0; j<A.length; j++)
      res[j] = resmap.get(A[j]);

    return res;
  }
}
