package Collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> lineSegments = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
        }
        List<Point> usedMinPoints = new ArrayList<Point>();
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length; i++) {
            Point[] sortedPoints = Arrays.copyOf(pointsCopy, pointsCopy.length);
            Arrays.sort(sortedPoints, pointsCopy[i].slopeOrder());
            for (int j = 1; j < sortedPoints.length; j++) {
                if (pointsCopy[i].slopeTo(sortedPoints[j]) == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
                int count = 2;
                while (j+1 < sortedPoints.length && pointsCopy[i].slopeTo(sortedPoints[j]) == pointsCopy[i].slopeTo(sortedPoints[j+1])) {
                    count++;
                    j++;
                }
                if (count >= 4) {
                    Point minPoint = sortedPoints[j-(count-2)];
                    if (minPoint.compareTo(pointsCopy[i]) > 0) minPoint = pointsCopy[i];
                    Point maxPoint = sortedPoints[j];
                    if (maxPoint.compareTo(pointsCopy[i]) < 0) maxPoint = pointsCopy[i];
                    if (!usedMinPoints.contains(minPoint) || minPoint == pointsCopy[i]) {
                        lineSegments.add(new LineSegment(minPoint, maxPoint));
                        usedMinPoints.add(minPoint);
                    }
                }
            }
        }
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
