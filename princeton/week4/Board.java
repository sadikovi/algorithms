import java.util.ArrayList;

public class Board {
  private final int n;
  private final int[][] tiles;
  private final int hamming;
  private final int manhattan;
  private final int emptyX;
  private final int emptyY;

  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    this.n = tiles.length;
    this.tiles = copy(tiles, this.n);
    this.hamming = computeHamming();
    this.manhattan = computeManhattan();
    int[] emptyArray = empty();
    this.emptyX = emptyArray[0];
    this.emptyY = emptyArray[1];
  }

  private int computeHamming() {
    int counter = 0;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - 1; j++) {
        if (tiles[i][j] != (1 + j + i * n)) {
          counter++;
        }
      }
    }
    return counter;
  }

  private int computeManhattan() {
    int sum = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (tiles[i][j] != 0) {
          int v = tiles[i][j] - 1;
          int i0 = v / n;
          int j0 = v - i0 * n;
          sum += Math.abs(i - i0) + Math.abs(j - j0);
        }
      }
    }
    return sum;
  }

  private int[] empty() {
    int[] point = new int[2];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (tiles[i][j] == 0) {
          point[0] = i;
          point[1] = j;
          return point;
        }
      }
    }
    return point;
  }

  private int[][] copy(int[][] tiles, int n) {
    int[][] newTiles = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        newTiles[i][j] = tiles[i][j];
      }
    }
    return newTiles;
  }

  private Board boardWithSwap(int x, int y, int x0, int y0) {
    int[][] newTiles = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        newTiles[i][j] = tiles[i][j];
      }
    }

    int tmp = newTiles[x][y];
    newTiles[x][y] = newTiles[x0][y0];
    newTiles[x0][y0] = tmp;

    return new Board(newTiles);
  }

  // string representation of this board
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(n + "\n");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        sb.append(" " + tiles[i][j]);
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  // board dimension n
  public int dimension() {
    return n;
  }

  // number of tiles out of place
  public int hamming() {
    return hamming;
  }

  // sum of Manhattan distances between tiles and goal
  public int manhattan() {
    return manhattan;
  }

  // is this board the goal board?
  public boolean isGoal() {
    return this.hamming == 0 && this.manhattan == 0;
  }

  // does this board equal y?
  public boolean equals(Object y) {
    if (y == null) return false;
    if (y.getClass() != Board.class) return false;

    Board that = (Board) y;

    if (that.n != this.n) return false;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (that.tiles[i][j] != this.tiles[i][j]) return false;
      }
    }

    return true;
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    ArrayList<Board> neighbours = new ArrayList<Board>();

    int x = emptyX;
    int y = emptyY;

    if (x > 0) neighbours.add(boardWithSwap(x, y, x - 1, y));
    if (x < n - 1) neighbours.add(boardWithSwap(x, y, x + 1, y));
    if (y > 0) neighbours.add(boardWithSwap(x, y, x, y - 1));
    if (y < n - 1) neighbours.add(boardWithSwap(x, y, x, y + 1));

    return neighbours;
  }

  // a board that is obtained by exchanging any pair of tiles
  public Board twin() {
    int i1 = -1, j1 = -1, i2 = -1, j2 = -1;

    int x = emptyX;
    int y = emptyY;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if ((i != x || j != y) && i1 == -1) {
          i1 = i;
          j1 = j;
        } else if ((i != x || j != y) && i2 == -1) {
          i2 = i;
          j2 = j;
        }
      }
    }

    return boardWithSwap(i1, j1, i2, j2);
  }

  // unit testing (not graded)
  public static void main(String[] args) {
    // TODO.
  }
}
