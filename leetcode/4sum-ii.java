public class Solution {
  // O(N^2) time and O(N^2) space complexity, where N is length of array A
  // Note that len(A) == len(B) == len(C) == len(D)
  public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int elemC : C) {
      for (int elemD : D) {
        int key = elemC + elemD;
        if (!map.containsKey(key)) {
          map.put(key, 0);
        }
        map.put(key, map.get(key) + 1);
      }
    }
    int count = 0;
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < B.length; j++) {
        int target = -(A[i] + B[j]);
        if (map.containsKey(target)) {
          count += map.get(target);
        }
      }
    }
    return count;
  }
}
