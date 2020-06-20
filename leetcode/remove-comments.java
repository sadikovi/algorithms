// Time complexity is O(N * M), where N is the number of lines and M is the max length of a line.
// String builder append is an amortised O(1) operation as well as appending to the list.
// Space complexity is O(N * M), we need to store the entire input when there are no comments.
//
// Test cases:
//
// ["int main() { // comment1", "  int a = 1; /* line0", "  line1", "  line2", "  */ if (true) {", "    // line3", "    return 0; // line4", "  } else {", "    /* line5 */ return 1;", "  }", "  // line6", "} /* line7", "*/"]
// ["// comment"]
// ["/* comment */"]
// ["/* comment", " comment */ "]
// ["struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};"]
// ["/* 8 * 5 */"]
// ["/* 8 */ 5 */"]
// ["/*/ comment/**/"]
//
class Solution {
  public List<String> removeComments(String[] source) {
    ArrayList<String> lines = new ArrayList<String>();
    boolean multiline = false;
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < source.length; i++) {
      String line = source[i];
      int curr = 0, len = line.length();

      while (curr < len) {
        char t = line.charAt(curr);
        switch (t) {
          case '/':
            if (!multiline && curr + 1 < len && line.charAt(curr + 1) == '/') {
              if (sb.length() > 0) {
                lines.add(sb.toString());
                sb.setLength(0);
              }
              curr = len;
            } else if (!multiline && curr + 1 < len && line.charAt(curr + 1) == '*') {
              multiline = true;
              curr += 2; // skip '/*'
            } else if (multiline) {
              curr++;
            } else {
              sb.append(t);
              curr++;
            }
            break;
          case '*':
            if (multiline && curr + 1 < len && line.charAt(curr + 1) == '/') {
              curr += 2; // skip '*/'
              multiline = false;
            } else if (multiline) {
              curr++;
            } else {
              sb.append(t);
              curr++;
            }
            break;
          default:
            if (!multiline) {
              sb.append(t);
            }
            curr++;
            break;
        }
      }

      if (!multiline && sb.length() > 0) {
        lines.add(sb.toString());
        sb.setLength(0);
      }
    }

    return lines;
  }
}
