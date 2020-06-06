import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// import edu.princeton.cs.algs4.In;
// import edu.princeton.cs.algs4.StdDraw;
// import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
  private final LineSegment[] segments;

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    if (points == null) throw new IllegalArgumentException();

    for (int i = 0; i < points.length; i++) {
      if (points[i] == null) throw new IllegalArgumentException();
      for (int j = i + 1; j < points.length; j++) {
        if (points[i] == null || points[j] == null) throw new IllegalArgumentException();
        if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
      }
    }

    List<LineSegment> tmp = new ArrayList<LineSegment>();
    Point[] arr = new Point[4];

    for (int p = 0; p < points.length; p++) {
      for (int q = p + 1; q < points.length; q++) {
        for (int r = q + 1; r < points.length; r++) {
          for (int s = r + 1; s < points.length; s++) {
            Comparator<Point> cmp = points[p].slopeOrder();
            if (cmp.compare(points[q], points[r]) == 0 && cmp.compare(points[r], points[s]) == 0) {
              arr[0] = points[p];
              arr[1] = points[q];
              arr[2] = points[r];
              arr[3] = points[s];
              Arrays.sort(arr);

              tmp.add(new LineSegment(arr[0], arr[3]));
            }
          }
        }
      }
    }

    segments = new LineSegment[tmp.size()];
    for (int i = 0; i < segments.length; i++) {
      segments[i] = tmp.get(i);
    }
  }

  // the number of line segments
  public int numberOfSegments() {
    return segments.length;
  }

  // the line segments
  public LineSegment[] segments() {
    return segments.clone();
  }

  // public static void main(String[] args) {
  //   // read the n points from a file
  //   In in = new In(args[0]);
  //   int n = in.readInt();
  //   Point[] points = new Point[n];
  //   for (int i = 0; i < n; i++) {
  //     int x = in.readInt();
  //     int y = in.readInt();
  //     points[i] = new Point(x, y);
  //   }
  //
  //   // draw the points
  //   StdDraw.enableDoubleBuffering();
  //   StdDraw.setXscale(0, 32768);
  //   StdDraw.setYscale(0, 32768);
  //   for (Point p : points) {
  //     p.draw();
  //   }
  //   StdDraw.show();
  //
  //   // print and draw the line segments
  //   BruteCollinearPoints collinear = new BruteCollinearPoints(points);
  //   for (LineSegment segment : collinear.segments()) {
  //     StdOut.println(segment);
  //     segment.draw();
  //   }
  //   StdDraw.show();
  // }
}
