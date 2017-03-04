public class Solution {
  public int findPoisonedDuration(int[] timeSeries, int duration) {
    int total = 0;
    if (timeSeries.length == 0) return total;
    int eta = timeSeries[0] + duration - 1;
    for (int i = 1; i < timeSeries.length; i++) {
      if (eta < timeSeries[i]) {
        total += duration;
      } else {
        total += timeSeries[i] - timeSeries[i - 1];
      }
      eta = timeSeries[i] + duration - 1;
    }
    total += duration;
    return total;
  }
}
