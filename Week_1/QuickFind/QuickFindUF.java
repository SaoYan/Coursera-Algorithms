package yiqi;

public class QuickFindUF
{
  private int[] id;

  // initialization
  public QuickFindUF(int N)
  {
    id = new int[N];
    for (int i = 0; i < N; i++)
      id[i] = i;
  }

  // quick find
  public boolean isConnected(int p, int q)
  {
    boolean flag;
    try{
      flag = (id[q] == id[p]);
    }
    catch (ArrayIndexOutOfBoundsException e){
      String error = String.format("index should be from 0 to %d\n", id.length-1);
      throw new RuntimeException(error);
    }
    return flag;
  }

  // union command
  public void union(int p, int q)
  {
    int pid, qid;
    try{
      pid = id[p];
      qid = id[q];
    }
    catch (ArrayIndexOutOfBoundsException e){
      String error = String.format("index should be from 0 to %d\n", id.length-1);
      throw new RuntimeException(error);
    }
    for (int i =0; i < id.length; i++)
    {
      if (id[i] == pid)
        id[i] = qid;
    }
  }
}
