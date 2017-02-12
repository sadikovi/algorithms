public class Solution {
  public int maxProfit(int[] prices) {
    int profit = 0, curr = 0;
    for (int i = 1; i < prices.length; i++) {
      curr += prices[i] - prices[i - 1];
      if (curr < 0) {
        curr = 0;
      } else {
        profit = Math.max(profit, curr);
      }
    }
    return profit;
  }
}
