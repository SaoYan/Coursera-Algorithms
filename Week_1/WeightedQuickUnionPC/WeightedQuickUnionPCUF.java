/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       4/3/2018
 *  Last updated:  4/23/2018
 *
 *  Compilation:   javac -d . WeightedQuickUnionPCUF.java
 *  Execution:     java yiqi.WeightedQuickUnionPCUF
 *
 *  Implement Weighted Quick Union algorithm (with path compression)
 *
 *  % java yiqi.WeightedQuickUnionPCUF
 *  true
 *  false
 *
 *----------------------------------------------------------------*/

package yiqi;

public class WeightedQuickUnionPCUF {
  private int[] id;
  private int[] size;

  public WeightedQuickUnionPCUF(int N) {
    id = new int[N];
    size = new int[N];
    for (int i =0; i < N; i++) {
      id[i] = i;
      size[i] = 1;
    }
  }

  // validate
  private void validate(int k) {
    if (k < 0 || k >= id.length) {
      throw new IllegalArgumentException("index is out of range! expected: between 0 and " + (id.length-1) + " got: " + k);
    }
  }

  private int findRoot(int p) {
    validate(p);
    while (p != id[p]) {
      id[p] = id[id[p]];
      p = id[p];
    }
    return p;
  }

  public boolean isConnected(int p, int q) {
    return findRoot(q) == findRoot(p);
  }

  public void union(int p, int q) {
    int rootp = findRoot(p);
    int rootq = findRoot(q);
    if (size[rootp] < size[rootq]) {
      id[rootp] = id[rootq];
      size[rootq] += size[rootp];
    }
    else {
      id[rootq] = id[rootp];
      size[rootp] += size[rootq];
    }
  }

  public static void main(String[] args) {
    WeightedQuickUnionPCUF client = new WeightedQuickUnionPCUF(100);
    client.union(0, 10);
    client.union(10, 20);
    System.out.println(client.isConnected(0, 20));
    System.out.println(client.isConnected(0, 30));
  }
}
