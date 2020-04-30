import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
  private SearchNode lastNode;

  private class SearchNode implements Comparable<SearchNode> {
    private final Board board;
    private final int moves;
    private SearchNode predecessor;
    private final int manhattanVal;

    public SearchNode(Board board, int moves) {
      if (board == null)
        throw new IllegalArgumentException("The input argument of constructor cannot be null!");
      this.board = board;
      this.moves = moves;
      this.predecessor = null;
      this.manhattanVal = board.manhattan();
    }

    public int compareTo(SearchNode that) {
      return (this.manhattanVal + this.moves) - (that.manhattanVal + that.moves);
    }
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null)
      throw new IllegalArgumentException("The input argument of constructor cannot be null!");
    solve(initial);
  }

  private void solve(Board initial) {
    MinPQ<SearchNode> nodePQ     = new MinPQ<SearchNode>();
    MinPQ<SearchNode> nodePQTwin = new MinPQ<SearchNode>();
    nodePQ.insert(new SearchNode(initial, 0));
    nodePQTwin.insert(new SearchNode(initial.twin(), 0));
    SearchNode lastNodeTwin;
    while (!nodePQ.isEmpty() && !nodePQTwin.isEmpty()) {
      lastNode = nodePQ.delMin();
      if (lastNode.board.isGoal()) return;
      for (Board board : lastNode.board.neighbors()) {
        if (lastNode.predecessor != null)
          if (board.equals(lastNode.predecessor.board))
            continue;
        SearchNode newNode = new SearchNode(board, lastNode.moves + 1);
        newNode.predecessor = lastNode;
        nodePQ.insert(newNode);
      }
      // twin
      lastNodeTwin = nodePQTwin.delMin();
      if (lastNodeTwin.board.isGoal()) return;
      for (Board board : lastNodeTwin.board.neighbors()) {
        if (lastNodeTwin.predecessor != null)
          if (board.equals(lastNodeTwin.predecessor.board))
            continue;
        SearchNode newNode = new SearchNode(board, lastNodeTwin.moves + 1);
        newNode.predecessor = lastNodeTwin;
        nodePQTwin.insert(newNode);
      }
    }
  }

  // is the initial board solvable?
  public boolean isSolvable() {
    if (lastNode.board.isGoal()) return true;
    return false;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if (!isSolvable()) return -1;
    return lastNode.moves;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (!isSolvable()) return null;
    Stack<Board> solutionStack = new Stack<Board>();
    SearchNode node = lastNode;
    while (node.predecessor != null) {
      solutionStack.push(node.board);
      node = node.predecessor;
    }
    solutionStack.push(node.board);
    return solutionStack;
  }

  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
  }
}
