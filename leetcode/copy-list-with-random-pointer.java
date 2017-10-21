// Solution that uses hash table to store map between original node and copied node to reconstruct
// random links. O(n) time and O(n) space of extra storage.

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
  public RandomListNode copyRandomList(RandomListNode head) {
    HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
    RandomListNode tmp = head, cpHead = null, cpTail = null;

    // build copies of nodes with old random links
    while (tmp != null) {
      RandomListNode cp = new RandomListNode(tmp.label);
      cp.random = tmp.random;
      map.put(tmp, cp);
      if (cpHead == null) {
        cpHead = cp;
        cpTail = cp;
      } else {
        cpTail.next = cp;
        cpTail = cpTail.next;
      }
      tmp = tmp.next;
    }
    // copy random links
    cpTail = cpHead;
    while (cpTail != null) {
      if (cpTail.random != null) {
        cpTail.random = map.get(cpTail.random);
      }
      cpTail = cpTail.next;
    }
    return cpHead;
  }
}

// Solution that copies a node and stores it after original node. Copied random link still points
// to original node. Next step is remapping to random link to a copied node, which is always the
// `random.next`. Final step is reconstructing original list and constructing new copied list.
// Runs in O(n) time and O(1) space of extra storage.

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
  public RandomListNode copyRandomList(RandomListNode head) {
    // insert copy after original pointing to the same random node
    RandomListNode tail = head;
    while (tail != null) {
      RandomListNode cp = new RandomListNode(tail.label);
      cp.next = tail.next;
      cp.random = tail.random;
      tail.next = cp;
      tail = cp.next;
    }
    // copy random links
    tail = head;
    while (tail != null) {
      tail = tail.next;
      tail.random = tail.random == null ? null : tail.random.next;
      tail = tail.next;
    }

    // copy next links
    RandomListNode primeHead = new RandomListNode(0);
    RandomListNode prime = primeHead;
    tail = head;
    while (tail != null) {
      prime.next = tail.next;
      prime = prime.next;
      tail.next = prime == null ? null : prime.next;
      tail = tail.next;
    }
    return primeHead.next;
  }
}
