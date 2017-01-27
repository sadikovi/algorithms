public class Solution {
  public int candy(int[] ratings) {
    int n = ratings.length;
    if (n == 0) return 0;
    int[] candies1 = new int[n];
    int[] candies2 = new int[n];
    for (int i = 1; i < n; i++) {
      if (ratings[i - 1] < ratings[i]) {
        candies1[i] = candies1[i - 1] + 1;
      } else {
        candies1[i] = 0;
      }
    }

    for (int i = n - 2; i >= 0; i--) {
      if (ratings[i + 1] < ratings[i]) {
        candies2[i] = candies2[i + 1] + 1;
      } else {
        candies2[i] = 0;
      }
    }

    int cnt = n;
    for (int i = 0; i < n; i++) {
      cnt += Math.max(candies1[i], candies2[i]);
    }
    return cnt;
  }
}
