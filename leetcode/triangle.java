// DP solution,
// modified version of https://discuss.leetcode.com/topic/1669/dp-solution-for-triangle
public class Solution {
  public int minimumTotal(List<List<Integer>> triangle) {
    int size = triangle.size();
    int[] dp = new int[size + 1];
    for (int layer = size - 1; layer >= 0; layer--) {
      for (int i = 0; i <= layer; i++) {
        dp[i] = Math.min(dp[i], dp[i + 1]) + triangle.get(layer).get(i);
      }
    }
    return dp[0];
  }
}

// Recursive solution
public class Solution {
  public int minimumTotal(List<List<Integer>> triangle) {
    return minTotal(triangle, 0, 0);
  }

  private int minTotal(List<List<Integer>> triangle, int depth, int i) {
    if (depth >= triangle.size()) return 0;
    if (depth == triangle.size() - 1) return triangle.get(depth).get(i);
    int left = minTotal(triangle, depth + 1, i);
    int right = minTotal(triangle, depth + 1, i + 1);
    return triangle.get(depth).get(i) + Math.min(left, right);
  }
}


// Recursive solution with memoization
public class Solution {
  public int minimumTotal(List<List<Integer>> triangle) {
    HashMap<String, Integer> memo = new HashMap<String, Integer>();
    return minTotal(triangle, 0, 0, memo);
  }

  private int minTotal(List<List<Integer>> triangle, int depth, int i, HashMap<String, Integer> memo) {
    if (memo.containsKey(depth + "-" + i)) return memo.get(depth + "-" + i);
    if (depth >= triangle.size()) return 0;
    if (depth == triangle.size() - 1) return triangle.get(depth).get(i);
    int left = minTotal(triangle, depth + 1, i, memo);
    int right = minTotal(triangle, depth + 1, i + 1, memo);
    int sum = triangle.get(depth).get(i) + Math.min(left, right);
    memo.put(depth + "-" + i, sum);
    return sum;
  }
}
