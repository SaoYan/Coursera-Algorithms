/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       4/3/2018
 *  Last updated:  4/23/2018
 *
 *  Compilation:   javac -d . QuickUnionUF.java
 *  Execution:     java yiqi.QuickUnionUF
 *
 *  Implement Quick Union algorithm
 *
 *  % java yiqi.QuickUnionUF
 *  true
 *  false
 *
 *----------------------------------------------------------------*/

package yiqi;

public class QuickUnionUF {
  private int[] id;

  public QuickUnionUF(int N) {
    id = new int[N];
    for (int i =0; i < N; i++) {
      id[i] = i;
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
    while (p != id[p])
      p = id[p];
    return p;
  }

  public boolean isConnected(int p, int q) {
    return findRoot(q) == findRoot(p);
  }

  public void union(int p, int q) {
    int rootp = findRoot(p);
    int rootq = findRoot(q);
    id[rootp] = rootq;
  }

  public static void main(String[] args) {
    QuickUnionUF client = new QuickUnionUF(100);
    client.union(0, 10);
    client.union(10, 20);
    System.out.println(client.isConnected(0, 20));
    System.out.println(client.isConnected(0, 30));
  }
}
