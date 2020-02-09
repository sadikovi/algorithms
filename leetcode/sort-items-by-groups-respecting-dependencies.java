// Idea is solving topological sort for groups first and then solving topological sort within each
// group.
//
// Test cases:
// 8
// 2
// [-1,-1,1,0,0,1,0,-1]
// [[],[6],[5],[6],[3,6],[],[],[]]
// 8
// 2
// [-1,-1,1,0,0,1,0,-1]
// [[],[6],[5],[6],[3],[],[4],[]]
// 6
// 3
// [0,1,2,2,1,1]
// [[1,5],[5],[5],[1],[2],[]]
// 4
// 1
// [-1,0,0,-1]
// [[],[0],[1,3],[2]]

class Solution {
  public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
    // 1. update -1 groups to belong to each unique group
    int[] updated_group = new int[n];
    int group_idx = m;
    for (int i = 0; i < n; i++) {
      if (group[i] == -1) {
        updated_group[i] = group_idx;
        group_idx++;
      } else {
        updated_group[i] = group[i];
      }
    }

    // 2. build group -> items
    HashMap<Integer, List<Integer>> group_items = new HashMap<Integer, List<Integer>>();
    for (int i = 0; i < updated_group.length; i++) {
      if (!group_items.containsKey(updated_group[i])) {
        group_items.put(updated_group[i], new ArrayList<Integer>());
      }
      group_items.get(updated_group[i]).add(i);
    }

    // 3. topological sort for groups
    HashMap<Integer, Integer> group_cnt = new HashMap<Integer, Integer>();
    HashMap<Integer, HashSet<Integer>> group_adj = new HashMap<Integer, HashSet<Integer>>();
    for (int i = 0; i < n; i++) {
      int g = updated_group[i];
      if (!group_cnt.containsKey(g)) {
        group_cnt.put(g, 0);
      }
      for (int d : beforeItems.get(i)) {
        int dg = updated_group[d];
        if (dg != g) {
          if (!group_adj.containsKey(dg)) {
            group_adj.put(dg, new HashSet<Integer>());
          }
          if (group_adj.get(dg).add(g)) {
            group_cnt.put(g, group_cnt.get(g) + 1);
          }
        }
      }
    }

    List<Integer> groups = topologicalSort(group_cnt, group_adj);
    if (groups == null) return new int[0]; // no way to sort the items

    List<Integer> sorted_items = new ArrayList<Integer>(n);
    for (int group_id : groups) {
      List<Integer> items = group_items.get(group_id);
      if (items != null && items.size() > 0) {
        HashMap<Integer, Integer> item_cnt = new HashMap<Integer, Integer>();
        HashMap<Integer, HashSet<Integer>> item_adj = new HashMap<Integer, HashSet<Integer>>();

        for (int item_id : items) {
          item_cnt.put(item_id, 0);
          for (int before_item_id : beforeItems.get(item_id)) {
            if (updated_group[item_id] == updated_group[before_item_id]) {
              if (!item_adj.containsKey(before_item_id)) {
                item_adj.put(before_item_id, new HashSet<Integer>());
              }
              if (item_adj.get(before_item_id).add(item_id)) {
                item_cnt.put(item_id, item_cnt.get(item_id) + 1);
              }
            }
          }
        }

        List<Integer> tmp = topologicalSort(item_cnt, item_adj);
        if (tmp == null) return new int[0];
        sorted_items.addAll(tmp);
      }
    }

    int[] res = new int[n];
    for (int i = 0; i < n; i++) {
      res[i] = sorted_items.get(i);
    }
    return res;
  }

  // returns null if there is no topological sort
  private List<Integer> topologicalSort(
      HashMap<Integer, Integer> cnt,
      HashMap<Integer, HashSet<Integer>> adj) {
    LinkedList<Integer> queue = new LinkedList<Integer>();
    for (int i : cnt.keySet()) {
      if (cnt.get(i) == 0) {
        queue.offer(i);
      }
    }

    ArrayList<Integer> res = new ArrayList<Integer>();

    while (!queue.isEmpty()) {
      int g = queue.poll();
      res.add(g);
      if (adj.containsKey(g)) {
        for (int dg : adj.get(g)) {
          cnt.put(dg, cnt.get(dg) - 1);
          if (cnt.get(dg) == 0) {
            queue.offer(dg);
          }
        }
      }
    }

    if (res.size() != cnt.size()) return null;
    return res;
  }
}
