/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public int kthSmallest(TreeNode root, int k) {
    if (root == null) return -1; // invalid state
    int size = 1 + size(root.left);
    if (size == k) return root.val;
    if (size > k) return kthSmallest(root.left, k);
    return kthSmallest(root.right, k - size);
  }

  private int size(TreeNode root) {
    if (root == null) return 0;
    return 1 + size(root.left) + size(root.right);
  }
}
