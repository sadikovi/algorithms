/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
// Fast solution with O(h) space, where h is height of the tree
public class Solution {
  public void connect(TreeLinkNode root) {
    if (root == null || root.left == null || root.right == null) return;
    // we know that this is a perfect tree, left and right are not null
    root.left.next = root.right;
    if (root.next != null) {
      root.right.next = root.next.left;
    }
    connect(root.left);
    connect(root.right);
  }
}

// Slow solution using O(n) space to collect nodes at the same level
public class Solution {
  public void connect(TreeLinkNode root) {
    List<List<TreeLinkNode>> arr = new ArrayList<List<TreeLinkNode>>();
    dfs(root, 0, arr);
    for (List<TreeLinkNode> list : arr) {
      TreeLinkNode node = null;
      for (TreeLinkNode n : list) {
        if (node == null) {
          node = n;
        } else {
          node.next = n;
          node = n;
        }
      }
    }
  }

  private void dfs(TreeLinkNode root, int depth, List<List<TreeLinkNode>> arr) {
    if (root == null) return;
    if (arr.size() >= depth) {
      arr.add(new ArrayList<TreeLinkNode>());
    }
    arr.get(depth).add(root);
    dfs(root.left, depth + 1, arr);
    dfs(root.right, depth + 1, arr);
  }
}
