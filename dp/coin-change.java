// Dynamic programming solution, time is O(amount * coins.length), space is O(amount)
class Solution {
  public int coinChange(int[] coins, int amount) {
    int[] arr = new int[amount + 1];
    Arrays.fill(arr, -1);
    arr[0] = 0;
    for (int i = 1; i <= amount; i++) {
      for (int coin : coins) {
        if (coin == i) {
          arr[i] = 1;
        } else if (coin < i && arr[i - coin] != -1) {
          if (arr[i] == -1) arr[i] = 1 + arr[i - coin];
          else arr[i] = Math.min(arr[i], 1 + arr[i - coin]);
        }
      }
    }
    return arr[amount];
  }
}
