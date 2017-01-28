/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) return null;
    HashMap<Integer, UndirectedGraphNode> graph = copy(node);
    return graph.get(node.label);
  }

  private HashMap<Integer, UndirectedGraphNode> copy(UndirectedGraphNode node) {
    HashMap<Integer, UndirectedGraphNode> graph = new HashMap<Integer, UndirectedGraphNode>();
    HashSet<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
    LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
    queue.add(node);
    while (!queue.isEmpty()) {
      UndirectedGraphNode current = queue.poll();
      if (!visited.contains(current)) {
        UndirectedGraphNode clone = getOrElse(graph, current.label);
        for (UndirectedGraphNode child : current.neighbors) {
          clone.neighbors.add(getOrElse(graph, child.label));
          queue.add(child);
        }
        visited.add(current);
      }
    }
    return graph;
  }

  private UndirectedGraphNode getOrElse(HashMap<Integer, UndirectedGraphNode> graph, int label) {
    if (!graph.containsKey(label)) {
      graph.put(label, new UndirectedGraphNode(label));
    }
    return graph.get(label);
  }
}

////////////////////////////////////////////////////////////////
// Alternative solution
////////////////////////////////////////////////////////////////

// Slightly optimized to use 'graph' itself as list of visited nodes, this saves time and space
// on maintaining additional hash set.
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) return null;
    HashMap<Integer, UndirectedGraphNode> graph = copy(node);
    return graph.get(node.label);
  }

  private HashMap<Integer, UndirectedGraphNode> copy(UndirectedGraphNode node) {
    HashMap<Integer, UndirectedGraphNode> graph = new HashMap<Integer, UndirectedGraphNode>();
    LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
    queue.add(node);
    while (!queue.isEmpty()) {
      UndirectedGraphNode current = queue.poll();
      UndirectedGraphNode clone = getOrElse(graph, current.label);
      for (UndirectedGraphNode child : current.neighbors) {
        if (!graph.containsKey(child.label)) {
          queue.add(child);
        }
        clone.neighbors.add(getOrElse(graph, child.label));
      }
    }
    return graph;
  }

  private UndirectedGraphNode getOrElse(HashMap<Integer, UndirectedGraphNode> graph, int label) {
    if (!graph.containsKey(label)) {
      graph.put(label, new UndirectedGraphNode(label));
    }
    return graph.get(label);
  }
}
