public class Solution {
  // H-Index solution with O(n log n) time complexity and O(log n) space complexity (sorting).
  public int hIndexSlower(int[] citations) {
    Arrays.sort(citations);
    int h = 0;
    for (int i = citations.length - 1; i >= 0; i--) {
      if (citations[i] <= citations.length && citations[i] <= h) {
        return h;
      }
      h++;
    }
    return h;
  }

  // H-Index solution with O(n) time complexity and O(n) space complexity
  public int hIndex(int[] citations) {
    int max = 0;
    for (int i = 0; i < citations.length; i++) {
      max = Math.max(max, citations[i]);
    }

    int[] h = new int[max + 1];
    for (int i = 0; i < citations.length; i++) {
      h[citations[i]]++;
    }

    int cnt = 0;
    for (int i = h.length - 1; i >= 0; i--) {
      if (h[i] + cnt >= i) {
        return i;
      } else {
        cnt += h[i];
      }
    }
    return cnt;
  }
}
