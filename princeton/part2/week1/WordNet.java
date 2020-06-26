import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {
  private static class Vertex {
    final int id;
    final String synset;

    Vertex(int id, String synset) {
      this.id = id;
      this.synset = synset;
    }

    @Override
    public String toString() {
      return "{" + id + ", \"" + synset + "\"}";
    }
  }

  private static class Node {
    final Vertex v;
    final int length;
    final Node prev;

    Node(Vertex v, int length, Node prev) {
      this.v = v;
      this.length = length;
      this.prev = prev;
    }

    @Override
    public String toString() {
      return "v: " + v + "(" + length + ") -> " + prev;
    }
  }

  private static class Result {
    final Vertex v;
    final int length;

    Result(Vertex v, int length) {
      this.v = v;
      this.length = length;
    }
  }

  private final HashMap<String, List<Vertex>> nouns;
  private final HashMap<Vertex, List<Vertex>> graph;

  // constructor takes the name of the two input files
  public WordNet(String synsets, String hypernyms) {
    if (synsets == null || hypernyms == null) {
      throw new IllegalArgumentException();
    }

    nouns = new HashMap<String, List<Vertex>>();
    graph = new HashMap<Vertex, List<Vertex>>();

    In synInput = new In(synsets);

    List<Vertex> vertices = new ArrayList<Vertex>();
    while (synInput.hasNextLine()) {
      String[] args = synInput.readLine().split(",");
      Vertex v = new Vertex(Integer.valueOf(args[0]), args[1]);
      String[] narr = args[1].split(" ");

      for (int i = 0; i < narr.length; i++) {
        if (!nouns.containsKey(narr[i])) {
          nouns.put(narr[i], new ArrayList<Vertex>());
        }
        nouns.get(narr[i]).add(v);
      }

      vertices.add(v);
    }

    In hypInput = new In(hypernyms);

    while (hypInput.hasNextLine()) {
      String[] args = hypInput.readLine().split(",");
      Vertex v = vertices.get(Integer.valueOf(args[0]));

      if (!graph.containsKey(v)) {
        graph.put(v, new ArrayList<Vertex>());
      }
      List<Vertex> adjacent = graph.get(v);

      for (int i = 1; i < args.length; i++) {
        adjacent.add(vertices.get(Integer.valueOf(args[i])));
      }
    }

    boolean foundRoot = false;
    for (Vertex v : vertices) {
      if (graph.containsKey(v) && graph.get(v).isEmpty()) {
        if (foundRoot) throw new IllegalArgumentException("More than one root");
        foundRoot = true;
      }
    }

    if (!foundRoot) throw new IllegalArgumentException("No root");

    // Check if there is a cycle
    topologicalSort();
  }

  // We don't maintain the stack since we only need to check if there are any cycles - we don't
  // need full topological order.
  private void topologicalSort() {
    boolean[] visiting = new boolean[graph.size()];
    boolean[] visited = new boolean[graph.size()];

    for (Vertex v : graph.keySet()) {
      if (!visited[v.id]) dfs(v, visiting, visited);
    }
  }

  private void dfs(Vertex v, boolean[] visiting, boolean[] visited) {
    visiting[v.id] = true;
    for (Vertex n : graph.get(v)) {
      if (!visited[n.id]) {
        if (visiting[n.id]) throw new IllegalArgumentException("Cycle");
        dfs(n, visiting, visited);
      }
    }
    visited[v.id] = true;
  }

  // returns all WordNet nouns
  public Iterable<String> nouns() {
    // defensive copy
    return new ArrayList<String>(nouns.keySet());
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    if (word == null) throw new IllegalArgumentException();
    return nouns.containsKey(word);
  }

  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB) {
    if (nounA == null || nounB == null) {
      throw new IllegalArgumentException();
    }

    if (!nouns.containsKey(nounA) || !nouns.containsKey(nounB)) {
      throw new IllegalArgumentException();
    }

    return findSAP(nouns.get(nounA), nouns.get(nounB)).length;
  }

  // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
  // in a shortest ancestral path (defined below)
  public String sap(String nounA, String nounB) {
    if (nounA == null || nounB == null) {
      throw new IllegalArgumentException();
    }

    if (!nouns.containsKey(nounA) || !nouns.containsKey(nounB)) {
      throw new IllegalArgumentException();
    }

    return findSAP(nouns.get(nounA), nouns.get(nounB)).v.synset;
  }

  private Result findSAP(List<Vertex> a, List<Vertex> b) {
    Node[] visitedA = bfs(a);
    Node[] visitedB = bfs(b);

    Result res = new Result(null, -1);
    for (int i = 0; i < visitedA.length; i++) {
      if (visitedA[i] != null && visitedB[i] != null) {
        Result curr = new Result(visitedA[i].v, visitedA[i].length + visitedB[i].length);
        if (res.length == -1 || curr.length < res.length) {
          res = curr;
        }
      }
    }

    return res;
  }

  private Node[] bfs(List<Vertex> start) {
    LinkedList<Node> queue = new LinkedList<Node>();
    Node[] visited = new Node[graph.size()];

    for (Vertex v : start) {
      queue.add(new Node(v, 0, null));
    }

    while (!queue.isEmpty()) {
      Node curr = queue.poll();
      visited[curr.v.id] = curr;

      List<Vertex> neighbours = graph.get(curr.v);
      if (neighbours != null) {
        for (Vertex n : neighbours) {
          if (visited[n.id] == null) {
            visited[n.id] = new Node(n, curr.length + 1, curr);
            queue.add(visited[n.id]);
          }
        }
      }
    }

    return visited;
  }

  // do unit testing of this class
  public static void main(String[] args) {
    WordNet wnet = new WordNet(args[0], args[1]);
    while (!StdIn.isEmpty()) {
      String[] nouns = StdIn.readLine().split(" ");
      if (nouns.length < 2) {
        System.out.println("bye!");
        break;
      }
      String synset = wnet.sap(nouns[0], nouns[1]);
      int distance = wnet.distance(nouns[0], nouns[1]);
      StdOut.printf("distance = %d, synset = %s\n", distance, synset);
    }
  }
}
