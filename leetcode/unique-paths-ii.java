// Solution runs in O(rows * cols) time or O(N) time, where N is the number of elements in the grid,
// and O(1) space.
// Trick is reusing obstacle grid to reduce space complexity, and treating obstacles as 0 paths.
class Solution {
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
    if (obstacleGrid[0][0] == 1) return 0;
    int n = obstacleGrid.length;
    int m = obstacleGrid[0].length;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        obstacleGrid[i][j] -= 2;
      }
    }

    obstacleGrid[0][0] = 1;
    for (int i = 1; i < n; i++) {
      obstacleGrid[i][0] = (obstacleGrid[i][0] == -1) ? 0 : (obstacleGrid[i-1][0] == 1 ? 1 : 0);
    }
    for (int j = 1; j < m; j++) {
      obstacleGrid[0][j] = (obstacleGrid[0][j] == -1) ? 0 : (obstacleGrid[0][j-1] == 1 ? 1 : 0);
    }

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if (obstacleGrid[i][j] == -1) {
          obstacleGrid[i][j] = 0;
        } else {
          obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
        }
      }
    }
    return obstacleGrid[n-1][m-1];
  }
}
