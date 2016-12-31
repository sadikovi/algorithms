public class Solution {
  // Running time is O(n) and space complexity is O(1).
  // It is essentially problem of running sum with max subarray sum, we just need to increase sum
  // until it is negative and reset it. Profit keeps track of total non-negative sum we captured.
  public int maxProfit(int[] prices) {
    int profit = 0;
    int curr = 0;
    for (int i = 1; i < prices.length; i++) {
      curr += prices[i] - prices[i - 1];
      if (curr < 0) {
        curr = 0;
      }
      if (curr > profit) {
        profit = curr;
      }
    }
    return profit;
  }
}
