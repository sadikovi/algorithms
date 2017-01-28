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
  public boolean isValidBST(TreeNode root) {
    return bst(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean bst(TreeNode root, long min, long max) {
    if (root == null) return true;
    return root.val > min && root.val < max &&
      bst(root.left, min, root.val) &&
      bst(root.right, root.val, max);
  }
}
