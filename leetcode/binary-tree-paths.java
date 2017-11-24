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

// Alternative solution, slower than solution above, runs in O(n^2) time where n is a number of
// nodes in the tree (string length is the same order as number of nodes, actually larger by
// constant factor).
class Solution {
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> res = new ArrayList<String>();
    paths(root, "", res);
    return res;
  }

  private void paths(TreeNode root, String path, List<String> res) {
    if (root == null) return;
    if (root.left == null && root.right == null) {
      res.add(path + root.val);
    } else {
      paths(root.left, path + root.val + "->", res);
      paths(root.right, path + root.val + "->", res);
    }
  }
}

// Improved solution similar to the first solution, but it is top-down and we use string builder.
class Solution {
  public List<String> binaryTreePaths(TreeNode root) {
      List<String> res = new ArrayList<String>();
      paths(root, new StringBuilder(), res);
      return res;
  }

  private void paths(TreeNode root, StringBuilder path, List<String> res) {
    if (root == null) return;
    if (root.left == null && root.right == null) {
      path.append(root.val);
      res.add(path.toString());
    } else {
      path.append(root.val + "->");
      int len = path.length();
      paths(root.left, path, res);
      path.setLength(len); // reset length
      paths(root.right, path, res);
    }
  }
}
