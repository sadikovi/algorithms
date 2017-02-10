/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
  public ListNode deleteDuplicates(ListNode head) {
    ListNode newHead = null;
    ListNode newTail = null;
    while (head != null) {
      ListNode node = head.next;
      head.next = null;
      int cnt = 1;
      while (node != null && node.val == head.val) {
        node = node.next;
        cnt++;
      }
      if (cnt == 1) {
        if (newTail != null) {
          newTail.next = head;
        } else {
          newHead = head;
        }
        newTail = head;
      }

      head = node;
    }
    return newHead;
  }
}
