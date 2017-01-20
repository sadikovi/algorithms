package test;

/*
scala> test.Test.hanoi(3)
== STEP 2 ==
Tower 1: [3]
Tower 2: []
Tower 3: [2, 1]

== STEP 2 ==
Tower 1: []
Tower 2: [3]
Tower 3: [2, 1]

== STEP 2 ==
Tower 1: []
Tower 2: []
Tower 3: [3, 2, 1]

== STEP 3 ==
Tower 1: []
Tower 2: []
Tower 3: [3, 2, 1]
*/

import java.util.Stack;

public class Test {
  public static void hanoi(int disks) {
    if (disks <= 0) return;
    Stack<Integer> one = new Stack<Integer>();
    Stack<Integer> two = new Stack<Integer>();
    Stack<Integer> three = new Stack<Integer>();
    while (disks > 0) {
      one.push(disks);
      disks--;
    }
    hanoi(one, two, three);
  }

  private static void hanoi(Stack<Integer> one, Stack<Integer> two, Stack<Integer> three) {
    move(one, two, three, one.size());
  }

  private static void move(Stack<Integer> one, Stack<Integer> two, Stack<Integer> three, int n) {
    if (n <= 0) return;
    if (n == 1) {
      moveSingleDisk(one, three);
    } else {
      move(one, two, three, n - 1);
      move(three, one, two, n - 1);
      move(one, two, three, 1);
      move(two, one, three, n - 1);

      System.out.println("== STEP " + n + " ==");
      System.out.println("Tower 1: " + one);
      System.out.println("Tower 2: " + two);
      System.out.println("Tower 3: " + three);
      System.out.println();
    }
  }

  private static void moveSingleDisk(Stack<Integer> from, Stack<Integer> to) {
    if (!canMove(from, to)) {
      throw new IllegalStateException("Cannot move disk from " + from + " to " + to);
    }
    to.push(from.pop());
  }

  private static boolean canMove(Stack<Integer> from, Stack<Integer> to) {
    if (from.isEmpty()) return false;
    if (to.isEmpty() || to.peek() > from.peek()) return true;
    return false;
  }
}
