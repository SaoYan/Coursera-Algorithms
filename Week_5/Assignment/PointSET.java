import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class PointSET {
  private final TreeSet<Point2D> pointSet;

  // construct an empty set of points
  public PointSET() { pointSet = new TreeSet<Point2D>(); }

  // is the set empty?
  public boolean isEmpty() { return pointSet.isEmpty(); }

  // number of points in the set
  public int size() { return pointSet.size(); }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null) throw new IllegalArgumentException("argument cannot be null!");
    if (!pointSet.contains(p))
      pointSet.add(p);
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null) throw new IllegalArgumentException("argument cannot be null!");
    return pointSet.contains(p);
  }

  // draw all points to standard draw
  public void draw() {
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius();
    for (Point2D point : pointSet)
      point.draw();
    StdDraw.show();
  }

  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) throw new IllegalArgumentException("argument cannot be null!");
    if (isEmpty()) return null;
    TreeSet<Point2D> insidePointSet = new TreeSet<Point2D>();
    for (Point2D point : pointSet)
      if (rect.contains(point))
        insidePointSet.add(point);
    return insidePointSet;
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (p == null) throw new IllegalArgumentException("argument cannot be null!");
    if (isEmpty()) return null;
    Point2D nearestPoint = null;
    double minDist = Double.POSITIVE_INFINITY;
    for (Point2D point : pointSet)
      if (point.distanceSquaredTo(p) < minDist) {
        minDist = point.distanceSquaredTo(p);
        nearestPoint = point;
      }
    return nearestPoint;
  }

  public static void main(String[] args) {
    String filename = args[0];
    In in = new In(filename);
    KdTree kdtree = new KdTree();
    while (!in.isEmpty()) {
        double x = in.readDouble();
        double y = in.readDouble();
        Point2D p = new Point2D(x, y);
        kdtree.insert(p);
    }

    Point2D query = new Point2D(0.1, 0.7);
    StdOut.println(kdtree.nearest(query));
  }
}
