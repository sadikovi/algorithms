package test;

public class Test {
  public static class Node {
    public int data;
    public Node next;

    public Node(int x) {
      this.data = x;
      this.next = null;
    }

    @Override
    public String toString() {
      return "Node(" + this.data + ") -> ";
    }
  }

  // Time complexity is O(len(list))
  // Space complexity is O(len(list))
  public static Node loopDetection1(Node head) {
    java.util.HashSet<Node> lookup = new java.util.HashSet<Node>();
    Node tmp = head;
    while (tmp != null) {
      if (lookup.contains(tmp)) return tmp;
      lookup.add(tmp);
      tmp = tmp.next;
    }
    return null;
  }

  // Time complexity is O(len(list))
  // Space complexity is O(1)
  public static Node loopDetection2(Node head) {
    if (head == null || head.next == null) return null;
    Node slow = head.next;
    Node fast = head.next.next;
    while (slow != null && fast != null && fast.next != null) {
      if (slow == fast) break;
      slow = slow.next;
      fast = fast.next.next;
    }
    fast = head;
    while (fast != null && slow != null) {
      if (fast == slow) return slow;
      fast = fast.next;
      slow = slow.next;
    }
    return null;
  }
}
