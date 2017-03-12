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
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> res = new LinkedList<List<Integer>>();
    if (root == null) return res;
    if (root.left == null && root.right == null) {
      if (root.val == sum) {
        List<Integer> l = new LinkedList<Integer>();
        l.add(root.val);
        res.add(l);
      }
    } else {
      List<List<Integer>> left = pathSum(root.left, sum - root.val);
      List<List<Integer>> right = pathSum(root.right, sum - root.val);
      for (List<Integer> l : left) {
        l.add(0, root.val);
        res.add(l);
      }
      for (List<Integer> l : right) {
        l.add(0, root.val);
        res.add(l);
      }
    }
    return res;
  }
}
