// Solution is based on BFS.
// Runtime is O(A^n + D), where A is 10 digits, n is the number of slots (4) and D is the size of
// the deadends list (visited set).
// Space complexity is O(A^n + D).
class Solution {
  static class Node {
    String s;
    int cnt;

    Node(String s, int c) {
      this.s = s;
      this.cnt = c;
    }
  }

  public int openLock(String[] deadends, String target) {
    HashSet<String> visited = new HashSet<String>();
    for (String d : deadends) {
      visited.add(d);
    }

    LinkedList<Node> q = new LinkedList<Node>();
    q.add(new Node("0000", 0));

    int min = Integer.MAX_VALUE;
    while (!q.isEmpty()) {
      Node curr = q.remove();
      if (curr.s.equals(target)) {
        min = Math.min(min, curr.cnt);
      } else if (!visited.contains(curr.s)) {
        for (int i = 0; i < curr.s.length(); i++) {
          q.add(new Node(decr(curr.s, i), curr.cnt + 1));
          q.add(new Node(incr(curr.s, i), curr.cnt + 1));
        }
        visited.add(curr.s);
      }
    }

    return min == Integer.MAX_VALUE ? -1 : min;
  }

  private String decr(String s, int pos) {
    int t = s.charAt(pos) - '0';
    t = t == 0 ? 9 : (t - 1);
    return s.substring(0, pos) + (char) (t + '0') + s.substring(pos + 1);
  }

  private String incr(String s, int pos) {
    int t = s.charAt(pos) - '0';
    t = t == 9 ? 0 : (t + 1);
    return s.substring(0, pos) + (char) (t + '0') + s.substring(pos + 1);
  }
}
