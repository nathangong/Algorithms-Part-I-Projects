package UnionFind;

public class QuickFindUF {
    int[] nodes;
    QuickFindUF(int n) {
        nodes = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = i;
        }
    }

    void union(int p, int q) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == nodes[q]) {
                nodes[i] = nodes[p];
            }
        }
    }

    boolean connected(int p, int q) {
        return nodes[p] == nodes[q];
    }
}
