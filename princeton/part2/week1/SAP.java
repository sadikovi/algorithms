import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
  private static class Node {
    final int id;
    final int length;
    final Node prev;

    Node(int vertex, int length, Node prev) {
      this.id = vertex;
      this.length = length;
      this.prev = prev;
    }

    @Override
    public String toString() {
      return "id: " + id + "(" + length + ") -> " + prev;
    }
  }

  private static class Result {
    final int id;
    final int length;

    Result(int id, int length) {
      this.id = id;
      this.length = length;
    }
  }

  private final Digraph graph;

  // constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph G) {
    if (G == null) throw new IllegalArgumentException();
    this.graph = new Digraph(G);
  }

  // length of shortest ancestral path between v and w; -1 if no such path
  public int length(int v, int w) {
    List<Integer> vlist = new ArrayList<Integer>();
    vlist.add(v);

    List<Integer> wlist = new ArrayList<Integer>();
    wlist.add(w);

    return length(vlist, wlist);
  }

  // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
  public int ancestor(int v, int w) {
    List<Integer> vlist = new ArrayList<Integer>();
    vlist.add(v);

    List<Integer> wlist = new ArrayList<Integer>();
    wlist.add(w);

    return ancestor(vlist, wlist);
  }

  // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    return sap(v, w).length;
  }

  // a common ancestor that participates in shortest ancestral path; -1 if no such path
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    return sap(v, w).id;
  }

  private void checkVertices(Iterable<Integer> v) {
    if (v == null) throw new IllegalArgumentException();
    for (Integer elem : v) {
      if (elem == null) throw new IllegalArgumentException();
      if (elem < 0 || elem >= graph.V()) throw new IllegalArgumentException();
    }
  }

  private Result sap(Iterable<Integer> v, Iterable<Integer> w) {
    checkVertices(v);
    checkVertices(w);

    Node[] visitedV = bfs(v);
    Node[] visitedW = bfs(w);

    Result res = new Result(-1, -1);
    for (int i = 0; i < visitedV.length; i++) {
      if (visitedV[i] != null && visitedW[i] != null) {
        Result curr = new Result(i, visitedV[i].length + visitedW[i].length);
        if (res.length == -1 || curr.length < res.length) {
          res = curr;
        }
      }
    }

    return res;
  }

  private Node[] bfs(Iterable<Integer> start) {
    LinkedList<Node> queue = new LinkedList<Node>();
    Node[] visited = new Node[graph.V()];

    for (int id : start) {
      queue.add(new Node(id, 0, null));
    }

    while (!queue.isEmpty()) {
      Node curr = queue.poll();
      if (visited[curr.id] != null && visited[curr.id].length < curr.length) continue;
      visited[curr.id] = curr;

      for (int n : graph.adj(curr.id)) {
        queue.add(new Node(n, curr.length + 1, curr));
      }
    }

    return visited;
  }

  // do unit testing of this class
  public static void main(String[] args) {
    In in = new In(args[0]);
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    while (!StdIn.isEmpty()) {
      int v = StdIn.readInt();
      int w = StdIn.readInt();
      if (v < 0 || w < 0) {
        System.out.println("bye!");
        break;
      }
      int length   = sap.length(v, w);
      int ancestor = sap.ancestor(v, w);
      StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
  }
}
