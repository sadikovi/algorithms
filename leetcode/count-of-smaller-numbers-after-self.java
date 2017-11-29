// Solution runs in O(n log n) time and O(n) space
class Solution {
  static class Node {
    Node left;
    Node right;
    int val = 1, sum = 1, dup = 1;

    public Node(int v, int s) {
      val = v;
      sum = s;
    }
  }

  public List<Integer> countSmaller(int[] nums) {
    Node root = null;
    Integer[] counts = new Integer[nums.length];
    for (int i = nums.length - 1; i >= 0; i--) {
      root = insert(nums[i], root, counts, i, 0);
    }
    return Arrays.asList(counts);
  }

  private Node insert(int num, Node node, Integer[] ans, int i, int preSum) {
    if (node == null) {
      node = new Node(num, 0);
      ans[i] = preSum;
    } else if (node.val == num) {
      node.dup++;
      ans[i] = preSum + node.sum;
    } else if (node.val > num) {
      node.sum++;
      node.left = insert(num, node.left, ans, i, preSum);
    } else {
      node.right = insert(num, node.right, ans, i, preSum + node.dup + node.sum);
    }
    return node;
  }
}
