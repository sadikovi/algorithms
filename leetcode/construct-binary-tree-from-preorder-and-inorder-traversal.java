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
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    return tree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
  }

  private TreeNode tree(int[] preorder, int pi, int pj, int[] inorder, int ii, int ij) {
    if (pi > pj) return null;
    TreeNode root = new TreeNode(preorder[pi]);
    int rootIndex = -1;
    for (int k = ii; k <= ij; k++) {
      if (preorder[pi] == inorder[k]) {
        rootIndex = k;
        break;
      }
    }
    root.left = tree(preorder, pi + 1, pi + (rootIndex - ii), inorder, ii, rootIndex - 1);
    root.right = tree(preorder, pi + (rootIndex - ii) + 1, pj, inorder, rootIndex + 1, ij);
    return root;
  }
}
