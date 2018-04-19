package yiqi;

public class QuickUnionUF
{
  private int[] id;

  public QuickUnionUF(int N)
  {
    id = new int[N];
    for (int i =0; i < N; i++)
    {
      id[i] = i;
    }
  }

  private int findRoot(int p)
  {
    // int root;
    // int parent = id[p];
    // if (id[parent] != parent)
    //   root = findRoot(parent);
    // else
    //   root = parent;
    // return root;

    while (p != id[p])
      p = id[p];
    return p;
  }

  public boolean isConnected(int p, int q)
  {
    boolean flag;
    try{
      flag = (findRoot(q) == findRoot(p));
    }
    catch (ArrayIndexOutOfBoundsException e){
      String error = String.format("index should be from 0 to %d\n", id.length-1);
      throw new RuntimeException(error);
    }
    return flag;
  }

  public void union(int p, int q)
  {
    int rootp, rootq;
    try{
      rootp = findRoot(p);
      rootq = findRoot(q);
    }
    catch (ArrayIndexOutOfBoundsException e){
      String error = String.format("index should be from 0 to %d\n", id.length-1);
      throw new RuntimeException(error);
    }
    id[rootp] = rootq;
  }
}
