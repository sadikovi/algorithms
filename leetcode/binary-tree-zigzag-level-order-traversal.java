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
  // Idea is reusing solution for level order traversal.
  // Once elements are collected level by level, we reverse odd indices (see problem definition)
  // to make it look like zigzag traversal.
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    traverse(root, res, 0);
    // reverse elements on odd indices
    for (int i = 0; i < res.size(); i++) {
      if (i % 2 != 0) {
        List<Integer> tmp = res.get(i);
        int j = 0;
        while (j < tmp.size() / 2) {
          int t = tmp.get(tmp.size() - j - 1);
          tmp.set(tmp.size() - j - 1, tmp.get(j));
          tmp.set(j, t);
          j++;
        }
      }
    }
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
