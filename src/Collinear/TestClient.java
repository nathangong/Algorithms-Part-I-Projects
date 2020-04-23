package Collinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class TestClient {
    public static void main(String[] args) {

        // read the n points from a file
//        In in = new In("C:\\Users\\Nathan.Gong22\\OneDrive - Bellarmine College Preparatory\\Programming and Web Dev\\Algorithms and Data Structures Class\\src\\Collinear\\input");
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }
        Point[] points = new Point[7];
        points[0] = new Point(0, 1);
        points[1] = new Point(2, 3);
        points[2] = new Point(4, 5);
        points[3] = new Point(8, 5);
        points[4] = new Point(10, 5);
        points[5] = new Point(3, 5);
        points[6] = new Point(6, 5);


        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);
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
