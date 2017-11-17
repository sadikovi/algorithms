// Solution uses O(h) space, where h is the height of the BST.
// `next()` method takes O(h) in the worst cast, but should be roughly O(1) in average, maybe
// because it is amortized over the course of traversal.
// `hasNext()` takes O(1) time.
//
// Idea is using stack to store only branch of the tree and add elements as traversed. At each
// level only h elements are stored, where h is the height of the tree.
//

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
  private LinkedList<TreeNode> stack;

  public BSTIterator(TreeNode root) {
    stack = new LinkedList<TreeNode>();
    pushAll(root);
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  /** @return the next smallest number */
  public int next() {
    TreeNode node = stack.pop();
    pushAll(node.right);
    return node.val;
  }

  private void pushAll(TreeNode root) {
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
  }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
