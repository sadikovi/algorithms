// Solution runs in O(max(a, b)) time and O(a + b) space, where 'a' is length of version1 string
// and 'b' is length of version2 string
public class Solution {
  public int compareVersion(String version1, String version2) {
    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");
    int i = 0;
    while (i < v1.length || i < v2.length) {
      int x = (i < v1.length) ? Integer.parseInt(v1[i]) : 0;
      int y = (i < v2.length) ? Integer.parseInt(v2[i]) : 0;
      if (x < y) return -1;
      if (x > y) return 1;
      i++;
    }
    return 0;
  }
}
