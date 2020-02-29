// Problem is boils down to solving connected components for all of the emails (vertices) in the
// graph. I think the code can be simplified by not using Node class, but that makes it a bit
// easier to read.
//
// Time complexity: max(O(AE), O(ElogE)); it depends on number of unique emails per account.
// Space complexity: O(E)
// E - number of emails, A - number of accounts

class Solution {
  static class Node {
    String email; // non-null
    String name; // non-null

    Node(String email, String name) {
      this.email = email;
      this.name = name;
    }

    @Override
    public int hashCode() {
      return email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Node) return ((Node) obj).email.equals(email);
      return false;
    }
  }

  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    List<List<String>> res = new ArrayList<List<String>>();
    HashSet<Node> visited = new HashSet<Node>();
    HashMap<Node, List<Node>> graph = new HashMap<Node, List<Node>>();

    for (List<String> account : accounts) {
      if (account.size() > 1) {
        String name = account.get(0);
        Node prev = new Node(account.get(1), name);
        if (!graph.containsKey(prev)) {
          graph.put(prev, new ArrayList<Node>());
        }
        for (int i = 2; i < account.size(); i++) {
          Node curr = new Node(account.get(i), name);
          if (!graph.containsKey(curr)) {
            graph.put(curr, new ArrayList<Node>());
          }
          graph.get(prev).add(curr);
          graph.get(curr).add(prev);
          prev = curr;
        }
      } else {
        res.add(account);
      }
    }

    for (Node n : graph.keySet()) {
      if (!visited.contains(n)) {
        List<Node> cmp = new ArrayList<Node>();
        component(n, graph, visited, cmp);
        res.add(convert(cmp));
      }
    }

    return res;
  }

  // Finds a component
  private void component(
      Node start, HashMap<Node, List<Node>> graph, HashSet<Node> visited, List<Node> res) {
    if (visited.contains(start)) return;
    visited.add(start);
    res.add(start);
    for (Node child : graph.get(start)) {
      component(child, graph, visited, res);
    }
  }

  // Converts nodes into the answer + sorting
  private List<String> convert(List<Node> nodes) {
    List<String> res = new ArrayList<String>();
    if (nodes.size() > 0) {
      res.add(nodes.get(0).name);
      String[] arr = new String[nodes.size()];
      for (int i = 0; i < nodes.size(); i++) {
        arr[i] = nodes.get(i).email;
      }
      Arrays.sort(arr);
      for (String s : arr) {
        res.add(s);
      }
    }
    return res;
  }
}
