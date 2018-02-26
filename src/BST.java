public class BST<T extends Comparable<T>> {
  static class TreeNode<T> {
    T value;
    TreeNode<T> left;
    TreeNode<T> right;

    TreeNode(T value) {
      this.value = value;
      this.left = null;
      this.right = null;
    }

    @Override
    public String toString() {
      return "[" + value + ", left: " + this.left + ", right: " + this.right + "]";
    }
  }

  private TreeNode<T> root;

  public BST() {
    this.root = null;
  }

  private TreeNode<T> insert(TreeNode<T> node, T value) {
    if (node == null) return new TreeNode<T>(value);
    if (node.value.compareTo(value) < 0) {
      node.right = insert(node.right, value);
    } else if (node.value.compareTo(value) > 0) {
      node.left = insert(node.left, value);
    }
    return node;
  }

  public void insert(T value) {
    this.root = insert(this.root, value);
  }

  private boolean search(TreeNode<T> node, T value) {
    if (node == null) return false;
    if (node.value.compareTo(value) == 0) return true;
    if (node.value.compareTo(value) < 0) {
      return search(node.right, value);
    } else {
      return search(node.left, value);
    }
  }

  public boolean search(T value) {
    return search(this.root, value);
  }

  private TreeNode<T> remove(TreeNode<T> node, T value) {
    if (node == null) return node;
    if (node.value.compareTo(value) == 0) {
      if (node.left == null) return node.right;
      if (node.right == null) return node.left;
      TreeNode<T> tmp = node.left;
      while (tmp.right != null) {
        tmp = tmp.right;
      }
      tmp.right = node.right.left;
      node.right.left = node.left;
      return node.right;
    } else if (node.value.compareTo(value) < 0) {
      node.right = remove(node.right, value);
    } else {
      node.left = remove(node.left, value);
    }
    return node;
  }

  public void remove(T value) {
    this.root = remove(this.root, value);
  }

  @Override
  public String toString() {
    return "BST: " + this.root;
  }
}
