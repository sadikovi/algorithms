import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  static class TreeNode {
    int data;
    TreeNode parent;
    TreeNode(int data) {
      this.data = data;
      this.parent = null;
    }
  }

  static class DisjointSet {
    HashMap<Integer, TreeNode> nodes;
    HashMap<TreeNode, Integer> sizes;

    DisjointSet() {
      nodes = new HashMap<Integer, TreeNode>();
      sizes = new HashMap<TreeNode, Integer>();
    }

    public void createSet(int x) {
      TreeNode node = new TreeNode(x);
      node.parent = node;
      sizes.put(node, 1);
      nodes.put(x, node);
    }

    public TreeNode findSet(int x) {
      TreeNode node = nodes.get(x);
      return findSet(node);
    }

    private TreeNode findSet(TreeNode node) {
      if (node != node.parent) node.parent = findSet(node.parent);
      return node.parent;
    }

    public void mergeSets(int x, int y) {
      TreeNode px = findSet(x);
      TreeNode py = findSet(y);
      if (px == py) return;

      px.parent = py;
      sizes.put(py, sizes.get(px) + sizes.get(py));
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int q = in.nextInt();
    DisjointSet set = new DisjointSet();
    for (int i = 1; i <= n; i++) {
      set.createSet(i);
    }

    for (int i = 0; i < q; i++) {
      String query = in.next();
      if (query.equals("Q")) {
        // print size for node i
        TreeNode node = set.findSet(in.nextInt());
        int size = set.sizes.get(node);
        System.out.println(size);
      } else {
        // merge two sets
        set.mergeSets(in.nextInt(), in.nextInt());
      }
    }
  }
}
