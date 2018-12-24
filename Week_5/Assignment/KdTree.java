import java.util.Comparator;
import java.util.LinkedList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class KdTree {
  private Node root;
  private static final boolean ODD  = true;
  private static final boolean EVEN = false;

  private class Node {
    private final Point2D p;
    private final RectHV rect;
    private Node lb, rt;
    private int count;

    public Node(Point2D p, RectHV rect) {
      this.p = p;
      this.rect = rect;
      this.lb = null;
      this.rt = null;
      this.count = 1;
    }
  }

  // construct an empty set of points
  public KdTree() { root = null; }

  // is the set empty?
  public boolean isEmpty() { return root == null; }

  // number of points in the set
  public int size() { return size(root); }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null) throw new IllegalArgumentException("argument cannot be null!");
    root = insert(root, p, ODD, new RectHV(0, 0, 1, 1));
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null) throw new IllegalArgumentException("argument cannot be null!");
    return contains(root, p, ODD);
  }

  // draw KD tree
  public void draw() {
    draw(root, ODD);
    StdDraw.show();
  }

  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) throw new IllegalArgumentException("argument cannot be null!");
    LinkedList<Point2D> insidePointList = new LinkedList<Point2D>();
    range(root, rect, insidePointList);
    return insidePointList;
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (p == null) throw new IllegalArgumentException("argument cannot be null!");
    double nearestDist = Double.POSITIVE_INFINITY;
    Point2D nearestPoint = nearest(root, p, nearestDist);
    return nearestPoint;
  }

  private int size(Node x) {
    if (x == null) return 0;
    return x.count;
  }

  private Node insert(Node x, Point2D p, boolean flag, RectHV rect) {
    if (x == null) return new Node(p, rect);
    if (x.p.equals(p)) return x;

    double cmp;
    if (flag == ODD) cmp = p.x() - x.p.x();
    else             cmp = p.y() - x.p.y();

    RectHV nextRect;
    if (cmp <= 0) {
      if (x.lb == null) {
        if (flag == ODD)
          nextRect = new RectHV(x.rect.xmin(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        else
          nextRect = new RectHV(x.rect.xmin(), x.rect.ymin(), x.rect.xmax(), x.p.y());
      }
      else nextRect = null;
      x.lb = insert(x.lb, p, !flag, nextRect);
    }
    else {
      if (x.rt == null) {
        if (flag == ODD)
          nextRect = new RectHV(x.p.x(), x.rect.ymin(), x.rect.xmax(), x.rect.ymax());
        else
          nextRect = new RectHV(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.rect.ymax());
      }
      else nextRect = null;
      x.rt = insert(x.rt, p, !flag, nextRect);
    }
    x.count = 1 + size(x.lb) + size(x.rt);
    return x;
  }

  private boolean contains(Node x, Point2D p, boolean flag) {
    if (x == null) return false;
    if (x.p.compareTo(p) == 0) return true;

    double cmp;
    if (flag == ODD) cmp = p.x() - x.p.x();
    else             cmp = p.y() - x.p.y();

    if (cmp <= 0) return contains(x.lb, p, !flag);
    else          return contains(x.rt, p, !flag);
  }

  private void draw(Node x, boolean flag) {
    if (x == null) return;

    StdDraw.setPenRadius(0.01);
    StdDraw.setPenColor(StdDraw.BLACK);
    x.p.draw();

    if (flag == ODD) {
      StdDraw.setPenRadius();
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
    }
    else {
      StdDraw.setPenRadius();
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
    }
    draw(x.lb, !flag);
    draw(x.rt, !flag);
  }

  private void range(Node x, RectHV rect, LinkedList<Point2D> list) {
    if (x == null) return;
    if (x.rect.intersects(rect)) {
      if (rect.contains(x.p))
        list.add(x.p);
      range(x.lb, rect, list);
      range(x.rt, rect, list);
    }
  }

  private Point2D nearest(Node x, Point2D p, double nearestD) {
    if (x == null) return null;
    if (x.rect.distanceSquaredTo(p) >= nearestD) return null;

    Point2D nearestPoint = null;
    double nearestDist = nearestD;
    if (x.p.distanceSquaredTo(p) < nearestDist) {
      nearestDist = x.p.distanceSquaredTo(p);
      nearestPoint = x.p;
    }

    Node firstSearch = x.lb;
    Node lastSearch  = x.rt;
    if (firstSearch != null && lastSearch != null)
      if (firstSearch.rect.distanceSquaredTo(p) > lastSearch.rect.distanceSquaredTo(p)) {
        firstSearch = x.rt;
        lastSearch  = x.lb;
      }

    Point2D firstSearchNearestPoint = nearest(firstSearch, p, nearestDist);
    if (firstSearchNearestPoint != null)
      if (firstSearchNearestPoint.distanceSquaredTo(p) < nearestDist) {
        nearestDist = firstSearchNearestPoint.distanceSquaredTo(p);
        nearestPoint = firstSearchNearestPoint;
      }

    Point2D lastSearchNearestPoint  = nearest(lastSearch, p, nearestDist);
    if (lastSearchNearestPoint != null)
      if (lastSearchNearestPoint.distanceSquaredTo(p) < nearestDist) {
        nearestDist = lastSearchNearestPoint.distanceSquaredTo(p);
        nearestPoint = lastSearchNearestPoint;
      }
    return nearestPoint;
  }

  // public static void main(String[] args) {
  //   KdTree kdtree = new KdTree();
  //
  //   kdtree.insert(new Point2D(0.75, 0.85));
  //   StdOut.println(kdtree.size());
  //   kdtree.insert(new Point2D(0.125, 0.375));
  //   StdOut.println(kdtree.size());
  //   kdtree.insert(new Point2D(0.25, 0.5));
  //   StdOut.println(kdtree.size());
  //   kdtree.insert(new Point2D(0.125, 0.5));
  //   StdOut.println(kdtree.size());
  //   kdtree.insert(new Point2D(0.875, 0.125));
  //   StdOut.println(kdtree.size());
  //   kdtree.insert(new Point2D(0.25, 0.625));
  //   StdOut.println(kdtree.size());
  //
  //   StdOut.println(kdtree.contains(new Point2D(0.875, 0.125)));
  //   StdOut.println(kdtree.contains(new Point2D(0.875, 0.875)));
  //
  //   kdtree.draw();
  //   StdDraw.show();
  // }

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
