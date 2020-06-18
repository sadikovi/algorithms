import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
  private final SET<Point2D> bst;

  // construct an empty set of points
  public PointSET() {
    this.bst = new SET<Point2D>();
  }

  // is the set empty?
  public boolean isEmpty() {
    return this.bst.isEmpty();
  }

  // number of points in the set
  public int size() {
    return this.bst.size();
  }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null) throw new IllegalArgumentException();
    this.bst.add(p);
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null) throw new IllegalArgumentException();
    return this.bst.contains(p);
  }

  // draw all points to standard draw
  public void draw() {
    for (Point2D p : this.bst) {
      StdDraw.point(p.x(), p.y());
    }
  }

  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) throw new IllegalArgumentException();
    ArrayList<Point2D> res = new ArrayList<Point2D>();
    for (Point2D p : this.bst) {
      if (rect.contains(p)) {
        res.add(p);
      }
    }
    return res;
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (p == null) throw new IllegalArgumentException();

    Point2D nearest = null;
    double dist = 0;
    for (Point2D d : this.bst) {
      if (nearest == null || p.distanceSquaredTo(d) < dist) {
        nearest = d;
        dist = p.distanceSquaredTo(d);
      }
    }
    return nearest;
  }

  // unit testing of the methods (optional)
  public static void main(String[] args) {
    Point2D p = new Point2D(0.0, 0.0);
    System.out.println(p.compareTo(new Point2D(0.0, 0.0))); // 0
    System.out.println(p.compareTo(new Point2D(1.0, 0.0))); // -1
    System.out.println(p.compareTo(new Point2D(-1.0, 0.0))); // 1
    System.out.println(p.compareTo(new Point2D(0.0, 1.0))); // -1
    System.out.println(p.compareTo(new Point2D(0.0, -1.0))); // 1
    System.out.println(p.compareTo(new Point2D(1.0, 1.0))); // -1
    System.out.println(p.compareTo(new Point2D(-1.0, 1.0))); // -1
  }
}
