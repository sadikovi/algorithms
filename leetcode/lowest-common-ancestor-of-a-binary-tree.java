/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left != null && right != null) return root;
    return left != null ? left : right;
  }
}

// Alternative solution similar to CTCI solution, runs in the same time as one above
public class Solution {
  static class Result {
    boolean foundP;
    boolean foundQ;
    TreeNode lca;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || p == null || q == null) return null;
    Result res = lca(root, p, q);
    return res.lca;
  }

  private Result lca(TreeNode root, TreeNode p, TreeNode q) {
    Result res = new Result();
    if (root == null) return res;
    Result left = lca(root.left, p, q);
    if (left.lca != null) return left;

    Result right = lca(root.right, p, q);
    if (right.lca != null) return right;

    res.foundP = left.foundP || right.foundP || root == p;
    res.foundQ = left.foundQ || right.foundQ || root == q;

    if (res.foundP && res.foundQ)
      res.lca = root;

    return res;
  }
}
