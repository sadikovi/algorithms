// Solution taken from
// https://discuss.leetcode.com/topic/38014/java-oms-with-explanation
public class Solution {
  public boolean isSelfCrossing(int[] x) {
    int n = x.length;
    if (n <= 3) return false;

    for (int i = 3; i < n; i++) {
      //Fourth line crosses first line and onward
      if (x[i] >= x[i-2] && x[i-1] <= x[i-3]) return true;
      if (i >= 4) {
        // Fifth line meets first line and onward
        if (x[i-1] == x[i-3] && x[i] + x[i-4] >= x[i-2]) return true;
      }
      if (i >= 5) {
        // Sixth line crosses first line and onward
        if (x[i-2] - x[i-4] >= 0 && x[i] >= x[i-2] - x[i-4] && x[i-1] >= x[i-3] - x[i-5] && x[i-1] <= x[i-3]) return true;
      }
    }
    return false;
  }
}
