package test;

public class Test {
  public static class TreeNode {
    public int data;
    public TreeNode parent;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int data) {
      this.data = data;
    }

    @Override
    public String toString() {
      return "TreeNode(" + data + ", left=" + left + ", right=" + right + ")";
    }
  }

  public static TreeNode successor(TreeNode node) {
    if (node == null) return null;
    if (node.right != null) {
      TreeNode tmp = node.right;
      while (tmp.left != null) {
        tmp = tmp.left;
      }
      return tmp;
    } else {
      TreeNode parent = node.parent;
      TreeNode tmp = node;
      while (parent != null && parent.right == tmp) {
        tmp = parent;
        parent = parent.parent;
      }
      return parent;
    }
  }
}
