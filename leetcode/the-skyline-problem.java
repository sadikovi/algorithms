// Solution runs in O(n * log n) time and uses O(n) space, where n is the number of buildings.
// Solution is copied from:
// https://discuss.leetcode.com/topic/22482/short-java-solution/20
//
// Idea:
// Sweepline is used in solving the problem. List<int[]> `heights` is used to save each of the line
// segments including both start and end point. The trick here is to set the start segment as
// negative height. This has a few good uses:
// - first, make sure the start segment comes before the end one after sorting.
// - second, when pushing into the queue, it is very each to distinguish either to add or remove a
// segment.
// Lastly, when the two adjacent buildings share same start and end x value, the next start segment
// always come before due to the negative height, this makes sure that when we peek the queue, we
// always get the value we are supposed to get. When the first building is lower, when we peek the
// queue, we get the height of the second building, and the first building will be removed in the
// next round of iteration. When the second building is lower, the first peek returns the first
// building and since it equals to prev, the height will not be added.
//
class Solution {
  public List<int[]> getSkyline(int[][] buildings) {
    List<int[]> heights = new ArrayList<>();
    for (int[] b : buildings) {
      heights.add(new int[]{b[0], - b[2]});
      heights.add(new int[]{b[1], b[2]});
    }
    Collections.sort(heights, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
    TreeMap<Integer, Integer> heightMap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
    heightMap.put(0, 1);
    int prevHeight = 0;
    List<int[]> skyLine = new LinkedList<int[]>();
    for (int[] h : heights) {
      if (h[1] < 0) {
        Integer cnt = heightMap.get(-h[1]);
        cnt = (cnt == null) ? 1 : cnt + 1;
        heightMap.put(-h[1], cnt);
      } else {
        Integer cnt = heightMap.get(h[1]);
        if (cnt == 1) {
          heightMap.remove(h[1]);
        } else {
          heightMap.put(h[1], cnt - 1);
        }
      }
      int currHeight = heightMap.firstKey();
      if (prevHeight != currHeight) {
        skyLine.add(new int[]{h[0], currHeight});
        prevHeight = currHeight;
      }
    }
    return skyLine;
  }
}
