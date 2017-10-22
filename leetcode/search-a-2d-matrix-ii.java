// Solution runs in O(rows * log(cols))
// Simple idea is treating matrix as a list of sorted arrays and applying binary search on each
// array to find element.
class Solution {
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0 || matrix[0].length == 0) return false;
    int rows = matrix.length;
    int cols = matrix[0].length;
    for (int i = 0; i < rows; i++) {
      if (search(matrix[i], 0, cols - 1, target)) return true;
    }
    return false;
  }

  private boolean search(int[] arr, int start, int end, int target) {
    while (start <= end) {
      int mid = (start + end) / 2;
      if (arr[mid] == target) return true;
      if (arr[mid] > target) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return false;
  }
}

// Solution runs in O(rows + cols), inspired by a leetcode solution.
// Idea is picking top right corner element and checking it against target element. If they match
// return it, otherwise, if element > target, then there are no elements matching target in current
// column - decrement column. If element < target, then there are no elements matching target in
// current row - increment row. Thus, we always reduce to left-bottom matrix until we find match.
class Solution {
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0 || matrix[0].length == 0) return false;
    int rows = matrix.length;
    int cols = matrix[0].length;
    int i = 0, j = cols - 1;
    while (i < rows && j >= 0) {
      if (matrix[i][j] == target) return true;
      if (matrix[i][j] < target) {
        ++i;
      } else {
        --j;
      }
    }
    return false;
  }
}
