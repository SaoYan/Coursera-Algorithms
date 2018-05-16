/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       4/23/2018
 *  Last updated:  4/23/2018
 *
 *  Compilation:   javac -d . Percolation.java PercolationStats.java
 *  Execution:     java PercolationStats size numTrials
 *  Dependencies:  Percolation.java
 *                 edu.princeton.cs.algs4.StdStats
 *
 *  Monte Carlo simulation:
 *  perform a series of computational experiments to estimate vavancy probability
 *
 *  % java Percolation 200 30
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
  private static final double CONSTANT = 1.96;
  private double meanVal;      // mean value
  private double stddevVal;    // standard deviation value
  private double loVal;        // low endpoint of 95% confidence interval
  private double hiVal;        // low endpoint of 95% confidence interval

  public PercolationStats(int size, int trials) {
    if (size <= 0 || trials <= 0)
      throw new IllegalArgumentException("both size of the system and the number of trials should be positive integers!");
    if (size == 1) {
      meanVal = 1;
      stddevVal = Double.NaN;
      loVal = Double.NaN;
      hiVal = Double.NaN;
    }
    else {
      double[] p = new double[trials];
      // perform experiments
      for (int i = 0; i < trials; i++) {
        Percolation percolation = new Percolation(size);
        while (!percolation.percolates()) {
          int row = StdRandom.uniform(1, size+1);
          int col = StdRandom.uniform(1, size+1);
          if (!percolation.isOpen(row, col))
            percolation.open(row, col);
        }
        p[i] = (double) percolation.numberOfOpenSites() / (double) (size*size);
      }
      meanVal = StdStats.mean(p);
      stddevVal = StdStats.stddev(p);
      loVal = meanVal - CONSTANT * stddevVal / Math.sqrt(trials);
      hiVal = meanVal + CONSTANT * stddevVal / Math.sqrt(trials);
    }

  }

  public double mean() {
    return meanVal;
  }

  public double stddev() {
    return stddevVal;
  }

  public double confidenceLo() {
    return loVal;
  }

  public double confidenceHi() {
    return hiVal;
  }

  public static void main(String[] args) {
    int size = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats client = new PercolationStats(size, trials);
    double x = client.mean();
    double s = client.stddev();
    double lo = client.confidenceLo();
    double hi = client.confidenceHi();
    System.out.printf("mean                    = %f\n", x);
    System.out.printf("stddev                  = %f\n", s);
    System.out.printf("95 confidence interval = [%f, %f]\n", lo, hi);
  }
}
