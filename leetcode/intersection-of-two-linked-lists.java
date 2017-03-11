/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
// Naive solution that runs in O(a + b) time and uses O(max(a, b)) space in worst case, where
// 'a' is the size of headA and 'b' is the size of headB
public class Solution {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    HashSet<ListNode> set = new HashSet<ListNode>();
    while (headA != null) {
      set.add(headA);
      headA = headA.next;
    }
    while (headB != null) {
      if (set.contains(headB)) return headB;
      headB = headB.next;
    }
    return null;
  }
}

// Solution runs in O(a + b) time and uses O(1) space, where
// 'a' is the size of headA and 'b' is the size of headB
public class Solution {
  private int size(ListNode tmp) {
    int size = 0;
    while (tmp != null) {
      tmp = tmp.next;
      size++;
    }
    return size;
  }

  private ListNode advance(ListNode tmp, int delta) {
    while (tmp != null && delta > 0) {
      tmp = tmp.next;
      delta--;
    }
    return tmp;
  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    HashSet<ListNode> set = new HashSet<ListNode>();
    int sizeA = size(headA);
    int sizeB = size(headB);
    if (sizeA > sizeB) {
      headA = advance(headA, sizeA - sizeB);
    } else {
      headB = advance(headB, sizeB - sizeA);
    }

    while (headA != null && headB != null) {
      if (headA == headB) return headA;
      headA = headA.next;
      headB = headB.next;
    }
    return null;
  }
}
