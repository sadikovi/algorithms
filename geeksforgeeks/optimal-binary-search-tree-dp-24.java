// Link: https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/

// Examples:
//
// int[] arr = new int[] {10, 12, 20};
// int[] freq = new int[] {34, 8, 50};
// cost: 142
//
// int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
// int[] freq = new int[] {34, 8, 50, 10, 23, 45, 20, 11, 52, 27};
// cost: 677

// Recursive solution, quite easy to implement.
// Time complexity O(n * 2^n)
class GFG {
  static int minCost(int start, int end, int[] arr, int[] freq, int level) {
    if (start > end) return 0;
    int i = start, cost = Integer.MAX_VALUE;
    while (i <= end) {
      cost = Math.min(cost, freq[i] * level +
        minCost(start, i - 1, arr, freq, level + 1) +
        minCost(i + 1, end, arr, freq, level + 1));
      i++;
    }
    return cost;
  }

  public static void main (String[] args) {
    int[] arr = new int[] {10, 12, 20};
    int[] freq = new int[] {34, 8, 50};
  	int cost = minCost(0, arr.length - 1, arr, freq, 1);
  	System.out.println("cost: " + cost);
  }
}

// Dynamic programming solution.
// Interesting trick with levels.
// Adapted from https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/
// Time complexity is O(n^3)
class GFG {
  static int minCost(int[] arr, int[] freq) {
    int[][] cost = new int[arr.length][arr.length];
    // a replacement for function "sum" to reduce to O(n^3)
    int[] sum_freq = new int[arr.length + 1];

    // Build sum of frequencies
    for (int i = 1; i <= arr.length; i++) {
      sum_freq[i] = sum_freq[i - 1] + freq[i - 1];
    }

    // Populate diagonal, this means that any tree of a single node has the cost as frequency
    for (int i = 0; i < arr.length; i++) {
      cost[i][i] = freq[i];
    }

    // This code fills up the smaller diagonals until we reach cost[0][n - 1]
    // In the example below, it is 2, 3, and 4:
    // x 2 3 4
    // 0 x 2 3
    // 0 0 x 2
    // 0 0 0 x
    for (int L = 2; L <= arr.length; L++) {
      for (int i = 0; i < arr.length - L + 1; i++) {
        int j = i + L - 1;
        cost[i][j] = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
          int tmp = (sum_freq[j + 1] - sum_freq[i]) + // same as "sum(freq, i, j)"
            (k == i ? 0 : cost[i][k - 1]) +
            (k == j ? 0 : cost[k + 1][j]);
          cost[i][j] = Math.min(cost[i][j], tmp);
        }
      }
    }

    return cost[0][arr.length - 1];
  }

  public static void main (String[] args) {
    int[] arr = new int[] {10, 12, 20};
    int[] freq = new int[] {34, 8, 50};
    // int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    // int[] freq = new int[] {34, 8, 50, 10, 23, 45, 20, 11, 52, 27};
    int cost = minCost(arr, freq);
    System.out.println("cost: " + cost);
  }
}
