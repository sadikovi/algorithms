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
  static class Nodes {
    TreeNode head;
    TreeNode tail;
  }

  private Nodes convert(TreeNode root) {
    Nodes res = new Nodes();
    if (root == null) return res;
    Nodes left = convert(root.left);
    Nodes right = convert(root.right);
    root.left = null;
    if (left.head != null && right.head != null) {
      root.right = left.head;
      left.tail.right = right.head;
      res.head = root;
      res.tail = right.tail;
    } else if (left.head != null) {
      root.right = left.head;
      res.head = root;
      res.tail = left.tail;
    } else if (right.head != null) {
      root.right = right.head;
      res.head = root;
      res.tail = right.tail;
    } else {
      res.head = root;
      res.tail = root;
    }
    return res;
  }

  public void flatten(TreeNode root) {
    convert(root);
  }
}
