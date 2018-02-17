// My solution, runs in O(n^2) time and O(n) space (actually k chunks, but there can be at most n
// chunks). Not particularly efficient solution, takes 4ms to run all test cases.
class Solution {
  public int maxChunksToSorted(int[] arr) {
    int chunks = 0, end = arr.length - 1;
    while (end >= 0) {
      end = locateChunk(arr, end) - 1;
      chunks++;
    }
    return chunks;
  }

  private int locateChunk(int[] arr, int end) {
    int min = end;
    while (end >= 0 && arr[end] != min) {
      int j = arr[end];
      while (arr[j] != j) {
        int t = arr[j];
        arr[j] = j;
        j = t;
        min = Math.min(min, j);
      }
      end--;
    }
    return min;
  }
}

// Official solution runs in O(n) time and O(1) space.
class Solution {
  public int maxChunksToSorted(int[] arr) {
    int ans = 0, max = 0;
    for (int i = 0; i < arr.length; ++i) {
      max = Math.max(max, arr[i]);
      if (max == i) ans++;
    }
    return ans;
  }
}
