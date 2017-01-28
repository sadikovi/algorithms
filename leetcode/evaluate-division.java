// Another solution is actually creating Graph class to store vertices and edges and do DFS
public class Solution {
  public static class Edge {
    String to;
    double weight;
    Edge(String to, double weight) {
      this.to = to;
      this.weight = weight;
    }
  }

  public double dfs(HashMap<String, List<Edge>> graph, String from, String to) {
    if (from == null || to == null) return -1.0;
    if (!graph.containsKey(from) || !graph.containsKey(to)) return -1.0;
    return dfs(graph, from, to, new HashSet<String>());
  }

  private double dfs(HashMap<String, List<Edge>> graph, String from, String to, HashSet<String> visited) {
    if (from.equals(to)) return 1.0;
    if (visited.contains(from)) return -1.0;
    visited.add(from);
    for (Edge edge : graph.get(from)) {
      double res = dfs(graph, edge.to, to, visited);
      if (res != -1.0) return res * edge.weight;
    }
    return -1.0;
  }

  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    // build graph
    HashMap<String, List<Edge>> graph = new HashMap<String, List<Edge>>();
    for (int i = 0; i < equations.length; i++) {
      String[] points = equations[i];
      double weight = values[i];
      if (!graph.containsKey(points[0])) graph.put(points[0], new ArrayList<Edge>());
      if (!graph.containsKey(points[1])) graph.put(points[1], new ArrayList<Edge>());
      graph.get(points[0]).add(new Edge(points[1], weight));
      graph.get(points[1]).add(new Edge(points[0], 1.0 / weight));
    }

    double[] res = new double[queries.length];
    for (int i = 0; i < queries.length; i++) {
      String[] points = queries[i];
      res[i] = dfs(graph, points[0], points[1]);
    }
    return res;
  }
}
