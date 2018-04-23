/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       4/12/2018
 *  Last updated:  4/23/2018
 *
 *  Compilation:   javac -d . QuickFindUF.java mainMethod.java
 *  Execution:     java mainMethod N
 *
 *  Invoke Quick-Find algorithm
 *
 *  % java mainMethod 100
 *  true
 *  true
 *  false
 *
 *----------------------------------------------------------------*/

import yiqi.QuickFindUF;

public class mainMethod {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    QuickFindUF client = new QuickFindUF(N);
    client.union(0, 10);
    client.union(10, 25);
    System.out.println(client.isConnected(0, 10));
    System.out.println(client.isConnected(0, 25));
    System.out.println(client.isConnected(0, 20));
  }
}
