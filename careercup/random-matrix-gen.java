package test;

/**
scala> test.Test.print(test.Test.generate(5))
1 3 1 2 4
3 1 2 1 1
4 1 2 3 1
2 2 4 2 4
3 4 1 3 1
*/
public class Test {
  public static int[][] generate(int n) {
    if (n <= 0 || n > 100) throw new IllegalArgumentException("Invalid n " + n);
    int[][] matrix = new int[n][n];
    generateRecur(matrix, n, n, 0, 0);
    return matrix;
  }

  public static void print(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  private static boolean generateRecur(int[][] matrix, int r, int c, int i, int j) {
    if (i < 0 || j < 0 || i >= r || j >= c) return true;
    if (matrix[i][j] != 0) return true;
    int[] arr = getRandomChoices();
    for (int k : arr) {
      matrix[i][j] = k;
      if (isValid(matrix, r, c, i, j)) {
        boolean ok = generateRecur(matrix, r, c, i, j + 1) && generateRecur(matrix, r, c, i + 1, j);
        if (ok) return true;
      }
      matrix[i][j] = 0;
    }
    return false;
  }

  private static boolean isValid(int[][] matrix, int r, int c, int i, int j) {
    int cnt = 1;
    for (int col = 1; col < c; col++) {
      if (matrix[i][col] != 0 && matrix[i][col] == matrix[i][col - 1]) {
        cnt++;
        if (cnt > 2) return false;
      } else {
        cnt = 1;
      }
    }

    cnt = 1;
    for (int row = 1; row < r; row++) {
      if (matrix[row][j] != 0 && matrix[row][j] == matrix[row - 1][j]) {
        cnt++;
        if (cnt > 2) return false;
      } else {
        cnt = 1;
      }
    }

    return true;
  }

  private static int[] getRandomChoices() {
    int[] arr = new int[4];
    int rand = new java.util.Random().nextInt(43) % 4;
    for (int i = 0; i < arr.length; i++) {
      arr[i] = ((rand + i) % 4) + 1;
    }
    return arr;
  }
}
