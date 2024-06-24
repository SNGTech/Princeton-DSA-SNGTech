/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96d;
    private double[] trialData;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n and trials must be greater than 0!");

        trialData = new double[trials];

        // Monte-Carlo Simulation
        for (int i = 0; i < trials; i++) {
            // StdOut.println("Starting Trial " + i);
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int randRow = StdRandom.uniformInt(1, n + 1);
                int randCol = StdRandom.uniformInt(1, n + 1);
                if (percolation.isOpen(randRow, randCol))
                    continue;
                percolation.open(randRow, randCol);
            }

            trialData[i] = ((double) percolation.numberOfOpenSites()) / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trialData);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialData);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trialData.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trialData.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, t);

        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println(
                "95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi()
                        + "]");
    }
}
