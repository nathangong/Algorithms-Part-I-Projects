package UnionFind;

public class QuickUnionUF {
    int[] nodes;
    QuickUnionUF(int n) {
        nodes = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = i;
        }
    }

    void union(int p, int q) {
        nodes[root(p)] = nodes[root(q)];
    }

    int root(int index) {
        while (nodes[index] != index) index = nodes[index];
        return index;
    }

    boolean connected(int p, int q) {
        return root(p) == root(q);
    }
}
