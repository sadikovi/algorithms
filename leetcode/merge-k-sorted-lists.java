/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
  private ListNode merge2Lists(ListNode l1, ListNode l2) {
    ListNode head = new ListNode(1);
    ListNode tail = head;
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        tail.next = l1;
        l1 = l1.next;
      } else {
        tail.next = l2;
        l2 = l2.next;
      }
      tail = tail.next;
    }
    tail.next = (l1 == null) ? l2 : l1;
    return head.next;
  }

  private ListNode partition(ListNode[] lists, int start, int end) {
    if (start > end) return null;
    if (start == end) return lists[start];
    int mid = (start + end) / 2;
    ListNode l1 = partition(lists, start, mid);
    ListNode l2 = partition(lists, mid + 1, end);
    return merge2Lists(l1, l2);
  }

  // Idea is to use previous solution for merging 2 lists and apply divide-and-conquer technique
  // by using partitioning. Problem becomes similar to mergesort.
  // runtime is O(nlgk), where 'k' is number of lists and 'n' is total number of nodes
  public ListNode mergeKLists(ListNode[] lists) {
    return partition(lists, 0, lists.length - 1);
  }
}
