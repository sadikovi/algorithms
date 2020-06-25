import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final WeightedQuickUnionUF uf;
  private final boolean[] open;
  private final int n;
  private final int p;
  private final int q;
  private int numOpen;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0) throw new IllegalArgumentException();
    this.uf = new WeightedQuickUnionUF(n * n + 2);
    this.open = new boolean[n * n];
    this.n = n;
    this.p = n * n;
    this.q = n * n + 1;
    this.numOpen = 0;
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    checkArgument(row);
    checkArgument(col);

    open[idx(row, col)] = true;

    if (row == 1) {
      uf.union(p, idx(row, col));
    }

    if (row == n) {
      uf.union(q, idx(row, col));
    }

    if (row > 1 && open[idx(row - 1, col)]) {
      uf.union(idx(row, col), idx(row - 1, col));
    }
    if (row < n && open[idx(row + 1, col)]) {
      uf.union(idx(row, col), idx(row + 1, col));
    }
    if (col > 1 && open[idx(row, col - 1)]) {
      uf.union(idx(row, col), idx(row, col - 1));
    }
    if (col < n && open[idx(row, col + 1)]) {
      uf.union(idx(row, col), idx(row, col + 1));
    }

    numOpen++;
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    checkArgument(row);
    checkArgument(col);

    return open[idx(row, col)];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    checkArgument(row);
    checkArgument(col);

    return uf.find(idx(row, col)) == uf.find(p);
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

  private int idx(int row, int col) {
    return (col - 1) * n + (row - 1);
  }

  // test client (optional)
  public static void main(String[] args) {
    // do nothing
  }
}
