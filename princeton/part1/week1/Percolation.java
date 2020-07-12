import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final WeightedQuickUnionUF uf;
  private final boolean[][] open;
  private final int n;
  private final int p;
  private final int q;
  private int numOpen;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0) throw new IllegalArgumentException();
    this.uf = new WeightedQuickUnionUF(n * n + 2);
    this.p = n * n;
    this.q = n * n + 1;
    this.open = new boolean[n][n];
    this.n = n;
    this.numOpen = 0;
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    checkArgument(row);
    checkArgument(col);

    if (isOpen(row, col)) return;

    row--;
    col--;

    numOpen++;
    open[row][col] = true;

    if (row > 0 && open[row - 1][col]) uf.union(idx(row, col), idx(row - 1, col));
    if (row < n - 1 && open[row + 1][col]) uf.union(idx(row, col), idx(row + 1, col));
    if (col > 0 && open[row][col - 1]) uf.union(idx(row, col), idx(row, col - 1));
    if (col < n - 1 && open[row][col + 1]) uf.union(idx(row, col), idx(row, col + 1));

    if (row == 0) {
      uf.union(p, idx(row, col));
    }

    if (row == n - 1) {
      uf.union(q, idx(row, col));
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    checkArgument(row);
    checkArgument(col);
    return open[row - 1][col - 1];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    checkArgument(row);
    checkArgument(col);
    return open[row - 1][col - 1] && uf.find(p) == uf.find(idx(row - 1, col - 1));
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return numOpen;
  }

  // does the system percolate?
  public boolean percolates() {
    return uf.find(p) == uf.find(q);
  }

  private void checkArgument(int i) {
    if (i < 1 || i > n) throw new IllegalArgumentException();
  }

  // row and col are 0-based
  private int idx(int row, int col) {
    return col * n + row;
  }

  // test client (optional)
  public static void main(String[] args) {
    // do nothing
    // In in = new In(args[0]);
    // int n = in.readInt();
    // Percolation p = new Percolation(n);
    // while (!in.isEmpty()) {
    //   p.open(in.readInt(), in.readInt());
    // }
    // System.out.println(p.isOpen(18, 1));
    // System.out.println(p.isFull(18, 1));
    // System.out.println(p.isOpen(22, 28));
    // System.out.println(p.isFull(22, 28));
    // System.out.println(p.isOpen(69, 9));
    // System.out.println(p.isFull(69, 9));
  }
}
