/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  public ListNode oddEvenList(ListNode head) {
    if (head == null) return head;
    ListNode p1 = head, p2 = head.next;
    while (p1.next != null) {
      ListNode tmp = p1.next;
      p1.next = tmp.next;
      // ends with even element
      if (p1.next != null) {
        tmp.next = p1.next.next;
        p1 = p1.next;
      }
    }
    p1.next = p2;
    return head;
  }
}
