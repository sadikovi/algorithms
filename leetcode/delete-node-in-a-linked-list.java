/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
  public void deleteNode(ListNode node) {
    // the trick is to copy next node into current one
    // and drop next node
    ListNode parent = node.next;
    node.next = parent.next;
    node.val = parent.val;
  }
}
