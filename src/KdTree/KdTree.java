import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.util.LinkedList;

public class KdTree {
    private Node root;
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    private static class Node {
        private Point2D p;
        private Node left, right;
        private RectHV rect;
        private int count;

        private Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }

    public boolean isEmpty() { return root == null; }

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        if (n == null) return 0;
        return n.count;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        root = insert(root, VERTICAL, p, new RectHV(0, 0, 1, 1));
    }

    private Node insert(Node n, boolean orientation, Point2D p, RectHV rect) {
        if (n == null) {
            Node newNode = new Node(p, rect);
            newNode.count = 1;
            return newNode;
        }

        double comp;
        if (orientation == VERTICAL) comp = p.x() - n.p.x();
        else comp = p.y() - n.p.y();

        if (comp < 0) {
            if (orientation == VERTICAL) n.left = insert(n.left, HORIZONTAL, p, new RectHV(n.rect.xmin(), n.rect.ymin(), n.p.x(), n.rect.ymax()));
            else n.left = insert(n.left, VERTICAL, p, new RectHV(n.rect.xmin(), n.rect.ymin(), n.rect.xmax(), n.p.y()));
        }
        else {
            if (!(p.x() == n.p.x() && p.y() == n.p.y())) {
                if (orientation == VERTICAL) n.right = insert(n.right, HORIZONTAL, p, new RectHV(n.p.x(), n.rect.ymin(), n.rect.xmax(), n.rect.ymax()));
                else n.right = insert(n.right, VERTICAL, p, new RectHV(n.rect.xmin(), n.p.y(), n.rect.xmax(), n.rect.ymax()));
            }
        }

        n.count = 1 + size(n.left) + size(n.right);
        return n;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        Node n = root;
        boolean orientation = VERTICAL;
        while(n != null) {
            double comp;
            if (orientation == VERTICAL) comp = p.x() - n.p.x();
            else comp = p.y() - n.p.y();

            if (comp < 0) n = n.left;
            else {
                if (p.x() == n.p.x() && p.y() == n.p.y()) return true;
                n = n.right;
            }

            orientation = !orientation;
        }
        return false;
    }

    public void draw() {
        StdDraw.clear();
        draw(root, VERTICAL, 0, 1);
        StdDraw.show();
    }

    private void draw(Node n, boolean orientation, double min, double max) {
        if (n == null) return;

        if (orientation == VERTICAL) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(n.p.x(), min, n.p.x(), max);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledCircle(n.p.x(), n.p.y(), 0.01);
            draw(n.left, HORIZONTAL, 0, n.p.x());
            draw(n.right, HORIZONTAL, n.p.x(), 1);
        }
        else {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(min, n.p.y(), max, n.p.y());
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledCircle(n.p.x(), n.p.y(), 0.01);
            draw(n.left, VERTICAL, 0, n.p.y());
            draw(n.right, VERTICAL, n.p.y(), 1);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();

        LinkedList<Point2D> inRange = new LinkedList<Point2D>();
        range(root, VERTICAL, rect, inRange);
        return inRange;
    }

    private void range(Node n, boolean orientation, RectHV rect, LinkedList list) {
        if (n.p.x() >= rect.xmin() && n.p.x() <= rect.xmax() && n.p.y() >= rect.ymin() && n.p.y() <= rect.ymax()) {
            list.add(n.p);
        }
        if (n.left != null && rect.intersects(n.left.rect)) range(n.left, !orientation, rect, list);
        if (n.right != null && rect.intersects(n.right.rect)) range(n.right, !orientation, rect, list);
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        if (isEmpty()) return null;
        else {
            return nearest(root, p, null);
        }
    }

    private Point2D nearest(Node n, Point2D p, Point2D min) {
        if (n != null) {
            if (min == null) min = n.p;
            if (min.distanceSquaredTo(p) >= n.rect.distanceSquaredTo(p)) {
                if (n.p.distanceSquaredTo(p) < min.distanceSquaredTo(p)) {
                    min = n.p;
                }
                if (n.right != null && n.right.rect.contains(p)) {
                    min = nearest(n.right, p, min);
                    min = nearest(n.left, p, min);
                }
                else {
                    min = nearest(n.left, p, min);
                    min = nearest(n.right, p, min);
                }
            }
        }
        return min;
    }

//    public static void main(String[] args) {
//        KdTree tree = new KdTree();
//        tree.insert(new Point2D(0.7, 0.2));
//        tree.insert(new Point2D(0.5, 0.4));
//        tree.insert(new Point2D(0.2, 0.3));
//        tree.insert(new Point2D(0.4, 0.7));
//        tree.insert(new Point2D(0.9, 0.6));
//        System.out.println(tree.nearest(new Point2D(0.4, 0.048)));
//
//    }
}
