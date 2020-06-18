import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
  private static class TreeNode {
    Point2D p;
    boolean isX;
    RectHV rect;
    TreeNode left;
    TreeNode right;

    TreeNode(Point2D p, boolean isX, RectHV rect) {
      this.p = p;
      this.isX = isX;
      this.rect = rect;
    }
  }

  private TreeNode root;
  private int size;

  private static TreeNode insertNode(TreeNode root, Point2D p, boolean isX, RectHV rect, int[] size) {
    if (root == null) {
      size[0]++;
      return new TreeNode(p, isX, rect);
    }
    int cmp = root.isX ? Double.compare(p.x(), root.p.x()) : Double.compare(p.y(), root.p.y());
    if (cmp < 0) {
      if (root.isX) {
        rect = new RectHV(root.rect.xmin(), root.rect.ymin(), root.p.x(), root.rect.ymax());
      } else {
        rect = new RectHV(root.rect.xmin(), root.rect.ymin(), root.rect.xmax(), root.p.y());
      }
      root.left = insertNode(root.left, p, !root.isX, rect, size);
    } else if (cmp > 0) {
      if (root.isX) {
        rect = new RectHV(root.p.x(), root.rect.ymin(), root.rect.xmax(), root.rect.ymax());
      } else {
        rect = new RectHV(root.rect.xmin(), root.p.y(), root.rect.xmax(), root.rect.ymax());
      }
      root.right = insertNode(root.right, p, !root.isX, rect, size);
    }
    return root;
  }

  private static TreeNode findNode(TreeNode root, Point2D p) {
    if (root == null) return null;
    if (root.p.compareTo(p) == 0) return root;
    int cmp = root.isX ? Double.compare(p.x(), root.p.x()) : Double.compare(p.y(), root.p.y());
    if (cmp < 0) return findNode(root.left, p);
    return findNode(root.right, p);
  }

  private static void findRange(
      TreeNode root,
      RectHV rect,
      ArrayList<Point2D> res) {
    if (root == null) return;
    if (rect.contains(root.p)) res.add(root.p);
    if (root.left != null && rect.intersects(root.left.rect)) findRange(root.left, rect, res);
    if (root.right != null && rect.intersects(root.right.rect)) findRange(root.right, rect, res);
  }

  private static Point2D findNearest(TreeNode root, Point2D p, Point2D closest) {
    if (root != null) {
      if (closest == null || closest.distanceTo(p) > root.p.distanceTo(p)) {
        closest = root.p;
      }
      if (root.left != null && root.left.rect.distanceTo(p) <= closest.distanceTo(p)) {
        closest = findNearest(root.left, p, closest);
      }
      if (root.right != null && root.right.rect.distanceTo(p) <= closest.distanceTo(p)) {
        closest = findNearest(root.right, p, closest);
      }
    }
    return closest;
  }

  private static void drawNode(TreeNode root) {
    if (root == null) return;

    if (root.isX) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(0.005);
      StdDraw.line(root.p.x(), root.rect.ymin(), root.p.x(), root.rect.ymax());
    } else {
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.setPenRadius(0.005);
      StdDraw.line(root.rect.xmin(), root.p.y(), root.rect.xmax(), root.p.y());
    }

    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(0.01);
    StdDraw.point(root.p.x(), root.p.y());

    drawNode(root.left);
    drawNode(root.right);
  }

  // construct an empty set of points
  public KdTree() {
    root = null;
    size = 0;
  }

  // is the set empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // number of points in the set
  public int size() {
    return size;
  }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null) throw new IllegalArgumentException();
    int[] s = new int[] { size };
    root = insertNode(root, p, true, new RectHV(0.0, 0.0, 1.0, 1.0), s);
    size = s[0];
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null) throw new IllegalArgumentException();
    return findNode(root, p) != null;
  }

  // draw all points to standard draw
  public void draw() {
    drawNode(root);
  }

  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) throw new IllegalArgumentException();
    ArrayList<Point2D> res = new ArrayList<Point2D>();
    findRange(root, rect, res);
    return res;
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (p == null) throw new IllegalArgumentException();
    return findNearest(root, p, null);
  }

  // unit testing of the methods (optional)
  public static void main(String[] args) {
    RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
    System.out.println(rect.intersects(new RectHV(1.1, 1.1, 2.0, 2.0)));
    System.out.println(rect.intersects(new RectHV(0.4, 0.4, 0.6, 0.6)));
    System.out.println(rect.intersects(new RectHV(-2.0, -2.0, 2.0, 2.0)));

    System.out.println(rect.distanceTo(new Point2D(0.0, 0.5)));
  }
}
