package test;

/*
import java.util._
val lst = new ArrayList[Stack[java.lang.Integer]]()
lst.add(new Stack[java.lang.Integer]())
lst.add(new Stack[Integer]())
lst.add(new Stack[Integer]())
lst.get(0).push(1)
lst.get(0).push(1)
lst.get(0).push(100)
lst.get(0).push(3)

lst.get(1).push(2000)
lst.get(1).push(2)
lst.get(1).push(3)
lst.get(1).push(1)

lst.get(2).push(10)
lst.get(2).push(1)
lst.get(2).push(4)

test.Test.maxSum(lst, 3)
// returns 107
test.Test.maxSumGreedy(lst, 3)
// returns 107
*/

import java.util.List;
import java.util.Stack;

public class Test {
  public static int maxSum(List<Stack<Integer>> stacks, int m) {
    if (stacks.isEmpty() || m <= 0) return 0;
    int sum = 0;
    for (int i = 0; i < m; i++) {
      for (Stack<Integer> stack : stacks) {
        int elem = stack.pop();
        int tsum = maxSum(stacks, m - 1);
        sum = Math.max(sum, tsum + elem);
        stack.push(elem);
      }
    }
    return sum;
  }

  // Suboptimal greedy solution, but runs in O(nm) time
  public static int maxSumGreedy(List<Stack<Integer>> stacks, int m) {
    if (stacks.isEmpty() || m <= 0) return -1;
    int sum = 0;
    while (m > 0) {
      int max = Integer.MIN_VALUE;
      int index = -1;
      for (int i = 0; i < stacks.size(); i++) {
        if (!stacks.get(i).isEmpty()) {
          int elem = stacks.get(i).peek();
          if (elem > max) {
            index = i;
            max = elem;
          }
        }
      }
      if (index < 0) return -1;
      sum += stacks.get(index).pop();
      m--;
    }
    return sum;
  }
}
