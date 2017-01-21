public class Solution {
  // Solution runs in O(log n) time
  // Idea is as follows:
  // 1. If citations[mid] == papers it means that we found optimum, h-index equals papers that
  // have at least h citations, going to the right will not find solution, and going to the left
  // will find lower h index.
  // 2. citations[mid] > papers, which means h-index is at most 'papers'
  // 3. citations[mid] < papers, which means we need to keep smallest h-index we have found
  public int hIndex(int[] citations) {
    int low = 0;
    int high = citations.length - 1;
    int h = 0;
    while (low <= high) {
      int mid = (low + high) / 2;
      int papers = citations.length - mid;
      if (citations[mid] == papers) return citations[mid];
      if (citations[mid] > papers) {
        h = Math.max(h, papers);
        high = mid - 1;
      } else {
        h = Math.min(h, papers);
        low = mid + 1;
      }
    }
    return h;
  }
}
