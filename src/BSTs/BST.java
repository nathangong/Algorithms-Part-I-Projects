package BSTs;

import edu.princeton.cs.algs4.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int count;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        if (n == null) return 0;
        return n.count;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public Node put(Node currNode, Key key, Value val) {
        if (currNode == null) return new Node(key, val);

        int comp = key.compareTo(currNode.key);
        if (comp < 0) currNode.left = put(currNode.left, key, val);
        else if (comp > 0) currNode.right = put(currNode.right, key, val);
        else currNode.val = val;
        currNode.count = 1 + size(currNode.left) + size(currNode.right);
        return currNode;
    }

    public Value get(Key key) {
        Node currNode = root;
        while(currNode != null) {
            int comp = key.compareTo(currNode.key);
            if (comp < 0) currNode = currNode.left;
            else if (comp > 0) currNode = currNode.right;
            else return currNode.val;
        }
        return null;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node currNode) {
        while(currNode.left != null) {
            currNode = currNode.left;
        }
        return currNode;
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node currNode) {
        while(currNode.right != null) {
            currNode = currNode.right;
        }
        return currNode;
    }

    public Key floor(Key key) {
        Node currNode = root;
        Node retNode = null;
        while(currNode != null) {
            int comp = key.compareTo(currNode.key);
            if (comp == 0) return key;
            if (comp < 0) {
                currNode = currNode.left;
            }
            else {
                if (retNode == null || currNode.key.compareTo(retNode.key) > 0) {
                    retNode = currNode;
                }
                currNode = currNode.right;
            }
        }
        if (retNode == null) return null;
        return retNode.key;
    }

    public Key ceil(Key key) {
        Node currNode = root;
        Node retNode = null;
        while(currNode != null) {
            int comp = key.compareTo(currNode.key);
            if (comp == 0) return key;
            if (comp > 0) {
                currNode = currNode.right;
            }
            else {
                if (retNode == null || currNode.key.compareTo(retNode.key) < 0) {
                    retNode = currNode;
                }
                currNode = currNode.left;
            }
        }
        if (retNode == null) return null;
        return retNode.key;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node n) {
        if (n == null) return 0;
        int comp = key.compareTo(n.key);
        if (comp < 0) return rank(key, n.left);
        else if (comp > 0) return size(n.left) + rank(key, n.right) + 1;
        else return size(n.left);
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node currNode, Queue<Key> q) {
        if (currNode == null) return;
        inorder(currNode.left, q);
        q.enqueue(currNode.key);
        inorder(currNode.right, q);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node currNode) {
        if (currNode.left == null) {
            return currNode.right;
        }
        currNode.left = deleteMin(currNode.left);
        currNode.count = 1 + size(currNode.left) + size(currNode.right);
        return currNode;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    public Node delete(Node currNode, Key key) {
        if (currNode == null) return null;

        int comp = key.compareTo(currNode.key);
        if (comp < 0) currNode.left = delete(currNode.left, key);
        else if (comp > 0) currNode.right = delete(currNode.right, key);
        else {
            if (currNode.left == null) return currNode.right;
            if (currNode.right == null) return currNode.left;

            Node oldNode = currNode;
            currNode = min(oldNode.right);
            currNode.right = deleteMin(oldNode.right);
            currNode.left = oldNode.left;
        }
        currNode.count = size(currNode.left) + size(currNode.right) + 1;
        return currNode;
    }
}
