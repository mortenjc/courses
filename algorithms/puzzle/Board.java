//import java.lang.StringBuffer;

public class Board {
  private int N;
  private int [][] board;    // First coordinate is x, second is y
  
  public Board(int[][] blocks) { // cstrct board from an N-by-N array of blocks
    N = blocks.length;
    board = new int [N][N];
    for (int x = 0; x < N; x++)
      for (int y = 0; y < N; y++)
         board[x][y] = blocks[y][x];
  }
                                 // (where blocks[i][j] = block in row i, column j)
  public int dimension() {                 // board dimension N
    return N;
  }
  
  public int hamming() {                   // number of blocks out of place
    int dist = 0;
    for (int x = 0; x < N; x++)
      for (int y = 0; y < N; y++)
        if (this.board[x][y] != ((1+(x + y*N)) % (N*N)) && (x+1)*(y+1) != N*N)
          dist++;
    return dist;
  }
  
  public int manhattan() {     // sum of Manhattan dists between blocks & goal
    int dist = 0;
    //StdOut.println("----");
    for (int x = 0; x < N; x++)
      for (int y = 0; y < N; y++)
    {
      int val = board[x][y];
      
      if (val != 0) {
        int exx = (val - 1) % N;
        int exy = (val - 1) / N;
        //StdOut.println("val: , exx, exy: "+ val + " " + exx +", " + exy);
        //StdOut.println("dist: " + (Math.abs(exx-x) +Math.abs(exy-y)));
        dist += Math.abs(exx-x) +Math.abs(exy-y);
      }
    }
    return dist;
  }
  
  public boolean isGoal() {               // is this board the goal board
      for (int x = 0; x < N; x++)
          for (int y = 0; y < N; y++)
          {
             if (this.board[x][y] != ((1+(x + y*N)) % (N*N)))
                return false;
          }
    return true;
  }
  
  public Board twin() { // a board made by exchanging 2 adj blocks in same row
    // Always switch 0,0 with 1,0
    Board bd = this.copy();
    int iy = 0;
    if (bd.board[0][0] == 0 || bd.board[1][0] == 0)
      iy = 1;
    int tmp =  bd.board[0][iy];
    bd.board[0][iy] = bd.board[1][iy];
    bd.board[1][iy] = tmp;
    return bd;
  }
  
  public boolean equals(Object obj) {        // does this board equal y?
    // Objects must be of same type
    if (!(obj instanceof Board))
      return false; 
    
    // board sizes must be equal
    if (this.dimension() != ((Board) obj).dimension())
      return false;
    
    // Positions must be equal
    for (int x = 0; x < N; x++)
      for (int y = 0; y < N; y++)
        if (this.board[x][y] != ((Board) obj).board[x][y])
           return false;
    return true;
  }
  
  public Iterable<Board> neighbors() {     // all neighboring boards;
    Queue<Board> nbr = new Queue<Board>();
    int ix = -1, iy = -1;
    for (int x = 0; x < N; x++)
      for (int y = 0; y < N; y++)
         if (this.board[x][y] == 0) {
           ix = x;
           iy = y;
         }
    if (ix == -1 || iy == -1)
      return nbr;

    if (iy > 0)
    {
      //StdOut.println("Shift block down");
      Board bd = this.copy();
      int tmp = bd.board[ix][iy-1];
      bd.board[ix][iy] = tmp;
      bd.board[ix][iy-1] = 0;
      nbr.enqueue(bd);
      //StdOut.println(bd.toString());
    }
    
    if (ix < N-1)
    {
      //StdOut.println("Shift block left");
      Board bd = this.copy();
      int tmp = bd.board[ix+1][iy];
      bd.board[ix][iy] = tmp;
      bd.board[ix+1][iy] = 0;
      nbr.enqueue(bd);
      //StdOut.println(bd.toString());
    }
    
    if (ix > 0)
    {
      //StdOut.println("Shift block right");
      Board bd = this.copy();
      int tmp = bd.board[ix-1][iy];
      bd.board[ix][iy] = tmp;
      bd.board[ix-1][iy] = 0;
      nbr.enqueue(bd);
      //StdOut.println(bd.toString());
    }
    
    if (iy < N-1)
    {
      //StdOut.println("Shift block up");
      Board bd = this.copy();
      int tmp = bd.board[ix][iy+1];
      bd.board[ix][iy] = tmp;
      bd.board[ix][iy+1] = 0;
      nbr.enqueue(bd);
      //StdOut.println(bd.toString());
    }
    return nbr;
  }
  
  
  public String toString() {              // string representation of the board
    String bs = new String();
    bs = N + "\n";
    for (int y = 0; y < N; y++)
    {
      for (int x = 0; x < N; x++)
         bs = bs + board[x][y]+ " ";
      bs = bs + "\n";
    }
    return bs;
  }
  
   public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int x = 0; x < N; x++)
        for (int y = 0; y < N; y++)
            blocks[x][y] = in.readInt();

    Board initial = new Board(blocks);
    
    StdOut.println(initial.toString());
    StdOut.println("Hamming: " + initial.hamming());
    StdOut.println("Manhattan: " + initial.manhattan());
    
    StdOut.println("A twin...");
    Board twn = initial.twin();
    StdOut.println(twn.toString());
    
    StdOut.println("Neighbors...");
    Queue<Board> nbr = (Queue<Board>) initial.neighbors();
    for (Board b: nbr)
      StdOut.println(b.toString());
   
  }
   
  private Board copy()
  {
    Board nb = new Board(this.board);
    nb.N = this.board.length;
    for (int x = 0; x < N; x++)
      for (int y = 0; y < N; y++)
         nb.board[x][y] = this.board[x][y];
    return nb;
  }
  
}