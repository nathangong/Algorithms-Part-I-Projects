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

    public static void main(String[] args) {

        QuickFindUF uf = new QuickFindUF(10);

        uf.union(1, 2);
        uf.union(5, 6);
        uf.union(3, 8);
        uf.union(3, 4);
        uf.union(4, 9);

        System.out.println(uf.connected(8, 4));

    }
}
