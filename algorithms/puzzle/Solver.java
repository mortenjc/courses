
public class Solver {
  private Node goal = null;
  private int  minmoves = -1;
  
  private MinPQ<Node> pq = new MinPQ<Node>();
  
  private class Node implements Comparable<Node>{
    private int moves;
    private Board b;
    private Node prev;
    
    public int compareTo(Node that) {
      if      (this.moves < that.moves) return -1;
      else if (this.moves > that.moves) return +1;
      else                              return 0;
    }
  }
 
  private void insert(Board brd, int mvs, Node prv)
  {
    Node nd = new Node();
    nd.b = brd;
    nd.moves = mvs;
    nd.prev = prv;
    pq.insert(nd);
  }
    
  public Solver(Board initial) { // find solution to initial board (use A* algo)
    Node nd = new Node();
    nd.moves = 0;
    nd.prev = null;
    nd.b = initial;
    pq.insert(nd);
    
    StdOut.println("First board:");
    StdOut.println(initial.toString());

    // .... now
    int iter=0;
    while (true) {      
      //StdOut.println("Iter: " + iter);
      Node n = pq.delMin();
      if (n.b.isGoal()) {
        goal = n;
        minmoves = n.moves;
        //StdOut.println("Goal found");
        return;
      } else {
        Queue<Board> nbrs = (Queue<Board>) n.b.neighbors();
        for (Board nbr: nbrs) { 
           //StdOut.println("Inserting neighbor");
           //StdOut.println(nbr.toString());
          if      (n.prev == null)
            insert(nbr, n.moves + nbr.hamming(), n);
          else if (!nbr.equals(n.prev.b))
            insert(nbr, n.moves + nbr.hamming(), n);
          
        }
        iter++;
      }
    }
    
    
  }
  
  public boolean isSolvable() {            // is the initial board solvable?
    return (goal != null);
  }
  
  public int moves() { // min # moves to solve initial board; -1 if no solution
    return minmoves;
  }
  
  public Iterable<Board> solution() { // seq of boards in a shortest solution
    Node nd = goal;
    Queue<Board> myq = new Queue<Board>();
    while (nd.prev != null)
    {
      //StdOut.println(nd.b.toString());
      myq.enqueue(nd.b);
      nd = nd.prev;
    }
    return myq;
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