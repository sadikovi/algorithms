/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
// Solution runs in O(n) time and uses O(n) space (virtual callstack + substrings)
// TODO: prove time complexity
public class Solution {
  public NestedInteger deserialize(String s) {
    if (s.charAt(0) == '[') {
      NestedInteger ni = new NestedInteger();
      int start = 1, end = s.length() - 2;
      while (start <= end) {
        int i = start;
        if (s.charAt(i) == '[') {
          Stack<Character> stack = new Stack<Character>();
          do {
            if (s.charAt(i) == '[') {
              stack.push('[');
            } else if (s.charAt(i) == ']') {
              stack.pop();
            }
            i++;
          } while (!stack.isEmpty());
        } else {
          i = s.indexOf(',', i);
          i = i < 0 ? (end + 1) : i;
        }
        ni.add(deserialize(s.substring(start, i)));
        start = i + 1;
      }
      return ni;
    } else {
      return new NestedInteger(Integer.parseInt(s));
    }
  }
}
