// Solution runs in O(n) time and uses O(1) space, where n is the length of the time points list
// Beats 97.49% of submissions
class Solution {
  public int findMinDifference(List<String> timePoints) {
    if (timePoints.size() < 2) return 0;
    boolean[] time = new boolean[1440];
    for (int i = 0; i < timePoints.size(); i++) {
      int min = min(timePoints.get(i));
      if (time[min]) return 0;
      time[min] = true;
    }

    int prev = 0, min = Integer.MAX_VALUE;
    int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
    for (int i = 0; i < 24 * 60; i++) {
      if (time[i]) {
        if (first != Integer.MAX_VALUE) {
          min = Math.min(min, i - prev);
        }
        first = Math.min(first, i);
        last = Math.max(last, i);
        prev = i;
      }
    }

    min = Math.min(min, (24 * 60 - last + first));
    return min;
  }

  private int min(String time) {
    return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
  }
}
