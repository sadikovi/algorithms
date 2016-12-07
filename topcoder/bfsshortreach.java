import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  public static class Node {
    int n;
    int distance = 0;
    boolean visited = false;
    Node(int n) { this.n = n; }
  }

  private static void findPaths(Node s, Node[] nodes, HashMap<Node, List<Node>> edges) {
    LinkedList<Node> queue = new LinkedList<Node>();
    queue.add(s);
    while (!queue.isEmpty()) {
      Node n = queue.poll();
      if (edges.containsKey(n)) {
        for (Node adj : edges.get(n)) {
          if (!adj.visited) {
            adj.distance = n.distance + 6;
            queue.add(adj);
          }
          adj.visited = true;
        }
      }
    }

    for (Node n : nodes) {
      if (n != s) {
        if (n.distance == 0) {
          n.distance = -1;
        }
        System.out.print(n.distance + " ");
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int q = in.nextInt();
    for (int x = 0; x < q; x++) {
      int n = in.nextInt();
      Node[] nodes = new Node[n];
      for (int i = 1; i <= n; i++) {
        nodes[i - 1] = new Node(i);
      }

      HashMap<Node, List<Node>> edges = new HashMap<Node, List<Node>>();
      int m = in.nextInt();
      for (int i = 1; i <= m; i++) {
        Node u = nodes[in.nextInt() - 1];
        Node v = nodes[in.nextInt() - 1];

        if (!edges.containsKey(u)) {
          edges.put(u, new ArrayList<Node>());
        }
        if (!edges.containsKey(v)) {
          edges.put(v, new ArrayList<Node>());
        }
        edges.get(u).add(v);
        edges.get(v).add(u);
      }

      Node s = nodes[in.nextInt() - 1];

      findPaths(s, nodes, edges);
    }
  }
}
