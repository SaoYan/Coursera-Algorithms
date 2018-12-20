/*
import java.util.LinkedList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
  private LinkedList<LineSegment> lineSegmentList = new LinkedList<LineSegment>();

   public FastCollinearPoints(Point[] points) {
     if (points == null) throw new IllegalArgumentException("Error: the argument to the constructor is null!");
   }

   // the number of line segments
   public int numberOfSegments() { return lineSegmentList.size(); }

   // the line segments
   public LinkedList<LineSegment> segments() { return lineSegmentList; }

   private void findSegments(Point[] points) {
     int nPoints = points.length;
     for (int i = 0; i < nPoints; i++) {
       Point origin  = points[i];
       Arrays.sort(points, origin.SlopeOrder());
       for (int )
     }
}
*/

import java.util.LinkedList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
  private LinkedList<LineSegment> lineSegmentList = new LinkedList<LineSegment>();

  // finds all line segments containing 4 points
  public FastCollinearPoints(Point[] points) {
    if (points == null) throw new IllegalArgumentException("Error: the argument to the constructor is null!");
    for (int i = 0; i < points.length; i++) {
      if (points[i] == null)
        throw new IllegalArgumentException("Error: null point within the array!");
        for (int j = i + 1; j < points.length; j++)
          if (points[i].compareTo(points[j]) == 0)
            throw new IllegalArgumentException("Array cannot contain same points!");
    }
    findSegments(points);
  }

  // the number of line segments
  public int numberOfSegments() { return lineSegmentList.size(); }

  // the line segments
  public LineSegment[] segments() {
    LineSegment[] segs = new LineSegment[numberOfSegments()];
    int i = 0;
    for (LineSegment segment: lineSegmentList)
      segs[i++] = segment;
    return segs;
  }

  private boolean isSegment(Point p1, Point p2, Point p3, Point p4) {
    if (p1.slopeTo(p2) == p1.slopeTo(p3) && p1.slopeTo(p2) == p1.slopeTo(p4))
      return true;
    return false;
  }

  private LineSegment findEndPoints(Point p1, Point p2, Point p3, Point p4) {
    Point[] pointArray = {p1, p2, p3, p4};
    Arrays.sort(pointArray);
    return new LineSegment(pointArray[0], pointArray[3]);

  }

  private void findSegments(Point[] points) {
    int nPoints = points.length;
    for (int i = 0; i < nPoints - 3; i++)
      for (int j = i+1; j < nPoints - 2; j++)
        for (int k = j+1; k < nPoints - 1; k++)
          for (int s = k+1; s < nPoints; s++)
            if (isSegment(points[i], points[j], points[k], points[s])) {
              lineSegmentList.add(findEndPoints(points[i], points[j], points[k], points[s]));
            }
  }

  public static void main(String[] args) {
    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
  }
}
