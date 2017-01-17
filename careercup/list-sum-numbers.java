package test;

/*
scala> import test.Test._
import test.Test._

scala> val num1 = new Node(1, new Node(9, new Node(2, new Node(1, null))))
num1: test.Test.Node = Node(1) -> Node(9) -> Node(2) -> Node(1) -> null

scala> val num2 = new Node(6, new Node(7, new Node(2, null)))
num2: test.Test.Node = Node(6) -> Node(7) -> Node(2) -> null

scala> test.Test.sumRecur(num1, num2)
res1: test.Test.Node = Node(2) -> Node(5) -> Node(9) -> Node(3) -> null

scala> 1921 + 672
res2: Int = 2593

scala> test.Test.sumRecur(new Node(2, null), new Node(8, null))
res3: test.Test.Node = Node(1) -> Node(0) -> null
*/

import java.util.LinkedList;

public class Test {
  public static class Node {
    int data;
    Node next;

    public Node() { }

    public Node(int data, Node next) {
      this.data = data;
      this.next = next;
    }

    @Override
    public String toString() {
      return "Node(" + this.data + ") -> " + this.next;
    }
  }

  // sum nodes iteratively with left->right order of numbers, e.g.
  // num1 = Node(1) -> Node(9) -> Node(2) -> Node(1) -> null
  // num2 = Node(6) -> Node(7) -> Node(2) -> null
  // result = Node(2) -> Node(5) -> Node(9) -> Node(3) -> null
  public static Node sumIter(Node num1, Node num2) {
    LinkedList<Integer> s1 = new LinkedList<Integer>();
    while (num1 != null) {
      s1.push(num1.data);
      num1 = num1.next;
    }

    LinkedList<Integer> s2 = new LinkedList<Integer>();
    while (num2 != null) {
      s2.push(num2.data);
      num2 = num2.next;
    }

    int carry = 0;
    Node res = null;
    while (!s1.isEmpty() || !s2.isEmpty()) {
      int sum = carry;
      sum += s1.isEmpty() ? 0 : s1.pop();
      sum += s2.isEmpty() ? 0 : s2.pop();
      Node t = new Node();
      t.data = sum % 10;
      carry = (sum >= 10) ? 1 : 0;
      if (res == null) {
        res = t;
      } else {
        t.next = res;
        res = t;
      }
    }

    if (carry > 0) {
      Node t = new Node();
      t.data = carry;
      t.next = res;
      res = t;
    }

    return res;
  }

  static class PartialSum {
    int carry;
    Node value;
  }

  // Recursive solution for the same list problem, idea is padding shorter number with 0s, so
  // recursion works correctly.
  public static Node sumRecur(Node num1, Node num2) {
    // pad shorter list with zeros
    int size1 = size(num1);
    int size2 = size(num2);
    if (size1 < size2) {
      num1 = padLeft(num1, size2 - size1);
    } else {
      num2 = padLeft(num2, size1 - size2);
    }

    PartialSum sum = partialSum(num1, num2);
    if (sum.carry > 0) {
      Node t = new Node();
      t.data = sum.carry;
      t.next = sum.value;
      sum.value = t;
    }
    return sum.value;
  }

  private static PartialSum partialSum(Node num1, Node num2) {
    PartialSum sum = new PartialSum();
    // by default sum has data = 0;
    if (num1 == null && num2 == null) return sum;
    sum = partialSum((num1 != null) ? num1.next : num1, (num2 != null) ? num2.next : num2);
    int value = sum.carry;
    if (num1 != null) {
      value += num1.data;
    }
    if (num2 != null) {
      value += num2.data;
    }

    Node t = new Node();
    t.data = value % 10;
    sum.carry = (value >= 10) ?  1 : 0;
    t.next = sum.value;
    sum.value = t;
    return sum;
  }

  private static int size(Node num) {
    Node tmp = num;
    int size = 0;
    while (tmp != null) {
      tmp = tmp.next;
      size++;
    }
    return size;
  }

  private static Node padLeft(Node num, int pad) {
    while (pad > 0) {
      Node n = new Node();
      n.data = 0;
      n.next = num;
      num = n;
      pad--;
    }
    return num;
  }
}
