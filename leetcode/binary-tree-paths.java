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
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> lst = new ArrayList<String>();
    if (root == null) {
      return lst;
    } else if (root.left == null && root.right == null) {
      lst.add("" + root.val);
    } else {
      lst.addAll(binaryTreePaths(root.left));
      lst.addAll(binaryTreePaths(root.right));
      for (int i = 0; i < lst.size(); i++) {
        lst.set(i, root.val + "->" + lst.get(i));
      }
    }
    return lst;
  }
}
