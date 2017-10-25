/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Recursive single-pass solution, we build up from the tail of the list.
// O(n) run time and O(n) space.
// Solution beats 93.80%.
class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) return null;
    int r = removeFromEnd(head, n);
    return (r == n) ? head.next : head;
  }

  private int removeFromEnd(ListNode node, int n) {
    if (node == null) return 0;
    int r = removeFromEnd(node.next, n) + 1;
    if (r == n + 1) {
      if (node.next != null) node.next = node.next.next;
    }
    return r;
  }
}

// Two-pass solution with O(n) time and O(1) space.
class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    int size = 0;
    ListNode node = head;
    while (node != null) {
      node = node.next;
      ++size;
    }

    n = size - n;
    if (n == 0) return head.next;
    node = head;
    while (n > 1) {
      node = node.next;
      --n;
    }
    node.next = node.next.next;
    return head;
  }
}
