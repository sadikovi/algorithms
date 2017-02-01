/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode fast = head;
    ListNode slow = head;
    while (slow.next != null && fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    fast = slow.next;
    slow.next = null;
    ListNode head1 = sortList(head);
    ListNode head2 = sortList(fast);
    return merge(head1, head2);
  }

  public ListNode merge(ListNode head1, ListNode head2) {
    if (head1 == null) return head2;
    if (head2 == null) return head1;

    ListNode head = null;
    ListNode tmp = null;
    while (head1 != null && head2 != null) {
      ListNode smaller;
      if (head1.val < head2.val) {
        smaller = head1;
        head1 = head1.next;
      } else {
        smaller = head2;
        head2 = head2.next;
      }

      if (head == null) {
        head = smaller;
        tmp = smaller;
      } else {
        tmp.next = smaller;
        tmp = tmp.next;
      }
    }

    if (head1 != null) {
      tmp.next = head1;
    } else if (head2 != null) {
      tmp.next = head2;
    }
    return head;
  }
}
