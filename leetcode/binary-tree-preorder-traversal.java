// Solution runs in O(n) time and O(n) space (stack of TreeNode). Idea is always adding left
// children, and adding right children only if the last processing node was not immediate right
// child. If it is, then this means we added both left and right children for the node and need to
// remove it from the stack.
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
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

    TreeNode last = null;
    addLeft(root, stack, res);
    while (!stack.isEmpty()) {
      TreeNode node = stack.peek();
      if (node.right != null && node.right != last) {
        addLeft(node.right, stack, res);
      } else {
        last = stack.pop();
      }
    }

    return res;
  }

  private void addLeft(TreeNode root, LinkedList<TreeNode> stack, List<Integer> res) {
    while (root != null) {
      res.add(root.val);
      stack.push(root);
      root = root.left;
    }
  }
}
