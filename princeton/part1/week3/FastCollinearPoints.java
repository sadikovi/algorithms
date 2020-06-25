import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {

  private final LineSegment[] lineSegments;

  /**
   * Finds all line segments containing 4 points or more points.
   */
  public FastCollinearPoints(Point[] points) {
    checkNull(points);
    Point[] sortedPoints = points.clone();
    Arrays.sort(sortedPoints);
    checkDuplicate(sortedPoints);

    final int N = points.length;
    final List<LineSegment> maxLineSegments = new LinkedList<>();

    for (int i = 0; i < N; i++) {
      Point p = sortedPoints[i];
      Point[] pointsBySlope = sortedPoints.clone();
      Arrays.sort(pointsBySlope, p.slopeOrder());

      // Notice the difference between "sortedPoints" & "pointsBySlope":
      // the below points are taken from "pointsBySlope".
      int x = 1;
      while (x < N) {
        LinkedList<Point> candidates = new LinkedList<>();
        final double SLOPE_REF = p.slopeTo(pointsBySlope[x]);

        do {
          candidates.add(pointsBySlope[x++]);
        } while (x < N && p.slopeTo(pointsBySlope[x]) == SLOPE_REF);

        // Candidates have a max line segment if ...
        // 1. Candidates are collinear: At least 4 points are located
        //    at the same line, so at least 3 without "p".
        // 2. The max line segment is created by the point "p" and the
        //    last point in candidates: so "p" must be the smallest
        //    point having this slope comparing to all candidates.
        if (candidates.size() >= 3 && p.compareTo(candidates.peek()) < 0) {
          Point min = p;
          Point max = candidates.removeLast();
          maxLineSegments.add(new LineSegment(min, max));
        }
      }
    }
    lineSegments = maxLineSegments.toArray(new LineSegment[0]);
  }

  private void checkNull(Point[] points) {
    if (points == null) {
      throw new NullPointerException("The array \"Points\" is null.");
    }
    for (Point p : points) {
      if (p == null) {
          throw new NullPointerException("The array \"Points\" contains null element.");
      }
    }
  }

  private void checkDuplicate(Point[] points) {
    for (int i = 0; i < points.length - 1; i++) {
      if (points[i].compareTo(points[i + 1]) == 0) {
        throw new IllegalArgumentException("Duplicate(s) found.");
      }
    }
  }

  /**
   * The number of line segments.
   */
  public int numberOfSegments() {
    return lineSegments.length;
  }

  /**
   * The line segments.
   */
  public LineSegment[] segments() {
    return lineSegments.clone();
  }
}

// import java.util.Arrays;
// import java.util.ArrayList;
// import java.util.Collections;
//
// // import edu.princeton.cs.algs4.In;
// // import edu.princeton.cs.algs4.StdDraw;
// // import edu.princeton.cs.algs4.StdOut;
//
// public class FastCollinearPoints {
//   private final LineSegment[] segments;
//
//   // finds all line segments containing 4 or more points
//   public FastCollinearPoints(Point[] points) {
//     if (points == null) throw new IllegalArgumentException();
//
//     for (int i = 0; i < points.length; i++) {
//       if (points[i] == null) throw new IllegalArgumentException();
//       for (int j = i + 1; j < points.length; j++) {
//         if (points[i] == null || points[j] == null) throw new IllegalArgumentException();
//         if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
//       }
//     }
//
//     Arrays.sort(points);
//     ArrayList<Point> chunk = new ArrayList<Point>();
//     ArrayList<LineSegment> seg = new ArrayList<LineSegment>();
//
//     for (int i = 0; i < points.length; i++) {
//       Point p = points[i];
//
//       Point[] cp = points.clone();
//       Arrays.sort(cp, p.slopeOrder());
//
//       for (int j = 0; j < cp.length; j++) {
//         if (cp[j].compareTo(p) == 0) {
//           continue;
//         }
//
//         if (!chunk.isEmpty() && p.slopeTo(cp[j]) == p.slopeTo(chunk.get(0))) {
//           chunk.add(cp[j]);
//         } else {
//           if (chunk.size() >= 3 && p.compareTo(chunk.get(0)) < 0) {
//             chunk.add(p);
//             seg.add(new LineSegment(Collections.min(chunk), Collections.max(chunk)));
//           }
//           chunk.clear();
//           chunk.add(cp[j]);
//         }
//       }
//     }
//
//     this.segments = new LineSegment[seg.size()];
//     for (int i = 0; i < seg.size(); i++) {
//       segments[i] = seg.get(i);
//     }
//   }
//
//   // the number of line segments
//   public int numberOfSegments() {
//     return segments.length;
//   }
//
//   // the line segments
//   public LineSegment[] segments() {
//     return segments.clone();
//   }
//
//   // public static void main(String[] args) {
//   //   // read the n points from a file
//   //   In in = new In(args[0]);
//   //   int n = in.readInt();
//   //   Point[] points = new Point[n];
//   //   for (int i = 0; i < n; i++) {
//   //     int x = in.readInt();
//   //     int y = in.readInt();
//   //     points[i] = new Point(x, y);
//   //   }
//   //
//   //   // draw the points
//   //   StdDraw.enableDoubleBuffering();
//   //   StdDraw.setXscale(0, 32768);
//   //   StdDraw.setYscale(0, 32768);
//   //   for (Point p : points) {
//   //     p.draw();
//   //   }
//   //   StdDraw.show();
//   //
//   //   // print and draw the line segments
//   //   FastCollinearPoints collinear = new FastCollinearPoints(points);
//   //   for (LineSegment segment : collinear.segments()) {
//   //     StdOut.println(segment);
//   //     segment.draw();
//   //   }
//   //   StdDraw.show();
//   // }
// }
