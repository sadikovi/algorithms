// Runtime: 0 ms, faster than 100.00% of Java online submissions.
// Memory Usage: 37.5 MB, less than 100.00% of Java online submissions.
//
// Input string always contains a valid matching set of parentheses.
//
// Time complexity is O(n * s) where n is the number of characters and s is the number of sets of
// parentheses.
// Space complexity is O(n) where n is the length of the input string.
//
// Test cases:
// "(abcd)"
// "(u(love)i)"
// "(ed(et(oc))el)"
// "a(bcdefghijkl(mno)p)q"
// "abc"
// "ta()usw((((a))))"
// "a(fgh)b(d(iotp)e)c"
//
class Solution {
  public String reverseParentheses(String s) {
    if (s == null) return s;

    char[] arr = new char[s.length()]; // array to swap characters
    for (int i = 0; i < s.length(); i++) {
      arr[i] = s.charAt(i);
    }

    // Update the stack or swap between matching brackets
    int[] open = new int[arr.length]; // stack for open brackets
    int oi = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == '(') {
        open[oi++] = i;
      } else if (arr[i] == ')') {
        swap(arr, open[--oi] + 1, i - 1);
      }
    }

    // Construct result
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != '(' && arr[i] != ')') {
        sb.append(arr[i]);
      }
    }
    return sb.toString();
  }

  // Swaps elements in 'arr' between 'start' and 'end'
  private void swap(char[] arr, int start, int end) {
    while (start < end) {
      char t = arr[start];
      arr[start] = arr[end];
      arr[end] = t;
      start++;
      end--;
    }
  }
}
