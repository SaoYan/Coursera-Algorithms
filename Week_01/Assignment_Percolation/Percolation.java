/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       4/23/2018
 *  Last updated:  4/23/2018
 *
 *  Compilation:   javac -d . Percolation.java
 *  Execution:     java Percolation size
 *  Dependencies:  edu.princeton.cs.algs4.WeightedQuickUnionUF
 *                 edu.princeton.cs.algs4.StdRandom
 *
 *  Model a percolation system: implement the Percolation data type.
 *
 *  % java Percolation 200
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
  private final int n;                       // number of rows/cols
  private int numOpen;                       // number of open sites
  private boolean[] isOpen;                  // store status of sites: open or not
  private final WeightedQuickUnionUF uf;     // Union Find data type (with top & bottom virtual sites)
  private final WeightedQuickUnionUF ufTop;  // Union Find data type (with only top virtual site); used to validate full sites

  // constructor: create n-by-n grid, with all sites blocked
  public Percolation(int size) {
    if (size <= 0)
      throw new IllegalArgumentException("size of the system should be positive integer!");
    n = size;
    numOpen = 0;
    isOpen = new boolean[n*n+2]; // default initialization: false
    uf = new WeightedQuickUnionUF(n*n+2);
    ufTop = new WeightedQuickUnionUF(n*n+1);
  }

  private void validate(int k) {
    if (k < 1 || k > n) {
      throw new IllegalArgumentException("row and column indices should be integers between 1 and " + n + "; got: " + k);
    }
  }

  private int xyToIdx(int row, int col) {
    validate(row);
    validate(col);
    return (row - 1) * n + col;
  }

  public void open(int row, int col) {
    int idx = xyToIdx(row, col);
    if (isOpen[idx]) return;
    isOpen[idx] = true;
    numOpen += 1;
    // connect to virtual top
    if (row == 1) {
      uf.union(0, idx);
      ufTop.union(0, idx);
    }
    // connect to virtual bottom
    if (row == n) {
      uf.union(idx, n*n+1);
    }
    //  up
    if (row > 1) {
      int idxUp = xyToIdx(row-1, col);
      if (isOpen(row-1, col)) {
        uf.union(idx, idxUp);
        ufTop.union(idx, idxUp);
      }
    }
    // down
    if (row < n) {
      int idxDown = xyToIdx(row+1, col);
      if (isOpen(row+1, col)) {
        uf.union(idx, idxDown);
        ufTop.union(idx, idxDown);
      }
    }
    // left
    if (col > 1) {
      int idxLeft = xyToIdx(row, col-1);
      if (isOpen(row, col-1)) {
        uf.union(idx, idxLeft);
        ufTop.union(idx, idxLeft);
      }
    }
    // right
    if (col < n) {
      int idxRight = xyToIdx(row, col+1);
      if (isOpen(row, col+1)) {
        uf.union(idx, idxRight);
        ufTop.union(idx, idxRight);
      }
    }
  }

  public boolean isOpen(int row, int col) {
    int idx = xyToIdx(row, col);
    return isOpen[idx];
  }

  public boolean isFull(int row, int col) {
    int idx = xyToIdx(row, col);
    return ufTop.connected(0, idx);
  }

  public int numberOfOpenSites() {
    return numOpen;
  }

  public boolean percolates() {
    return uf.connected(0, n*n+1);
  }

  public static void main(String[] args) {
    int size = Integer.parseInt(args[0]);
    Percolation client = new Percolation(size);
    while (!client.percolates()) {
      int row = StdRandom.uniform(1, size+1);
      int col = StdRandom.uniform(1, size+1);
      if (!client.isOpen(row, col))
        client.open(row, col);
    }
    double p = (double) client.numberOfOpenSites() / (double) (size*size);
    System.out.printf("vavancy probability p = %f\n", p);
  }
}
