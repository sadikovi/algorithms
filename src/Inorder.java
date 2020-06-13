import java.util.ArrayList;
import java.util.List;

public class Inorder {
  static class TreeNode {
    int val;
    TreeNode parent;
    TreeNode left;
    TreeNode right;

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;

      if (left != null) left.parent = this;
      if (right != null) right.parent = this;
    }

    @Override
    public String toString() {
      return "[" + val + "]";
    }
  }

  private static void inorder(TreeNode root, List<Integer> values) {
    if (root == null) return;
    inorder(root.left, values);
    values.add(root.val);
    inorder(root.right, values);
  }

  private static void inorderConstant(TreeNode root, List<Integer> values) {
    TreeNode prev = null;
    TreeNode curr = root;

    while (curr != null) {
      if (curr.left != null && curr.parent == prev) {
        prev = curr;
        curr = curr.left;
      } else if (curr.right != null && curr.right != prev) {
        values.add(curr.val);
        prev = curr;
        curr = curr.right;
      } else {
        if (curr.right != prev) {
          values.add(curr.val);
        }
        prev = curr;
        curr = curr.parent;
      }
    }
  }

  public static void main(String[] args) {
    List<Integer> values = new ArrayList<Integer>();

    TreeNode root = new TreeNode(5,
      new TreeNode(3,
        new TreeNode(2, null, null),
        new TreeNode(4, null, null)
      ),
      new TreeNode(7,
        new TreeNode(6, null, null),
        new TreeNode(8, null, null)
      )
    );

    inorder(root, values);
    System.out.println(values);
    values.clear();
    inorderConstant(root, values);
    System.out.println(values);
  }
}
