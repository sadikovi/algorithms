import java.awt.Color;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
  private Picture picture;
  private int width;
  private int height;
  private double[][] seams;

  // create a seam carver object based on the given picture
  public SeamCarver(Picture picture) {
    if (picture == null) throw new IllegalArgumentException();
    update(new Picture(picture));
  }

  private void update(Picture pic) {
    picture = pic;
    width = picture.width();
    height = picture.height();
    seams = new double[width][height];
    computeEnergy();
  }

  private double dualGradientEnergy(Color cx1, Color cx2, Color cy1, Color cy2) {
    double rx = Math.pow(cx1.getRed() - cx2.getRed(), 2);
    double gx = Math.pow(cx1.getGreen() - cx2.getGreen(), 2);
    double bx = Math.pow(cx1.getBlue() - cx2.getBlue(), 2);

    double ry = Math.pow(cy1.getRed() - cy2.getRed(), 2);
    double gy = Math.pow(cy1.getGreen() - cy2.getGreen(), 2);
    double by = Math.pow(cy1.getBlue() - cy2.getBlue(), 2);

    return Math.sqrt((rx + gx + bx) + (ry + gy + by));
  }

  private void computeEnergy() {
    for (int col = 0; col < width; col++) {
      for (int row = 0; row < height; row++) {
        if (col == 0 || row == 0 || col == width - 1 || row == height - 1) {
          seams[col][row] = 1000;
        } else {
          Color cx1 = picture.get(col - 1, row);
          Color cx2 = picture.get(col + 1, row);
          Color cy1 = picture.get(col, row - 1);
          Color cy2 = picture.get(col, row + 1);
          seams[col][row] = dualGradientEnergy(cx1, cx2, cy1, cy2);
        }
      }
    }
  }

  // current picture
  public Picture picture() {
    return new Picture(picture);
  }

  // width of current picture
  public int width() {
    return width;
  }

  // height of current picture
  public int height() {
    return height;
  }

  // energy of pixel at column x and row y
  public double energy(int x, int y) {
    if (x < 0 || x >= width || y < 0 || y >= height) {
      throw new IllegalArgumentException();
    }
    return seams[x][y];
  }


  private boolean relax(double[][] weights, double weight, int col, int row) {
    if (row < 0 || row >= height || col < 0 || col >= width) return false;
    if (weights[col][row] > seams[col][row] + weight) {
      weights[col][row] = seams[col][row] + weight;
      return true;
    }
    return false;
  }

  // sequence of indices for horizontal seam
  public int[] findHorizontalSeam() {
    double[][] weights = new double[width][height];
    int[][] paths = new int[width][height];

    for (int col = 0; col < width; col++) {
      for (int row = 0; row < height; row++) {
        if (col == 0) {
          weights[col][row] = seams[col][row];
        } else {
          weights[col][row] = Double.POSITIVE_INFINITY;
        }
      }
    }

    for (int col = 0; col < width; col++) {
      for (int row = 0; row < height; row++) {
        double weight = weights[col][row];
        if (relax(weights, weight, col + 1, row)) {
          paths[col + 1][row] = row;
        }
        if (relax(weights, weight, col + 1, row - 1)) {
          paths[col + 1][row - 1] = row;
        }
        if (relax(weights, weight, col + 1, row + 1)) {
          paths[col + 1][row + 1] = row;
        }
      }
    }

    double min = Double.POSITIVE_INFINITY;
    int r = -1, c = -1;
    for (int row = 0; row < height; row++) {
      if (min > weights[width - 1][row]) {
        min = weights[width - 1][row];
        c = width - 1;
        r = row;
      }
    }

    if (r == -1 || c == -1) throw new RuntimeException();

    int[] seam = new int[width];
    while (c >= 0) {
      seam[c] = r;
      r = paths[c][r];
      c--;
    }

    return seam;
  }

  // sequence of indices for vertical seam
  public int[] findVerticalSeam() {
    double[][] weights = new double[width][height];
    int[][] paths = new int[width][height];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        if (row == 0) {
          weights[col][row] = seams[col][row];
        } else {
          weights[col][row] = Double.POSITIVE_INFINITY;
        }
      }
    }

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        double weight = weights[col][row];
        if (relax(weights, weight, col, row + 1)) {
          paths[col][row + 1] = col;
        }
        if (relax(weights, weight, col - 1, row + 1)) {
          paths[col - 1][row + 1] = col;
        }
        if (relax(weights, weight, col + 1, row + 1)) {
          paths[col + 1][row + 1] = col;
        }
      }
    }

    double min = Double.POSITIVE_INFINITY;
    int r = -1, c = -1;
    for (int col = 0; col < width; col++) {
      if (min > weights[col][height - 1]) {
        min = weights[col][height - 1];
        c = col;
        r = height - 1;
      }
    }

    if (r == -1 || c == -1) throw new RuntimeException();

    int[] seam = new int[height];
    while (r >= 0) {
      seam[r] = c;
      c = paths[c][r];
      r--;
    }

    return seam;
  }

  // remove horizontal seam from current picture
  public void removeHorizontalSeam(int[] seam) {
    if (seam == null) throw new IllegalArgumentException();
    if (height <= 1) throw new IllegalArgumentException();
    if (seam.length != width) throw new IllegalArgumentException();
    for (int i = 0; i < width; i++) {
      if (seam[i] < 0 || seam[i] >= height || i > 0 && Math.abs(seam[i] - seam[i - 1]) > 1) {
        throw new IllegalArgumentException();
      }
    }

    Picture tmp = new Picture(width, height - 1);
    for (int col = 0; col < width; col++) {
      int rowT = 0;
      for (int row = 0; row < height; row++) {
        if (seam[col] != row) {
          tmp.set(col, rowT++, picture.get(col, row));
        }
      }
    }

    update(tmp);
  }

  // remove vertical seam from current picture
  public void removeVerticalSeam(int[] seam) {
    if (seam == null) throw new IllegalArgumentException();
    if (width <= 1) throw new IllegalArgumentException();
    if (seam.length != height) throw new IllegalArgumentException();
    for (int i = 0; i < height; i++) {
      if (seam[i] < 0 || seam[i] >= width || i > 0 && Math.abs(seam[i] - seam[i - 1]) > 1) {
        throw new IllegalArgumentException();
      }
    }

    Picture tmp = new Picture(width - 1, height);
    for (int row = 0; row < height; row++) {
      int colT = 0;
      for (int col = 0; col < width; col++) {
        if (seam[row] != col) {
          tmp.set(colT++, row, picture.get(col, row));
        }
      }
    }

    update(tmp);
  }

  //  unit testing (optional)
  public static void main(String[] args) {
    // Picture pic = new Picture(3, 4);
    //
    // pic.set(0, 0, new Color(255, 101, 51));
    // pic.set(1, 0, new Color(255, 101, 153));
    // pic.set(2, 0, new Color(255, 101, 255));
    //
    // pic.set(0, 1, new Color(255, 153, 51));
    // pic.set(1, 1, new Color(255, 153, 153));
    // pic.set(2, 1, new Color(255, 153, 255));
    //
    // pic.set(0, 2, new Color(255, 203, 51));
    // pic.set(1, 2, new Color(255, 204, 153));
    // pic.set(2, 2, new Color(255, 205, 255));
    //
    // pic.set(0, 3, new Color(255, 255, 51));
    // pic.set(1, 3, new Color(255, 255, 153));
    // pic.set(2, 3, new Color(255, 255, 255));

    // SeamCarver sc = new SeamCarver(new Picture("princeton/part2/week2/HJoceanSmall.png"));
    //
    // sc.picture().show();
    //
    // for (int i = 0; i < 40; i++) {
    //   // System.out.println(java.util.Arrays.toString(sc.findVerticalSeam()));
    //   // System.out.println(java.util.Arrays.toString(sc.findHorizontalSeam()));
    //   sc.removeVerticalSeam(sc.findVerticalSeam());
    //   sc.removeHorizontalSeam(sc.findHorizontalSeam());
    // }
    //
    // sc.picture().show();

    // SeamCarver sc = new SeamCarver(new Picture("princeton/part2/week2/10x10.png"));
    // sc.removeVerticalSeam(new int[] { 8, 8, 8, 6, 5, 5, 4, 5, 6, 5 });
    // sc.removeVerticalSeam(null);
  }
}
