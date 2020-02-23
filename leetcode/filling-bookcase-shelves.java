// Recursive solution (Time Limit Exceeded).
// Time complexity is O(2^n), space complexity O(n).
class Solution {
  public int minHeightShelves(int[][] books, int shelf_width) {
    if (books.length == 0) return 0;
    return minHeight(books, 1, shelf_width, books[0][0], books[0][1]);
  }

  // Recursive call to compute the following cases:
  // - book is on the current/same shelf as long as it fits by width
  // - book is on a different shelf
  private int minHeight(int[][] books, int i, int shelf_width, int curr_width, int curr_height) {
    if (i >= books.length) return curr_height;
    int same_shelf = Integer.MAX_VALUE;
    if (curr_width + books[i][0] <= shelf_width) {
      same_shelf = minHeight(
        books, i + 1, shelf_width, curr_width + books[i][0], Math.max(curr_height, books[i][1]));
    }
    int new_shelf = curr_height + minHeight(books, i + 1, shelf_width, books[i][0], books[i][1]);
    return Math.min(same_shelf, new_shelf);
  }
}

// Dynamic programming solution.
// Passes all test cases on leetcode.
// Time complexity is O(n^2), space complexity is O(n).
// - best case: all books are thick enough, so only one book fits on a shelf.
// - worst case: all books fit on one shelf.
class Solution {
  public int minHeightShelves(int[][] books, int shelf_width) {
    if (books.length == 0) return 0;

    int[] height = new int[books.length + 1];
    height[0] = 0;
    height[1] = books[0][1];

    for (int i = 2; i < height.length; i++) {
      height[i] = Integer.MAX_VALUE;
      int j = i, curr_width = 0, curr_height = 0;
      // by default we place a book on a different shelf and then add a book from the previous shelf
      // and see if we make the overall height smaller.
      while (j >= 1 && curr_width + books[j - 1][0] <= shelf_width) {
        curr_width += books[j - 1][0];
        curr_height = Math.max(books[j - 1][1], curr_height);
        height[i] = Math.min(height[i], height[j - 1] + curr_height);
        j--;
      }
    }

    return height[books.length];
  }
}

// Test cases:
// [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]]
// 4
// [[1,1],[2,3],[2,3],[1,1]]
// 4
// [[1,1],[2,3],[1,1],[2,3],[1,1]]
// 4
// [[1,1],[2,3],[2,3],[1,1],[3,3]]
// 4
// [[3,2],[3,2],[3,2],[3,2]]
// 4
// [[3,2]]
// 4
// [[1,1],[2,3],[2,3],[2,3]]
// 6
