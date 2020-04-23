package BSTs;

public class RedBlackBST<Key extends Comparable<Key>, Value> extends BST {
    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int count;
        private boolean color;

        public Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    private boolean isRed(Node n) {
        if (n == null) return false;
        return n.color == RED;
    }

    private Node rotateLeft(Node t) {
        Node b = t.right;
        t.right = b.left;
        b.left = t;
        b.color = t.color;
        t.color = RED;
        return b;
    }

    private Node rotateRight(Node t) {
        Node b = t.left;
        t.left = b.right;
        b.right = t;
        b.color = t.color;
        t.color = RED;
        return b;
    }

    private void flipColors(Node n) {
        n.color = RED;
        n.left.color = BLACK;
        n.right.color = BLACK;
    }

    private Node put(Node currNode, Key key, Value val) {
        if (currNode == null) return new Node(key, val, RED);
        int comp = key.compareTo(currNode.key);
        if (comp < 0) currNode.left = put(currNode.left, key, val);
        else if (comp > 0) currNode.right = put(currNode.right, key, val);
        else currNode.val = val;

        if (isRed(currNode.right) && !isRed(currNode.left)) currNode = rotateLeft(currNode);
        if (isRed(currNode.left) && isRed(currNode.left.left)) currNode = rotateRight(currNode);
        if (isRed(currNode.left) && isRed(currNode.right)) flipColors(currNode);

        return currNode;
    }

    @Override
    public void delete(Comparable key) {
        throw new UnsupportedOperationException();
    }
}
