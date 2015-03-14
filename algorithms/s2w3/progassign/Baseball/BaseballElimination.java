import java.util.ArrayList;

public class BaseballElimination {
  
  private static final double INFINITY = Double.POSITIVE_INFINITY;
  private static final double EPSILON = 1E-11;
  
  private int       N;   // # of teams
  private String [] teamNames;
  private int []    teamIds;
  private int []    w;
  private int []    l;
  private int []    r;
  private int [][]  g;
  private int maxWin = 0;
  private int maxId = -1;
  
  // create a baseball division from given filename in format specified below
  public BaseballElimination(String filename) {
    In in = new In(filename);
    N = in.readInt();
    teamNames = new String [N];
    teamIds = new int [N];
    w = new int [N];
    l = new int [N];
    r = new int [N];
    g = new int [N][N];
    
    for (int i = 0; i < N; i++) {
      teamNames[i] = in.readString();
      teamIds[i] = i;
      w[i] = in.readInt();
      if (w[i] > maxWin) {
        maxWin = w[i];
        maxId = i;
      }
      l[i] = in.readInt();
      r[i] = in.readInt();
      for (int j = 0; j < N; j++)
        g[i][j] = Integer.parseInt(in.readString());
    }
  }
  
  // number of teams
  public int numberOfTeams() { return N; }                       
  
  // all teams
  public Iterable<String> teams() {
    ArrayList<String> names = new ArrayList<String>();
    for (int i = 0; i < N; i++)
      names.add(teamNames[i]);
    return names;
  }
  
  // number of wins for given team
  public int wins(String team) {
    for (int i = 0; i < N; i++) {
      if (teamNames[i].equals(team))
        return w[i];
    }
    throw new IllegalArgumentException();
  }
  
  // number of losses for given team
  public int losses(String team) {                    
    for (int i = 0; i < N; i++) {
      if (teamNames[i].equals(team))
        return l[i];
    }
    throw new IllegalArgumentException();
  }
  
  // number of remaining games for given team
  public int remaining(String team)  {
    for (int i = 0; i < N; i++) {
      if (teamNames[i].equals(team))
        return r[i];
    }
    throw new IllegalArgumentException();
  }
  
  // number of remaining games between team1 and team2
  public int against(String team1, String team2) {
    int t1 = atoi(team1);
    int t2 = atoi(team2);
    
    if ((t1 < 0) || (t1 >= N) || (t2 < 0) || (t2 >= N))
      throw new IllegalArgumentException();
    return g[t1][t2];
  }
  
  private Bag<String> createNetwork(int eliminate) {
    if ((eliminate < 0) || (eliminate >= N))
      throw new IllegalArgumentException();
    
    Bag<String> bag = new Bag<String>();
    
    // Trivial elimination
    if (w[eliminate] + r[eliminate] < maxWin) {
      bag.add(teamNames[maxId]);
      return bag;
    }
    
    // Non trivial elimination  
    int N2 = 1 + (N)*(N-1)/2  + N + 1;
    FlowNetwork FN = new FlowNetwork(N2);
    int max = N2 - 1;
    double sCap = 0;
    
    int nodeIndex = N;
    for (int i = 0; i < N; i++) {
      if (i == eliminate)
        continue;
      FN.addEdge(new FlowEdge(i, max, w[eliminate] + r[eliminate] - w[i]));
      for (int j = 0; j < N; j++) {
        if ((j == eliminate) || (i >= j))
          continue;
        sCap += g[i][j];
        FN.addEdge(new FlowEdge(max -1, nodeIndex, g[i][j]));
        FN.addEdge(new FlowEdge(nodeIndex, i, INFINITY));
        FN.addEdge(new FlowEdge(nodeIndex, j, INFINITY));
        nodeIndex++;   
      }
    }
    
    FordFulkerson FF = new FordFulkerson(FN, max - 1, max);
   
    if (Math.abs(FF.value() - sCap) > EPSILON) {
      for (int i = 0; i < N; i++)
        if (FF.inCut(i))
          bag.add(teamNames[i]);
    } else
      bag = null;
    
    return bag;
  }
  
  // is given team eliminated?
  public boolean isEliminated(String team) { 
    return (createNetwork(atoi(team)) != null);
  }
  
  public Iterable<String> certificateOfElimination(String team) {
    return createNetwork(atoi(team));
  }
  
  private int atoi(String a) {
    int index = -1;
    for (int i = 0; i < N; i++)
      if (teamNames[i].equals(a))
        index = i; 
    return index;
  }
  
  private void toFile(String fname, FlowNetwork fn)
  {
    Out out = new Out(fname);
    out.println("digraph flow {");
    out.println("rankdir = LR;");
    out.println("node [fixedsize=true, shape=circle];");
    for (int i = 0; i < fn.V(); i++) {
      //out.println("i: " + i);
      for (FlowEdge e : fn.adj(i)) {
        String from = " " + i;
        String to = " " + e.to();
        String cap;
        if (e.from() == i) {
          if (i == fn.V() -2)
            from = "s";
          if (e.to() == (fn.V() -1))
            to = "t";
          if (e.capacity() > 1000000)
            cap = "oo";
          else 
            cap = "" + e.capacity();
          out.println(from + " -> " + to + "[fontsize=11, label=\"" + e.flow() + "/" + cap + "\"];");
        }
      }
    }
    out.println("}");
    out.close();
  }
  
  public static void main(String[] args) {
    BaseballElimination division = new BaseballElimination("teams4.txt");
    for (String team : division.teams()) {
      if (division.isEliminated(team)) {
        StdOut.print(team + " is eliminated by the subset R = { ");
        for (String t : division.certificateOfElimination(team))
          StdOut.print(t + " ");
        StdOut.println("}");
      }
      else {
        StdOut.println(team + " is not eliminated");
      }
    }
  
  }
}