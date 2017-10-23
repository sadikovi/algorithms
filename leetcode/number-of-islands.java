// The time complexity is O(m*n), where m and n are the width and height of the grid, respectively.
// The time complexity is only "linear" in the number of cells in the grid.

// Since we are conducting a depth-first search on grid, we have to touch every cell in grid at
// least once. However, in the worst-conceivable case, we're only touching every interior cell in
// grid five times (and touching the exterior cells three or four times).

class Solution {
  public int numIslands(char[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) return 0;
    int rows = grid.length, cols = grid[0].length, count = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == '1') {
          markIsland(grid, rows, cols, i, j);
          count++;
        }
      }
    }
    return count;
  }

  private void markIsland(char[][] grid, int rows, int cols, int i, int j) {
    if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == '0') return;
    grid[i][j] = '0';
    markIsland(grid, rows, cols, i + 1, j);
    markIsland(grid, rows, cols, i, j + 1);
    markIsland(grid, rows, cols, i - 1, j);
    markIsland(grid, rows, cols, i, j - 1);
  }
}
