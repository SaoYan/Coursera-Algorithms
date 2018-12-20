import java.util.LinkedList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
  private LinkedList<LineSegment> lineSegmentList = new LinkedList<LineSegment>();

   public FastCollinearPoints(Point[] points) {
     if (points == null) throw new IllegalArgumentException("Error: the argument to the constructor is null!");
     for (int i = 0; i < points.length; i++) {
       if (points[i] == null)
         throw new IllegalArgumentException("Error: null within the input argument!");
         for (int j = i + 1; j < points.length; j++) {
           if (points[j] == null)
             throw new IllegalArgumentException("Error: null within the input argument!");
           if (points[i].compareTo(points[j]) == 0)
             throw new IllegalArgumentException("Array cannot contain same points!");
         }
     }
   }

   // the number of line segments
   public int numberOfSegments() { return lineSegmentList.size(); }

   // the line segments
   public LineSegment[] segments() {}

   private void findSegments(Point[] points) {
     int nPoints = points.length;
     for (int i = 0; i < nPoints; i++) {
       Point origin  = points[i];
       Arrays.sort(points, origin.SlopeOrder());
       for (int )
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
      FastCollinearPoints collinear = new FastCollinearPoints(points);
      for (LineSegment segment : collinear.segments()) {
          StdOut.println(segment);
          segment.draw();
      }
      StdDraw.show();
    }
}
