// Solution runs in O(n) time and O(1) space.
// Find length and the last node, make list circular and search for len-k node.
// Then unlink nodes and reassign the head.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null) return head;

    ListNode tmp = head;
    int len = 1;
    while (tmp.next != null) {
      tmp = tmp.next;
      len++;
    }
    tmp.next = head;
    k %= len;
    while (len > k) {
      tmp = tmp.next;
      len--;
    }
    head = tmp.next;
    tmp.next = null;
    return head;
  }
}
