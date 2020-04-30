import java.util.Arrays;
import java.util.LinkedList;
import edu.princeton.cs.algs4.StdOut;

public class Board {
  private final int[][] blocks;

  // construct a board from an n-by-n array of blocks
  // (where blocks[i][j] = block in row i, column j)
  public Board(int[][] blocks) {
    // this.blocks = blocks
    // do not use reference!
    // unsafe because argument "blocks" is externally mutable
    // use defensive copy
    int n = blocks.length;
    this.blocks = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        this.blocks[i][j] = blocks[i][j];
  }

  // board dimension n
  public int dimension() { return this.blocks.length; }

  // number of blocks out of place
  public int hamming() {
    int hammingVal = 0;
    int correctNum;
    for (int i = 0; i < dimension(); i++) {
      for (int j = 0; j < dimension(); j++) {
        if (this.blocks[i][j] == 0)
          continue;
        if (i == dimension() - 1 && j == dimension() - 1)
          correctNum = 0;
        else
          correctNum = i * dimension() + (j + 1);
        if (this.blocks[i][j] != correctNum)
          hammingVal++;
      }
    }
    return hammingVal;
  }

  // sum of Manhattan distances between blocks and goal
  public int manhattan() {
    int manhattanVal = 0;
    int iGoal, jGoal;
    for (int i = 0; i < dimension(); i++) {
      for (int j = 0; j < dimension(); j++) {
        if (this.blocks[i][j] == 0)
          continue;
        iGoal = (this.blocks[i][j] - 1) / dimension();
        jGoal = (this.blocks[i][j] - 1) % dimension();
        manhattanVal += Math.abs(iGoal - i);
        manhattanVal += Math.abs(jGoal - j);
      }
    }
    return manhattanVal;
  }

  // is this board the goal board?
  public boolean isGoal() {
    int correctNum;
    for (int i = 0; i < dimension(); i++) {
      for (int j = 0; j < dimension(); j++) {
        if (i == dimension() - 1 && j == dimension() - 1)
          correctNum = 0;
        else
          correctNum = i * dimension() + (j + 1);
        if (this.blocks[i][j] != correctNum)
          return false;
      }
    }
    return true;
  }

  // a board that is obtained by exchanging any pair of blocks
  public Board twin() {
    // create a copy
    int[][] twinBlocks = new int[dimension()][dimension()];
    for (int m = 0; m < dimension(); m++)
      for (int n = 0; n < dimension(); n++)
        twinBlocks[m][n] = this.blocks[m][n];
    // pick two blocks
    int i1 = -1, j1 = -1, i2 = -1, j2 = -1;
    int i, j;
    for (int n = 0; n < dimension() * dimension(); n++) {
      i = n / dimension();
      j = n % dimension();
      if (this.blocks[i][j] == 0)
        continue;
      if (i1 < 0 || j1 < 0) {
        i1 = i;
        j1 = j;
      }
      else if (i2 < 0 || j2 < 0) {
        i2 = i;
        j2 = j;
      }
      else
        break;
    }
    // exchange
    int temp = twinBlocks[i1][j1];
    twinBlocks[i1][j1] = twinBlocks[i2][j2];
    twinBlocks[i2][j2] = temp;
    return new Board(twinBlocks);
  }

  // does this board equal y?
  public boolean equals(Object y) {
    if (y == this) return true;
    if (y == null) return false;
    if (y.getClass() != this.getClass()) return false;
    Board that = (Board) y;
    return Arrays.deepEquals(this.blocks, that.blocks);
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    LinkedList<Board> neighborList = new LinkedList<Board>();
    // find blank block
    int i = 0, j = 0;
    for (int n = 0; n < dimension() * dimension(); n++) {
      i = n / dimension();
      j = n % dimension();
      if (this.blocks[i][j] == 0)
        break;
    }
    // move the left block
    int[][] newBlocks;
    if (i > 0) {
      newBlocks = new int[dimension()][dimension()];
      for (int m = 0; m < dimension(); m++)
        for (int n = 0; n < dimension(); n++)
          newBlocks[m][n] = this.blocks[m][n];
      newBlocks[i][j] = newBlocks[i-1][j];
      newBlocks[i-1][j] = 0;
      neighborList.add(new Board(newBlocks));
    }
    // move the right block
    if (i < dimension() - 1) {
      newBlocks = new int[dimension()][dimension()];
      for (int m = 0; m < dimension(); m++)
        for (int n = 0; n < dimension(); n++)
          newBlocks[m][n] = this.blocks[m][n];
      newBlocks[i][j] = newBlocks[i+1][j];
      newBlocks[i+1][j] = 0;
      neighborList.add(new Board(newBlocks));
    }
    // move the top block
    if (j > 0) {
      newBlocks = new int[dimension()][dimension()];
      for (int m = 0; m < dimension(); m++)
        for (int n = 0; n < dimension(); n++)
          newBlocks[m][n] = this.blocks[m][n];
      newBlocks[i][j] = newBlocks[i][j-1];
      newBlocks[i][j-1] = 0;
      neighborList.add(new Board(newBlocks));
    }
    // move the bottom block
    if (j < dimension() - 1) {
      newBlocks = new int[dimension()][dimension()];
      for (int m = 0; m < dimension(); m++)
        for (int n = 0; n < dimension(); n++)
          newBlocks[m][n] = this.blocks[m][n];
      newBlocks[i][j] = newBlocks[i][j+1];
      newBlocks[i][j+1] = 0;
      neighborList.add(new Board(newBlocks));
    }
    return neighborList;
  }

  // string representation of this board (in the output format specified below)
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(dimension() + "\n");
    for (int i = 0; i < dimension(); i++) {
        for (int j = 0; j < dimension(); j++) {
            s.append(String.format("%2d ", this.blocks[i][j]));
        }
        s.append("\n");
    }
    return s.toString();
  }

  public static void main(String[] args) {
    int n = 4;
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        blocks[i][j] = i * n + j;
    blocks[0][0] = blocks[2][1];
    blocks[2][1] = 0;
    Board board = new Board(blocks);

    StdOut.println("Original board");
    StdOut.println(board.toString());
    StdOut.printf("Hamming: %d\n", board.hamming());
    StdOut.printf("Manhattan: %d\n", board.manhattan());
    StdOut.printf("Is goal? %b\n", board.isGoal());
    StdOut.println('\n');

    Board twinBoard = board.twin();
    StdOut.println("Twin board");
    StdOut.println(twinBoard.toString());
    StdOut.printf("Is goal? %b\n", twinBoard.isGoal());
    StdOut.printf("Equal to original? %b\n", board.equals(twinBoard));
    StdOut.println('\n');

    StdOut.println("Neighbot boards");
    for (Board neighborBoard : board.neighbors())
      StdOut.println(neighborBoard.toString());

    StdOut.println(board.toString());
  }
}
