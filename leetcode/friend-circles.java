// Solution is connected components problem, we are given adjacency matrix and are asked to find
// isolated components in the graph.
// For each vertex we perform DFS (or BFS) and mark connected vertices. Every new vertex in outer
// loop marks new component.
//
// Solution beats 69.80% of submissions.
// Algorithm runs in O(n^2) time and O(n) space.
class Solution {
  public int findCircleNum(int[][] M) {
    int n = M.length; // number of vertices
    boolean[] visited = new boolean[n];
    int count = 0;
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        // depth-first search
        dfs(M, i, visited);
        count++;
      }
    }
    return count;
  }

  private void dfs(int[][] M, int v, boolean[] visited) {
    visited[v] = true;
    int[] adjacent = M[v];
    for (int j = 0; j < adjacent.length; j++) {
      if (M[v][j] == 1 && !visited[j]) {
        dfs(M, j, visited);
      }
    }
  }
}
