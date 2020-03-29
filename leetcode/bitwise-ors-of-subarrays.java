// Naive solution; time complexity is O(N^3), space complexity is O(W), where W is the maximum
// number of bits that a number is represented by in array A.
class Solution {
  public int subarrayBitwiseORs(int[] A) {
    HashSet<Integer> orResults = new HashSet<Integer>();
    for (int i = 0; i < A.length; i++) {
      for (int j = i; j < A.length; j++) {
        int tmp = 0;
        for (int k = i; k <= j; k++) {
          tmp |= A[k];
        }
        orResults.add(tmp);
      }
    }
    return orResults.size();
  }
}

// Slightly better solution.
// Time complexity is O(N^2) and space complexity is O(W), where W is the meximum number of bits
// that a number is represented by in array A.
class Solution {
  public int subarrayBitwiseORs(int[] A) {
    HashSet<Integer> results = new HashSet<Integer>(32);
    for (int i = 0; i < A.length; i++) {
      int res = A[i];
      for (int j = i; j < A.length; j++) {
        res |= A[j];
        results.add(res);
      }
    }
    return results.size();
  }
}

// Even better version.
// Time complexity is O(N * W), where N is the length of the array A and W is the maximum number
// of bits that a number is represented by in array A.
// Space complexity is O(W), where W is "see above". Here we reuse hashsets.
class Solution {
  public int subarrayBitwiseORs(int[] A) {
    HashSet<Integer> results = new HashSet<Integer>(32); // there are at most 32 different results
    HashSet<Integer> curr = new HashSet<Integer>(32);
    HashSet<Integer> tmp = new HashSet<Integer>(32);

    for (int i = 0; i < A.length; i++) {
      curr.add(A[i]);
      tmp.clear();

      for (int value : curr) {
        tmp.add(value | A[i]);
      }

      results.addAll(tmp);

      // swap curr and tmp instead of creating a new set every time.
      HashSet<Integer> swap = curr;
      curr = tmp;
      tmp = swap;
    }
    return results.size();
  }
}
