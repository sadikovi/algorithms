// Solution with stack, runs in O(n) time, can be O(n^2) for certain inputs, IMHO.
// Uses O(n) space.
class Solution {
  public boolean isValidSerialization(String preorder) {
    if (preorder.length() == 0) return false;

    LinkedList<String> stack = new LinkedList<String>();
    String[] arr = preorder.split(",");

    for (int i = 0; i < arr.length; i++) {
      while (stack.size() >= 2 && arr[i].equals("#")) {
        String a = stack.poll(), b = stack.poll();
        if (!a.equals("#") || b.equals("#")) {
          stack.push(b);
          stack.push(a);
          break;
        }
      }
      stack.push(arr[i]);
    }
    return stack.size() == 1 && stack.peek().equals("#");
  }
}

// Another solution uses different approach, running time is O(n) and space is O(n):
// https://discuss.leetcode.com/topic/35976/7-lines-easy-java-solution
//
// In a binary tree, if we consider null as leaves, then all non-null nodes provide 2 outdegree
// and 1 indegree (2 children and 1 parent), except root all null nodes provide 0 outdegree and
// 1 indegree (0 child and 1 parent).
// Suppose we try to build this tree. During building, we record the difference between out degree
// and in degree `diff = outdegree - indegree`. When the next node comes, we then decrease diff
// by 1, because the node provides an in degree. If the node is not null, we increase diff by 2,
// because it provides two out degrees. If a serialization is correct, diff should never be
// negative and diff will be zero when finished.
class Solution {
  public boolean isValidSerialization(String preorder) {
    String[] nodes = preorder.split(",");
    int diff = 1;
    for (String node: nodes) {
      if (--diff < 0) return false;
      if (!node.equals("#")) diff += 2;
    }
    return diff == 0;
  }
}
