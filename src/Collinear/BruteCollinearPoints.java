package Collinear;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            for (int j = i+1; j < points.length; j++) {
                if (points[j] == null) throw new IllegalArgumentException();
                if (points[i].slopeTo(points[j]) == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                for (int k = j+1; k < points.length; k++) {
                    for (int l = k+1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k]) && points[k].slopeTo(points[l]) == points[j].slopeTo(points[k])) {
                            Point[] minMaxPoints = findMinMaxPoints(points[i], points[j], points[k], points[l]);
                            lineSegments.add(new LineSegment(minMaxPoints[0], minMaxPoints[1]));
                        }
                    }
                }
            }
        }
    }

    private Point[] findMinMaxPoints(Point p, Point q, Point r, Point s) {
        Point[] points = new Point[]{p, q, r, s};
        Arrays.sort(points);
        return new Point[]{points[0], points[3]};
    }

    public int numberOfSegments() { return lineSegments.size(); }

    public LineSegment[] segments() {
        LineSegment[] ret = new LineSegment[numberOfSegments()];
        for (int i = 0; i < numberOfSegments(); i++) {
            ret[i] = lineSegments.get(i);
        }
        return ret;
    }
}
