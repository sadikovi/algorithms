import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BaseballElimination {
  private final HashMap<String, Integer> teams;
  private final HashMap<Integer, String> teamsInverse;
  private final int n;
  private final int[] w;
  private final int[] l;
  private final int[] r;
  private final int[][] g;
  private final boolean[] eliminated;
  private final HashMap<Integer, List<String>> eliminations;

  // create a baseball division from given filename in format specified below
  public BaseballElimination(String filename) {
    In in = new In(filename);

    this.n = Integer.valueOf(in.readLine());
    this.teams = new HashMap<String, Integer>();
    this.teamsInverse = new HashMap<Integer, String>();
    this.w = new int[n];
    this.l = new int[n];
    this.r = new int[n];
    this.g = new int[n][n];

    for (int i = 0; i < n; i++) {
      String team = in.readString();
      teams.put(team, i);
      teamsInverse.put(i, team);
      w[i] = in.readInt();
      l[i] = in.readInt();
      r[i] = in.readInt();
      for (int j = 0; j < n; j++) {
        g[i][j] = in.readInt();
      }
    }

    this.eliminated = new boolean[n];
    this.eliminations = new HashMap<Integer, List<String>>();
  }

  // number of teams
  public int numberOfTeams() {
    return n;
  }

  // all teams
  public Iterable<String> teams() {
    return new ArrayList<String>(teams.keySet());
  }

  // number of wins for given team
  public int wins(String team) {
    if (team == null || !teams.containsKey(team)) throw new IllegalArgumentException();
    return w[teams.get(team)];
  }

  // number of losses for given team
  public int losses(String team) {
    if (team == null || !teams.containsKey(team)) throw new IllegalArgumentException();
    return l[teams.get(team)];
  }

  // number of remaining games for given team
  public int remaining(String team) {
    if (team == null || !teams.containsKey(team)) throw new IllegalArgumentException();
    return r[teams.get(team)];
  }

  // number of remaining games between team1 and team2
  public int against(String team1, String team2) {
    if (team1 == null || !teams.containsKey(team1)) throw new IllegalArgumentException();
    if (team2 == null || !teams.containsKey(team2)) throw new IllegalArgumentException();
    return g[teams.get(team1)][teams.get(team2)];
  }

  // is given team eliminated?
  public boolean isEliminated(String team) {
    if (team == null || !teams.containsKey(team)) throw new IllegalArgumentException();
    int x = teams.get(team);
    checkElimination(x);
    return eliminated[x];
  }

  // subset R of teams that eliminates given team; null if not eliminated
  public Iterable<String> certificateOfElimination(String team) {
    if (team == null || !teams.containsKey(team)) throw new IllegalArgumentException();
    int x = teams.get(team);
    checkElimination(x);
    List<String> res = eliminations.get(x);
    if (res == null) return null;
    return new ArrayList<String>(res);
  }

  private void checkElimination(int team) {
    if (!eliminations.containsKey(team)) {
      // Trivial elimination
      for (int i = 0; i < n; i++) {
        if (i != team && w[team] + r[team] < w[i]) {
          eliminated[team] = true;
          List<String> eliminationTeams = new ArrayList<String>();
          eliminationTeams.add(teamsInverse.get(i));
          eliminations.put(team, eliminationTeams);
          return;
        }
      }

      // Non-trivial elimination
      FordFulkerson alg = new FordFulkerson(graph(team));
      eliminated[team] = alg.isEliminated();
      List<String> res = null;
      if (alg.isEliminated()) {
        res = new ArrayList<String>();
        for (int id : alg.set()) {
          res.add(teamsInverse.get(id));
        }
      }
      eliminations.put(team, res);
    }
  }

  private static class Graph {
    final int V;
    final HashMap<Integer, List<Edge>> adj;
    final HashMap<String, Integer> map;

    Graph(int V, HashMap<Integer, List<Edge>> adj, HashMap<String, Integer> map) {
      this.V = V;
      this.adj = adj;
      this.map = map;
    }

    @Override
    public String toString() {
      return "V: " + V + ", adj: " + adj + ", map: " + map;
    }
  }

  private static class Edge {
    private final int from;
    private final int to;
    private final int capacity;
    private int flow;

    Edge(int from, int to, int capacity, int flow) {
      this.from = from;
      this.to = to;
      this.capacity = capacity;
      this.flow = flow;
    }

    public int other(int vertex) {
      if (from == vertex) return to;
      if (to == vertex) return from;
      throw new IllegalArgumentException("Invalid vertex " + vertex);
    }

    public int residualCapacityTo(int vertex) {
      if (from == vertex) return flow;
      if (to == vertex) return capacity == Integer.MAX_VALUE ? capacity : (capacity - flow);
      throw new IllegalArgumentException("Invalid vertex " + vertex);
    }

    public void addResidualFlowTo(int vertex, int delta) {
      if (from == vertex) {
        flow -= delta;
      } else if (to == vertex) {
        flow += delta;
      } else {
        throw new IllegalArgumentException("Invalid vertex " + vertex);
      }
      if (flow < 0) throw new IllegalArgumentException("Flow is negative");
      if (flow > capacity) throw new IllegalArgumentException("Flow exceeds capacity");
    }

    @Override
    public String toString() {
      String cap = capacity == Integer.MAX_VALUE ? "INF" : ("" + capacity);
      return "{" + from + " - (" + flow + "/" + cap + ") -> " + to + "}";
    }
  }

  private Graph graph(int team) {
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    int vid = 0;

    map.put("s", vid++);
    map.put("t", vid++);
    for (int i = 0; i < n; i++) {
      if (i != team) {
        map.put("" + i, vid++);
      }
    }
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (i != team && j != team) {
          map.put(i + "-" + j, vid++);
        }
      }
    }

    int V = vid;

    HashMap<Integer, List<Edge>> graph = new HashMap<Integer, List<Edge>>();
    for (int i = 0; i < V; i++) {
      graph.put(i, new ArrayList<Edge>());
    }

    int s = map.get("s");
    int t = map.get("t");

    for (int i = 0; i < n; i++) {
      if (i != team) {
        int v = map.get("" + i);
        Edge edge = new Edge(v, t, w[team] + r[team] - w[i], 0);
        graph.get(v).add(edge);
        graph.get(t).add(edge);
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (i != team && j != team) {
          int game = map.get(i + "-" + j);
          Edge edge = new Edge(s, game, g[i][j], 0);

          graph.get(s).add(edge);
          graph.get(game).add(edge);

          // add team vertices
          int ti = map.get("" + i);
          int tj = map.get("" + j);

          Edge gi = new Edge(game, ti, Integer.MAX_VALUE, 0);
          graph.get(ti).add(gi);
          graph.get(game).add(gi);

          Edge gj = new Edge(game, tj, Integer.MAX_VALUE, 0);
          graph.get(tj).add(gj);
          graph.get(game).add(gj);
        }
      }
    }

    return new Graph(V, graph, map);
  }

  private static class FordFulkerson {
    private boolean[] marked;
    private Edge[] edgeTo;
    private int value; // max flow

    private boolean isEliminated;
    private List<Integer> set;

    FordFulkerson(Graph G) {
      int s = G.map.get("s");
      int t = G.map.get("t");

      int value = excess(G, t);
      while (hasAugmentingPath(G, s, t)) {
        // compute bottleneck capacity
        int bottleneck = Integer.MAX_VALUE;

        for (int v = t; v != s; v = edgeTo[v].other(v)) {
          bottleneck = Math.min(bottleneck, edgeTo[v].residualCapacityTo(v));
        }

        // augment flow
        for (int v = t; v != s; v = edgeTo[v].other(v)) {
          edgeTo[v].addResidualFlowTo(v, bottleneck);
        }

        value += bottleneck;
      }

      this.isEliminated = false;
      this.set = null;

      for (Edge e : G.adj.get(s)) {
        isEliminated = isEliminated || e.flow < e.capacity;
      }

      if (isEliminated) {
        set = new ArrayList<Integer>();
        for (String key : G.map.keySet()) {
          if (marked[G.map.get(key)] && !key.equals("s") && !key.equals("t") && !key.contains("-")) {
            set.add(Integer.valueOf(key));
          }
        }
      }
    }

    private boolean hasAugmentingPath(Graph G, int s, int t) {
      edgeTo = new Edge[G.V];
      marked = new boolean[G.V];

      LinkedList<Integer> queue = new LinkedList<Integer>();
      queue.add(s);
      while (!queue.isEmpty() && !marked[t]) {
        int v = queue.poll();
        for (Edge e : G.adj.get(v)) {
          int w = e.other(v);
          if (e.residualCapacityTo(w) > 0) {
            if (!marked[w]) {
              edgeTo[w] = e;
              marked[w] = true;
              queue.add(w);
            }
          }
        }
      }
      return marked[t];
    }

    // return excess flow at vertex v
    private int excess(Graph G, int v) {
      int excess = 0;
      for (Edge e : G.adj.get(v)) {
        if (v == e.from) {
          excess -= e.flow;
        } else {
          excess += e.flow;
        }
      }
      return excess;
    }

    public boolean isEliminated() {
      return isEliminated;
    }

    public List<Integer> set() {
      return set;
    }
  }

  public static void main(String[] args) {
    BaseballElimination division = new BaseballElimination(args[0]);
    for (String team : division.teams()) {
      if (division.isEliminated(team)) {
        StdOut.print(team + " is eliminated by the subset R = { ");
        for (String t : division.certificateOfElimination(team)) {
          StdOut.print(t + " ");
        }
        StdOut.println("}");
      } else {
        StdOut.println(team + " is not eliminated");
      }
    }
  }
}
