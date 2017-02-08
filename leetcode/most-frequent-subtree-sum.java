/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
  public int[] findFrequentTreeSum(TreeNode root) {
    HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
    findFrequentSum(root, freq);
    int maxFrq = 0;
    int size = 0;
    for (int frq : freq.values()) {
      if (frq == maxFrq) {
        size++;
      } else if (frq > maxFrq) {
        maxFrq = frq;
        size = 1;
      }
    }

    int i = 0;
    int[] res = new int[size];
    for (int key : freq.keySet()) {
      if (freq.get(key) == maxFrq) {
        res[i++] = key;
      }
    }
    return res;
  }

  private int findFrequentSum(TreeNode root, HashMap<Integer, Integer> freq) {
    if (root == null) return 0;
    int sum = root.val + findFrequentSum(root.left, freq) + findFrequentSum(root.right, freq);
    if (!freq.containsKey(sum)) {
      freq.put(sum, 0);
    }
    freq.put(sum, freq.get(sum) + 1);
    return sum;
  }
}
