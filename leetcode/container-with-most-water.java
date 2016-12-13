public class Solution {
  // naive, brute-force approach over difference combinations of areas
  public int maxAreaNaive(int[] height) {
    int n = height.length;
    int area = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int sqr = Math.min(height[i], height[j]) * (j - i);
        area = Math.max(area, sqr);
      }
    }
    return area;
  }

  // Idea is moving pointer that will imply increase in area, for example, if left is less than
  // right at the current iteration, there is no point to move right, because it will be bound be
  // left pointer, but if we move left pointer we might get at least right pointer area.
  public int maxArea(int[] height) {
    int i = 0;
    int j = height.length - 1;
    int area = 0;
    while (i < j) {
      int sqr = Math.min(height[i], height[j]) * (j - i);
      area = Math.max(area, sqr);
      if (height[i] < height[j]) {
        i++;
      } else {
        j--;
      }
    }
    return area;
  }
}
