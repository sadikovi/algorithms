// Solution runs in O(n) time and O(n) space for recursive calls, where n is max(l1, l2).

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  static class Result {
    int carry = 0;
    ListNode sum = null;
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int sum1 = size(l1), sum2 = size(l2);
    if (sum1 < sum2) {
      l1 = pad(l1, sum2 - sum1);
    } else {
      l2 = pad(l2, sum1 - sum2);
    }

    Result res = add(l1, l2);
    if (res.carry > 0) {
      ListNode l = new ListNode(res.carry);
      l.next = res.sum;
      return l;
    } else {
      return res.sum;
    }
  }

  private int size(ListNode l) {
    int size = 0;
    while (l != null) {
      l = l.next;
      size++;
    }
    return size;
  }

  private ListNode pad(ListNode l, int num) {
    while (num > 0) {
      ListNode tmp = new ListNode(0);
      tmp.next = l;
      l = tmp;
      --num;
    }
    return l;
  }

  private Result add(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) return new Result();
    Result res = add(l1.next, l2.next);
    int value = l1.val + l2.val + res.carry;
    res.carry = value / 10;
    ListNode tmp = new ListNode(value % 10);
    tmp.next = res.sum;
    res.sum = tmp;
    return res;
  }
}
