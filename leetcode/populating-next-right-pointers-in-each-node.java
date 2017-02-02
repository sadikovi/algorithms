/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
// Fast solution with O(1) space
public class Solution {
  public void connect(TreeLinkNode root) {
    if (root == null) return;
    if (root.left == null || root.right == null) return;
    root.left.next = root.right;
    TreeLinkNode subtreeNext = root.next;
    if (subtreeNext != null) {
      root.right.next = subtreeNext.left;
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
