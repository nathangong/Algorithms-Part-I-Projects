package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int[][] grid;
    private boolean[][] open;
    private WeightedQuickUnionUF uf;
    private int n;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.n = n;
        grid = new int[n][n];
        open = new boolean[n][n];
        int curr = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = curr;
                open[i][j] = false;
                curr++;
            }
        }
        uf = new WeightedQuickUnionUF(n*n+2);
    }

    public void open(int row, int col) {
        if (!(row >= 1 && row <= n && col >= 1 && col <= n)) throw new IllegalArgumentException();
        row--;
        col--;
        if (open[row][col]) return;
        open[row][col] = true;
        if (row == 0) {
            uf.union(grid[row][col], 0);
        }
        else if (row == n-1) {
            uf.union(grid[row][col], n*n+1);
        }

        if (col+1 < open.length && open[row][col+1]) {
            uf.union(grid[row][col], grid[row][col+1]);
        }
        if (col-1 >= 0 && open[row][col-1]) {
            uf.union(grid[row][col], grid[row][col-1]);
        }
        if (row+1 < open.length && open[row+1][col]) {
            uf.union(grid[row][col], grid[row+1][col]);
        }
        if (row-1 >= 0 && open[row-1][col]) {
            uf.union(grid[row][col], grid[row-1][col]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == n-1) System.out.println(open[i][j]);
                else System.out.print(open[i][j] + " " + (open[i][j] ? " " : ""));
            }
        }
        System.out.println("\n");
    }

    public boolean isOpen(int row, int col) {
        if (!(row >= 1 && row <= n && col >= 1 && col <= n)) throw new IllegalArgumentException();
        return open[row-1][col-1];
    }

    public boolean isFull(int row, int col) {
        if (!(row >= 1 && row <= n && col >= 1 && col <= n)) throw new IllegalArgumentException();
        if (!isOpen(row, col)) return false;
        return uf.find(0) == uf.find(grid[row-1][col-1]);
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (boolean[] a : open) {
            for (boolean b: a) {
                if (b) count++;
            }
        }
        return count;
    }

    public boolean percolates() {
        if (uf.find(0) == uf.find(n*n+1)) return true;
        return false;
    }
}
