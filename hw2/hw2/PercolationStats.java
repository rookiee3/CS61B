package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int T;
    private double[] openSiteFraction;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("invalid");
        }

        this.T = T;
        openSiteFraction = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int x, y;
                do {
                    x = StdRandom.uniform(N);
                    y = StdRandom.uniform(N);
                } while (percolation.isOpen(x, y));
                percolation.open(x, y);
            }
            openSiteFraction[i] = (double) percolation.numberOfOpenSites() / (N * N);
        }
    }
    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(openSiteFraction);
    }
    public double stddev() {
        // sample standard deviation of percolation threshold
        return StdStats.stddev(openSiteFraction);
    }
    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }
    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }
}
