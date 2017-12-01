// Solution runs in O(n * log n) time (time to construct graph, dfs is O(n)) and O(n) space, where
// n is the number of tickets.
//
// Problem is DFS of the graph, where we need to be careful how we record result.
// The main gotcha is inserting result after you have traversed all children, this solves the
// problem, when itinerary has smallest lexicographical order, but is invalid.
class Solution {
  public List<String> findItinerary(String[][] tickets) {
    LinkedList<String> res = new LinkedList<String>();
    HashMap<String, PriorityQueue<String>> graph = buildGraph(tickets);
    dfs(graph, "JFK", res);
    return res;
  }

  private HashMap<String, PriorityQueue<String>> buildGraph(String[][] tickets) {
    HashMap<String, PriorityQueue<String>> graph = new HashMap<String, PriorityQueue<String>>();
    for (String[] ticket : tickets) {
      if (!graph.containsKey(ticket[0])) {
        graph.put(ticket[0], new PriorityQueue<String>());
      }
      graph.get(ticket[0]).add(ticket[1]);
    }
    return graph;
  }

  private void dfs(HashMap<String, PriorityQueue<String>> graph, String curr, LinkedList<String> res) {
    PriorityQueue<String> children = graph.get(curr);
    while (children != null && !children.isEmpty()) {
      dfs(graph, children.poll(), res);
    }
    res.addFirst(curr);
  }
}
