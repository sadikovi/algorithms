// Solution runs in O(n^2) time (outer loop and index check for each element in the stack)
// and O(n) space complexity (stack + set + sb)
// test examples:
public class Solution {
  public String removeDuplicateLetters(String s) {
    // stack maintains current non-duplicate string
    Stack<Character> stack = new Stack<Character>();
    // set maintains unique characters that are in the stack, for quick check
    HashSet<Character> set = new HashSet<Character>();
    for (int i = 0; i < s.length(); i++) {
      char t = s.charAt(i);
      // if element exists in the stack, skip
      // this is done with assumption that existing element has already best position on stack
      if (set.contains(t)) continue;
      // while element is less than head of stack (greedy approach) and there is a duplicate that
      // exists in the string after element, remove stack head - reason: we can achieve better
      // placement by removing and adding stack head later and getting smallest lexicographical
      // order.
      // while removing elements, also remove from the set.
      while (!stack.isEmpty() && stack.peek() > t && s.indexOf(stack.peek(), i + 1) > 0) {
        set.remove(stack.pop());
      }
      stack.push(t);
      set.add(t);
    }
    // build final string from stack and return
    StringBuilder sb = new StringBuilder();
    for (char elem : stack) {
      sb.append(elem);
    }
    return sb.toString();
  }
}

// Naive recursive solution, runs in exponential time, gets TLE on one of the tests
public class Solution {
  public String removeDuplicateLetters(String s) {
    String[] min = new String[1];
    recur(s, "", min);
    return min[0];
  }

  private void recur(String str, String res, String[] min) {
    if (str.length() == 0) {
      if (min[0] == null || min[0].compareTo(res) > 0) {
        min[0] = res;
      }
    } else {
      char t = str.charAt(0);
      boolean duplicate = str.indexOf(t, 1) > 0;
      if (duplicate) {
        recur(str.substring(1), res, min);
        recur(removeChar(str, t), res + t, min);
      } else {
        recur(str.substring(1), res + t, min);
      }
    }
  }

  private String removeChar(String str, char t) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) != t) {
        sb.append(str.charAt(i));
      }
    }
    return sb.toString();
  }
}
