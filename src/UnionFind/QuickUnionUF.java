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

    public static void main(String[] args) {

        QuickUnionUF uf = new QuickUnionUF(10);

        uf.union(1, 2);
        uf.union(5, 6);
        uf.union(3, 8);
        uf.union(3, 4);
        uf.union(4, 9);

        System.out.println(uf.connected(8, 4));

    }
}
