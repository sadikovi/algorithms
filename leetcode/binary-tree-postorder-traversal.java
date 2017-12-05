// Trivial solution with recursion, runs in O(n) time and O(n) space.
class Solution {
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    postorder(root, res);
    return res;
  }

  private void postorder(TreeNode root, List<Integer> res) {
    if (root != null) {
      postorder(root.left, res);
      postorder(root.right, res);
      res.add(root.val);
    }
  }
}

// Iterative solution (no tree modification), runs in O(n) time and O(n) space.
//
// Idea is always adding left node branch to the stack. Then checking if current node has right
// child that equals to the last processing node or null, if so - we have processed both children,
// add to the list, otherwise add right child and all its left children, and so on until stack is
// empty.
class Solution {
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    while (root != null) {
      stack.push(root);
      root = root.left;
    }

    TreeNode last = null;
    while (!stack.isEmpty()) {
      root = stack.peek();
      if (root.right == null || root.right == last) {
        // add right and all left nodes for it
        last = stack.peek();
        res.add(stack.pop().val);
      } else {
        root = root.right;
        while (root != null) {
          stack.push(root);
          root = root.left;
        }
      }
    }

    return res;
  }
}
