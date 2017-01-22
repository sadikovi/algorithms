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
      return "Node(" + this.data + ") -> " + this.next;
    }
  }

  public static Node intersection(Node a, Node b) {
    int sizeA = 0;
    int sizeB = 0;
    Node tmp;

    tmp = a;
    while (tmp != null) {
      sizeA++;
      tmp = tmp.next;
    }

    tmp = b;
    while (tmp != null) {
      sizeB++;
      tmp = tmp.next;
    }

    if (sizeA > sizeB) {
      a = advanceList(a, sizeA - sizeB);
    } else {
      b = advanceList(b, sizeB - sizeA);
    }

    while (a != null && b != null) {
      if (a == b) return a;
      a = a.next;
      b = b.next;
    }

    return null;
  }

  private static Node advanceList(Node head, int diff) {
    while (head != null && diff > 0) {
      head = head.next;
      diff--;
    }
    return head;
  }
}
