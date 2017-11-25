// Idea of the solution is doing preorder traversal and storing each value and storing null
// pointers as markers "X" (or any other suitable marker).
// When deserializing we use queue to remove such elements, so the next child traversal maintains
// only relevant elements.
//
// Serialization takes O(n) time and O(n) space.
// Deserialization takes O(n) time and O(n) space.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    List<String> arr = new ArrayList<String>();
    preorder(root, arr);
    return str(arr);
  }

  private void preorder(TreeNode root, List<String> arr) {
    if (root != null) {
      arr.add(root.val + "");
      preorder(root.left, arr);
      preorder(root.right, arr);
    } else {
      arr.add("X");
    }
  }

  private String str(List<String> arr) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < arr.size(); i++) {
      sb.append(arr.get(i));
      if (i < arr.size() - 1) {
        sb.append(",");
      }
    }
    return sb.toString();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data.isEmpty()) return null;
    String[] arr = data.split(",");
    LinkedList<Integer> q = new LinkedList<Integer>();
    for (String t : arr) {
      if (t.equals("X")) {
        q.add(null);
      } else {
        q.add(Integer.parseInt(t));
      }
    }
    return build(q);
  }

  private TreeNode build(LinkedList<Integer> queue) {
    if (queue.isEmpty()) return null;
    Integer val = queue.remove();
    if (val == null) return null;
    TreeNode root = new TreeNode(val);
    root.left = build(queue);
    root.right = build(queue);
    return root;
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
