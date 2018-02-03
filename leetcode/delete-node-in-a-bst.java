// Solution runs in O(h) time and O(h) space, where h is the height of the binary search tree.
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
  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return root;
    if (root.val > key) {
      root.left = deleteNode(root.left, key);
      return root;
    } else if (root.val < key) {
      root.right = deleteNode(root.right, key);
      return root;
    } else {
      TreeNode right = root.right;
      // 1. root is the largest node, return left subtree
      if (right == null) return root.left;
      // 2. root.right is the smallest node in the right subtree, use it as new root
      if (right.left == null) {
        right.left = root.left;
        return right;
      }
      // find the smallest node in the right subtree to use as new root
      // and relink it
      while (right.left.left != null) {
        right = right.left;
      }
      TreeNode L = right;
      TreeNode T = right.left;
      L.left = T.right;
      T.right = root.right;
      T.left = root.left;
      return T;
    }
  }
}
