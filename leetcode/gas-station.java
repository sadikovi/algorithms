public class Solution {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int n = gas.length;
    for (int i = 0; i < n; i++) {
      gas[i] = gas[i] - cost[i];
    }

    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += gas[i];
    }
    if (sum < 0) return -1;

    int index = 0;
    int partial = 0;
    int start = 0;
    while (true) {
      if (partial < 0) {
        start = index;
        partial = gas[index];
      } else {
        partial += gas[index];
      }
      index = (index + 1) % n;
      if (start == index) return start;
    }
  }
}
