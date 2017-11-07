// Solution runs:
// - height takes O(n) time and O(h) space in the worst case, where h is height and can be n
// - building array takes O(2^n) time and O(2^n) space [output]
// - print takes O(n) time and O(h) space in the worst case, where h is height and can be n
//
// Idea is calculating height and total number of nodes in "perfect tree" as (2^height - 1) first,
// because, regardless of the tree structure, we have to fill all positions of perfect tree (some
// positions are ""). Then we simply traverse tree passing level and start/end position for each
// subtree.
//
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
  public List<List<String>> printTree(TreeNode root) {
    int height = height(root);
    int len = (1 << height) - 1;
    List<List<String>> res = new ArrayList<List<String>>();
    for (int i = 0; i < height; i++) {
      res.add(new ArrayList<String>());
      for (int j = 0; j < len; j++) {
        res.get(i).add("");
      }
    }
    print(root, 0, 0, len-1, res);
    return res;
  }

  private int height(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(height(root.left), height(root.right));
  }

  private void print(TreeNode root, int level, int start, int end, List<List<String>> res) {
    if (root == null || start > end) return;
    int mid = (start + end) / 2;
    res.get(level).set(mid, "" + root.val);
    print(root.left, level+1, start, mid-1, res);
    print(root.right, level+1, mid+1, end, res);
  }
}
