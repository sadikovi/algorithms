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
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    traverse(root, res, 0);
    return res;
  }

  private void traverse(TreeNode root, List<List<Integer>> res, int level) {
    if (root == null) return;
    if (res.size() == level) res.add(new ArrayList<Integer>());
    res.get(level).add(root.val);
    traverse(root.left, res, level + 1);
    traverse(root.right, res, level + 1);
  }
}
