// Fairly naive solution where at each level we search inorder array to find the partition index.
// This results in O(n^2) time in worst case and O(n) space complexity, because you will have to
// recursively scan N elements when tree is skewed on one side (in particularly,
// when [1, 2, 3, 4] inorder and [4, 3, 2, 1] preorder)

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
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length != inorder.length) return null;
    return tree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
  }

  private TreeNode tree(int[] preorder, int i, int j, int[] inorder, int p, int q) {
    if (i > j || p > q) return null;
    TreeNode root = new TreeNode(preorder[i]);
    int k = p;
    while (k <= q) {
      if (inorder[k] == preorder[i]) break;
      k++;
    }
    root.left = tree(preorder, i + 1, i + k - p, inorder, p, k - 1);
    root.right = tree(preorder, i + k - p + 1, j, inorder, k + 1, q);
    return root;
  }
}

// Improved solution where we use HashMap to locate partition index.
// This runs in O(n) time and has O(n) space complexity.

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
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length != inorder.length) return null;
    HashMap<Integer, Integer> in = new HashMap<Integer, Integer>();
    for (int i = 0; i < inorder.length; i++) {
      in.put(inorder[i], i);
    }
    return tree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, in);
  }

  private TreeNode tree(int[] preorder, int i, int j, int[] inorder, int p, int q, HashMap<Integer, Integer> in) {
    if (i > j) return null;
    TreeNode root = new TreeNode(preorder[i]);
    int k = in.get(preorder[i]);
    root.left = tree(preorder, i + 1, i + k - p, inorder, p, k - 1, in);
    root.right = tree(preorder, i + k - p + 1, j, inorder, k + 1, q, in);
    return root;
  }
}
