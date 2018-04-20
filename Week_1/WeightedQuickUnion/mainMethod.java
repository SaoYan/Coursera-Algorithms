import yiqi.WeightedQuickUnionUF;

public class mainMethod {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    WeightedQuickUnionUF client = new WeightedQuickUnionUF(N);
    client.union(0, 10);
    client.union(10, 25);
    System.out.println(client.isConnected(0, 10));
    System.out.println(client.isConnected(0, 25));
    System.out.println(client.isConnected(0, 20));
  }
}