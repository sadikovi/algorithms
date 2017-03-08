/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
  public ListNode insertionSortList(ListNode head) {
    ListNode newHead = new ListNode(-1);
    while (head != null) {
      ListNode t = head;
      head = head.next;
      ListNode tr = newHead;
      while (tr.next != null && tr.next.val <= t.val) {
        tr = tr.next;
      }

      t.next = tr.next;
      tr.next = t;
    }
    return newHead.next;
  }
}
