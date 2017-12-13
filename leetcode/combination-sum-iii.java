// Solution runs in O(k*2^k) (k * 9^k) time, because when k and n are sufficiently large, recursion
// tree height is at most k, and we make 9 choices at each level, and it takes O(k) to copy elements
// for a list in the worst case. Space complexity is O(k^2) space.
class Solution {
  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    combinations(0, k, n, new ArrayList<Integer>(), res);
    return res;
  }

  private void combinations(int i, int k, int n, List<Integer> curr, List<List<Integer>> res) {
    if (n == 0 && k == 0) {
      res.add(curr);
    } else if (n > 0 && k > 0) {
      for (int j = i+1; j <= 9; j++) {
        List<Integer> copy = new ArrayList<Integer>(curr);
        copy.add(j);
        combinations(j, k-1, n-j, copy, res);
      }
    }
  }
}
