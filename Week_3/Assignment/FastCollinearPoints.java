import java.util.LinkedList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
  private final LinkedList<LineSegment> lineSegmentList = new LinkedList<LineSegment>();

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

  private void findSegments(Point[] points) {
    int nPoints = points.length;
    // Arrays.sort(points);
    Point[] copy = new Point[nPoints];
    for (int i = 0; i < nPoints; i++)
      copy[i] = points[i];

    for (int i = 0; i < nPoints; i++) {
      Point origin  = points[i];
      Arrays.sort(copy, origin.slopeOrder());
      int j = 0;
      while (j < nPoints - 2) {
        if (origin.slopeOrder().compare(copy[j], copy[j+1]) != 0) {
          j++;
          continue;
        }

        if (origin.slopeOrder().compare(copy[j+1], copy[j+2]) != 0) {
          j++;
          continue;
        }

        if (j + 3 >= nPoints) {
          Arrays.sort(copy, j, j+3);
          if (origin.compareTo(copy[j]) < 0) lineSegmentList.add(new LineSegment(origin, copy[j+2]));
          // else if (origin.compareTo(copy[j+2]) > 0) lineSegmentList.add(new LineSegment(copy[j], origin));
          // else lineSegmentList.add(new LineSegment(copy[j], copy[j+2]));
          j += 3;
        }
        else {
          int k = j + 3;
          while (k < nPoints) {
            if (origin.slopeOrder().compare(copy[j], copy[k]) != 0) break;
            k++;
          }
          Arrays.sort(copy, j, k);
          if (origin.compareTo(copy[j]) < 0) lineSegmentList.add(new LineSegment(origin, copy[k-1]));
          // else if (origin.compareTo(copy[k-1]) > 0) lineSegmentList.add(new LineSegment(copy[j], origin));
          // else lineSegmentList.add(new LineSegment(copy[j], copy[k-1]));
          j = k;
        }
      }
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
  }
}
