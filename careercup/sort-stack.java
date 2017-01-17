package test;

/*
val stack = new java.util.Stack[Integer]()
stack.push(6)
stack.push(5)
stack.push(3)
stack.push(1)
stack.push(4)
stack.push(2)

scala> stack
res17: java.util.Stack[Integer] = [6, 5, 3, 1, 4, 2]

scala> test.Test.sortStackBetter(stack)

scala> stack
res19: java.util.Stack[Integer] = [6, 5, 4, 3, 2, 1]
*/
import java.util.Stack;

public class Test {
  private static void sortedPush(Stack<Integer> stack, int elem) {
    Stack<Integer> tmp = new Stack<Integer>();
    while (!stack.isEmpty() && stack.peek() < elem) {
      tmp.push(stack.pop());
    }
    stack.push(elem);
    while (!tmp.isEmpty()) {
      stack.push(tmp.pop());
    }
  }

  // Uses O(n^2) time and O(n) of space, but it uses 2 stacks at any given moment, which is
  // more than allowed by question, see better solution below
  public static void sortStackNaive(Stack<Integer> stack) {
    Stack<Integer> tmp = new Stack<Integer>();
    while (!stack.isEmpty()) {
      tmp.push(stack.pop());
    }

    while (!tmp.isEmpty()) {
      sortedPush(stack, tmp.pop());
    }
  }

  // Runs in O(n^2) time and O(n) space and uses only one additional stack
  public static void sortStackBetter(Stack<Integer> stack) {
    Stack<Integer> tmp = new Stack<Integer>();
    while (!stack.isEmpty()) {
      int elem = stack.pop();
      int count = 0;
      while (!tmp.isEmpty() && tmp.peek() > elem) {
        stack.push(tmp.pop());
        count++;
      }
      tmp.push(elem);
      while (count > 0) {
        tmp.push(stack.pop());
        count--;
      }
    }

    // tmp stack has elements in decreasing order (largest element on top)
    // shift them back into stack to have smallest element on top
    // we could avoid this by pushing elements in reversed order and returning tmp
    while (!tmp.isEmpty()) {
      stack.push(tmp.pop());
    }
  }
}
