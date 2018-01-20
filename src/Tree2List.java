// Solution to the problem of converting binary search tree into doubly linked list
// CTCI 17.12
public class Tree2List {
  public static class BiNode {
    int value;
    BiNode node1;
    BiNode node2;

    public BiNode(int value, BiNode node1, BiNode node2) {
      this.value = value;
      this.node1 = node1;
      this.node2 = node2;
    }

    @Override
    public String toString() {
      return "[" + this.value + "]";
    }
  }

  public static class Segment {
    BiNode head;
    BiNode tail;
  }

  // Convert tree to list (tree is BST, list is doubly linked list)
  public static BiNode tree2list(BiNode root) {
    if (root == null) return null;
    Segment seg = convert(root);
    return seg.head;
  }

  static Segment convert(BiNode root) {
    if (root == null) return null;
    Segment left = convert(root.node1);
    Segment right = convert(root.node2);
    Segment curr = new Segment();

    if (left == null) {
      root.node1 = null;
      curr.head = root;
    } else {
      root.node1 = left.tail;
      left.tail.node2 = root;
      curr.head = left.head;
    }

    if (right == null) {
      root.node2 = null;
      curr.tail = root;
    } else {
      root.node2 = right.head;
      right.head.node1 = root;
      curr.tail = right.tail;
    }

    return curr;
  }

  // == Testing ==

  public static void solution(int[] arr) {
    java.util.Arrays.sort(arr);
    BiNode bst = bst(arr, 0, arr.length - 1);
    BiNode list = tree2list(bst);
    System.out.println("List from left to right:");
    BiNode tmp = list;
    while (tmp != null) {
      System.out.println(tmp);
      tmp = tmp.node2;
    }
    tmp = list;
    while (tmp != null && tmp.node2 != null) {
      tmp = tmp.node2;
    }
    System.out.println("List from right to left:");
    while (tmp != null) {
      System.out.println(tmp);
      tmp = tmp.node1;
    }
  }

  private static BiNode bst(int[] arr, int start, int end) {
    if (start > end) return null;
    int mid = (start + end) / 2;
    return new BiNode(arr[mid], bst(arr, start, mid - 1), bst(arr, mid + 1, end));
  }
}
