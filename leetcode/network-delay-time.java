// Problem is the shortest paths problem and we use Dijkstra algorithm to find the smallest time
// for each node to receive signal. Then we iterate over weights and compute max time, because
// the network is bound by max time it takes to reach all nodes. If any of the nodes contain
// Integer.MAX_VALUE it means that we cannot reach that node, so the answer is -1.
//
// Running time is O(V^2) or O(E * logV) time depending on the priority queue implementation.
// With Fibonacci heap time complexity becomes O(E + VlogV).
// Space complexity is O(V*E) or O(V^2).
class Solution {
  public int networkDelayTime(int[][] times, int N, int K) {
    int[] weights = new int[N+1];
    for (int i = 1; i <= N; i++) {
      weights[i] = Integer.MAX_VALUE;
    }

    HashMap<Integer, List<int[]>> graph = new HashMap<Integer, List<int[]>>();
    for (int[] edge : times) {
      if (!graph.containsKey(edge[0])) {
        graph.put(edge[0], new ArrayList<int[]>());
      }
      graph.get(edge[0]).add(edge);
    }

    PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> (a[2] - b[2]));
    queue.add(new int[]{0, K, 0});

    while (!queue.isEmpty()) {
      int[] edge = queue.remove();
      if (weights[edge[1]] > weights[edge[0]] + edge[2]) {
        weights[edge[1]] = weights[edge[0]] + edge[2];
        List<int[]> edges = graph.get(edge[1]);
        if (edges != null) {
          for (int[] e : edges) {
            queue.add(e);
          }
        }
      }
    }

    int max = 0;
    for (int w : weights) {
      max = Math.max(w, max);
    }
    return max == Integer.MAX_VALUE ? -1 : max;
  }
}
