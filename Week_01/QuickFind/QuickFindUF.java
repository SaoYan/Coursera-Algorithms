/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       4/3/2018
 *  Last updated:  4/23/2018
 *
 *  Compilation:   javac -d . QuickFindUF.java
 *  Execution:     java yiqi.QuickFindUF
 *
 *  Implement Quick Find algorithm
 *
 *  % java yiqi.QuickFindUF
 *  true
 *  false
 *
 *----------------------------------------------------------------*/

package yiqi;

public class QuickFindUF {
  private int[] id; // id[i] is parent of i

  // initialization
  public QuickFindUF(int N) {
    id = new int[N];
    for (int i = 0; i < N; i++)
      id[i] = i;
  }

  // validate
  private void validate(int k) {
    if (k < 0 || k >= id.length) {
      throw new IllegalArgumentException("index is out of range! expected: between 0 and " + (id.length-1) + " got: " + k);
    }
  }

  // quick find
  public boolean isConnected(int p, int q) {
    validate(p);
    validate(q);
    return id[q] == id[p];
  }

  // union command
  public void union(int p, int q) {
    validate(p);
    validate(q);
    int pid = id[p];
    int qid = id[q];
    for (int i =0; i < id.length; i++) {
      if (id[i] == pid)
        id[i] = qid;
    }
  }

  public static void main(String[] args) {
    QuickFindUF client = new QuickFindUF(100);
    client.union(0, 10);
    client.union(10, 20);
    System.out.println(client.isConnected(0, 20));
    System.out.println(client.isConnected(0, 30));
  }
}
