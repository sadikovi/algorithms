// Great explanation on Leetcode with step-by-step guide:
// https://discuss.leetcode.com/topic/39834/step-by-step-tackling-of-the-problem

// Start with exponential runtime solution:
class Solution {
  public int rob(TreeNode root) {
    if (root == null) return 0;
    int val = 0;
    if (root.left != null) {
      val += rob(root.left.left) + rob(root.left.right);
    }
    if (root.right != null) {
      val += rob(root.right.left) + rob(root.right.right);
    }

    return Math.max(root.val + val, rob(root.left) + rob(root.right));
  }
}

// Add memoization because of the overlapping subproblems:
class Solution {
  public int rob(TreeNode root) {
    return rob(root, new HashMap<TreeNode, Integer>());
  }

  private int rob(TreeNode root, HashMap<TreeNode, Integer> memo) {
    if (root == null) return 0;
    if (memo.containsKey(root)) return memo.get(root);

    int val = root.val;
    if (root.left != null) {
      val += rob(root.left.left, memo) + rob(root.left.right, memo);
    }
    if (root.right != null) {
      val += rob(root.right.left, memo) + rob(root.right.right, memo);
    }
    int res = Math.max(val, rob(root.left, memo) + rob(root.right, memo));
    memo.put(root, res);
    return res;
  }
}

// Build dynamic programming solution:
class Solution {
  public int rob(TreeNode root) {
    int[] res = robSub(root);
    return Math.max(res[0], res[1]);
  }

  private int[] robSub(TreeNode root) {
    if (root == null) return new int[2];
    int[] left = robSub(root.left);
    int[] right = robSub(root.right);

    int[] res = new int[2];
    res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    res[1] = root.val + left[0] + right[0];

    return res;
  }
}
