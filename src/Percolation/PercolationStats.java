package Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] trials;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.trials = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int count = 0;
            while (!p.percolates()) {
                int row = StdRandom.uniform(n)+1;
                int col = StdRandom.uniform(n)+1;
                while (p.isOpen(row, col)) {
                    row = StdRandom.uniform(n)+1;
                    col = StdRandom.uniform(n)+1;
                }
                p.open(row, col);
                count++;
            }
            this.trials[i] = count/((double)n*n);
        }
    }

    public double mean() {
        return StdStats.mean(trials);
    }

    public double stddev() {
        return StdStats.stddev(trials);
    }

    public double confidenceLo() {
        return mean() - 1.96*stddev()/Math.sqrt(trials.length);
    }

    public double confidenceHi() {
        return mean() + 1.96*stddev()/Math.sqrt(trials.length);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, t);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println("[" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
