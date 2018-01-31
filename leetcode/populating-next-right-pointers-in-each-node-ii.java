// Solution runs in O(n) time and O(1) space.
// Every node is touched twice (when linking between left and right children and linking across
// children), hence O(n) time.
//
// Similar to solution for perfect binary tree, except we need to link right subtree first, because
// we need links to exist for leaf nodes on levels.

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
  public void connect(TreeLinkNode root) {
    if (root == null || root.left == null && root.right == null) return;
    if (root.left != null && root.right != null) {
      root.left.next = root.right;
    }
    TreeLinkNode tmp = root.next;
    while (tmp != null && tmp.left == null && tmp.right == null) {
      tmp = tmp.next;
    }
    if (tmp != null) {
      TreeLinkNode from = root.right != null ? root.right : root.left;
      TreeLinkNode to = tmp.left != null ? tmp.left : tmp.right;
      from.next = to;
    }
    connect(root.right);
    connect(root.left);
  }
}
