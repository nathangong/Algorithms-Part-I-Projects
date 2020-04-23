package KdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> points;

    public PointSET() {
        points = new TreeSet<Point2D>();
    }

    public boolean isEmpty() { return points.isEmpty(); }

    public int size() { return points.size(); }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        points.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return points.contains(p);
    }

    public void draw() {
        StdDraw.clear();
        for (Point2D p : points) {
            p.draw();
        }
        StdDraw.show();
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();

        LinkedList<Point2D> inRange = new LinkedList<Point2D>();
        for (Point2D p : points) {
            if (rect.contains(p)) {
                inRange.add(p);
            }
        }
        return inRange;
    }

    public Point2D nearest(Point2D point) {
        if (point == null) throw new IllegalArgumentException();

        double minDistance = Double.MAX_VALUE;
        Point2D nearest = null;
        for (Point2D p : points) {
            double dist = p.distanceSquaredTo(point);
            if (dist < minDistance) {
                minDistance = dist;
                nearest = p;
            }
        }
        return nearest;
    }
}
