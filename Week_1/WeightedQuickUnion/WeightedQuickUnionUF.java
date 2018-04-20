package yiqi;

public class WeihghtedQuickUnionUF {
  private int[] id;
  private int[] size;

  public QuickUnionUF(int N) {
    id = new int[N];
    for (int i =0; i < N; i++) {
      id[i] = i;
      size[i] = 1;
    }
  }

  private int findRoot(int p) {
    while (p != id[p])
      p = id[p];
    return p;
  }

  public boolean isConnected(int p, int q) {
    boolean flag;
    try {
      flag = (findRoot(q) == findRoot(p));
    }
    catch (ArrayIndexOutOfBoundsException e) {
      String error = String.format("index should be from 0 to %d\n", id.length-1);
      throw new RuntimeException(error);
    }
    return flag;
  }

  public void union(int p, int q) {
    int rootp, rootq;
    try {
      rootp = findRoot(p);
      rootq = findRoot(q);
    }
    catch (ArrayIndexOutOfBoundsException e) {
      String error = String.format("index should be from 0 to %d\n", id.length-1);
      throw new RuntimeException(error);
    }
    if (size[rootp] < size[rootq]) {
      id[rootp] = id[rootq];
      size[rootq] += size[rootp];
    }
    else {
      id[rootq] = id[rootp];
      size[rootp] += size[rootq];
    }
  }
}
