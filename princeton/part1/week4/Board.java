import java.util.ArrayList;

public class Board {
  private static class Tile {
    int x;
    int y;
  }

  private final int n;
  private final int[][] tiles;
  private final Tile empty;
  private final int hamming;
  private final int manhattan;

  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    this.n = tiles.length;
    this.tiles = copy(tiles);
    this.empty = getEmptyTile();
    this.hamming = computeHamming();
    this.manhattan = computeManhattan();
  }

  private Tile getEmptyTile() {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (tiles[i][j] == 0) {
          Tile tile = new Tile();
          tile.x = i;
          tile.y = j;
          return tile;
        }
      }
    }

    return null;
  }

  private int computeHamming() {
    int dist = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == empty.x && j == empty.y) {
          // empty tile, ignore
        } else {
          int exp = i * n + j + 1;
          if (tiles[i][j] != exp) {
            dist++;
          }
        }
      }
    }

    return dist;
  }

  private int computeManhattan() {
    int dist = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == empty.x && j == empty.y) {
          // empty tile, ignore
        } else {
          int v = tiles[i][j] - 1;
          int iExp = v / n;
          int jExp = v - iExp * n;
          dist += Math.abs(i - iExp) + Math.abs(j - jExp);
        }
      }
    }

    return dist;
  }

  private int[][] copy(int[][] in) {
    int[][] out = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        out[i][j] = in[i][j];
      }
    }

    return out;
  }

  private Board copyWithSwap(int i1, int j1, int i2, int j2) {
    int[][] newTiles = copy(this.tiles);

    int tmp = newTiles[i1][j1];
    newTiles[i1][j1] = newTiles[i2][j2];
    newTiles[i2][j2] = tmp;

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
    return hamming == 0 && manhattan == 0;
  }

  // does this board equal y?
  public boolean equals(Object y) {
    if (y == null) return false;
    if (!(y instanceof Board)) return false;
    Board that = (Board) y;

    if (this.n != that.n) return false;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (this.tiles[i][j] != that.tiles[i][j]) return false;
      }
    }

    return true;
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    ArrayList<Board> neighbours = new ArrayList<Board>();

    int x = empty.x;
    int y = empty.y;

    if (x > 0) neighbours.add(copyWithSwap(x, y, x - 1, y));
    if (x < n - 1) neighbours.add(copyWithSwap(x, y, x + 1, y));
    if (y > 0) neighbours.add(copyWithSwap(x, y, x, y - 1));
    if (y < n - 1) neighbours.add(copyWithSwap(x, y, x, y + 1));

    return neighbours;
  }

  // a board that is obtained by exchanging any pair of tiles
  public Board twin() {
    int x = empty.x;
    int y = empty.y;

    int i1 = -1, j1 = -1, i2 = -1, j2 = -1;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i1 == -1 && (i != x || j != y)) {
          i1 = i;
          j1 = j;
        } else if (i2 == -1 && (i != x || j != y)) {
          i2 = i;
          j2 = j;
        }
      }
    }

    if (i1 == i2 && j1 == j2) throw new RuntimeException("Incorrect twin");
    if (i1 < 0 || i2 < 0 || j1 < 0 || j2 < 0) throw new RuntimeException("Incorrect twin i/j");

    return copyWithSwap(i1, j1, i2, j2);
  }

  // unit testing (not graded)
  public static void main(String[] args) {
    int[][] tiles = new int[][] {
      {1, 0, 3},
      {4, 2, 5},
      {7, 8, 6}
    };

    Board b = new Board(tiles);

    System.out.println("\nHamming distance:");
    System.out.println(b.hamming());

    System.out.println("\nManhattan distance:");
    System.out.println(b.manhattan());

    System.out.println("\nBoard:");
    System.out.println(b);

    System.out.println("\nTwin board:");
    System.out.println(b.twin());

    System.out.println("\nNeighbours:");
    for (Board n : b.neighbors()) {
      System.out.println(n);
    }
  }
}
