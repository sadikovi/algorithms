// Solution runs in O(n) time and O(n) space, where n is length of string
public class Solution {
  public String convert(String s, int numRows) {
    if (numRows <= 1) return s;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numRows; i++) {
      int index = i;
      while (index < s.length()) {
        sb.append(s.charAt(index));
        int diag = index + 2*(numRows - i - 1);
        index += 2*(numRows - 1);
        if (i > 0 && i < numRows - 1 && diag < s.length()) {
          sb.append(s.charAt(diag));
        }
      }
    }
    return sb.toString();
  }
}
