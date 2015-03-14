
public class SAP {
  private static final int INFINITY = Integer.MAX_VALUE;
  
  private Digraph DG;
  
  // constructor takes a digraph (not necessarily a DAG) 
  public SAP(Digraph G) {
    DG = new Digraph(G);   
  }
  
  // length of shortest ancestral path between v and w; -1 if no such path 
  public int length(int v, int w) {
    Bag<Integer> vs = new Bag<Integer>();
    Bag<Integer> ws = new Bag<Integer>();
    vs.add(v);
    ws.add(w);
    
    return generalizedSearch(vs, ws, true);  
  }
  
  // a common ancestor of v and w that participates in a shortest 
  // ancestral path; -1 if no such path 
  public int ancestor(int v, int w) {
    Bag<Integer> vs = new Bag<Integer>();
    Bag<Integer> ws = new Bag<Integer>();
    vs.add(v);    
    ws.add(w);
    
    return generalizedSearch(vs, ws, false);
  }
  
  // length of shortest ancestral path between any vertex in v and any vertex in w;
  // -1 if no such path 
  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    
    return generalizedSearch(v, w, true);
  }
  
  // a common ancestor that participates in shortest ancestral path; -1 
  // if no such path 
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    
    return generalizedSearch(v, w, false);
  }
  
  // Used in all four length() and ancestor() functions
  private int generalizedSearch(Iterable<Integer> v, 
                                Iterable<Integer> w, boolean length) {
    for (int vs : v) {
      if ((vs < 0) || (vs >= DG.V()))
        throw new IndexOutOfBoundsException();
    }
    for (int ws : w) {
      if ((ws < 0) || (ws >= DG.V()))
        throw new IndexOutOfBoundsException();
    }
    
    BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(DG, v);
    BreadthFirstDirectedPaths bfdp2 = new BreadthFirstDirectedPaths(DG, w);
    
    int min = INFINITY;
    int anc = -1;
    
    for (int i = 0; i < DG.V(); i++) {
      int dist1 = bfdp1.distTo(i);
      int dist2 = bfdp2.distTo(i);
      int dist;
      if ((dist1 == INFINITY) || (dist2 == INFINITY))
        dist = INFINITY;
      else
        dist = dist1 + dist2;
      
      if (dist < min) {
        min = dist;
        anc = i; 
      }
    }
    
    if (min == INFINITY)
      min = -1;
    
    if (length)
      return min;
    else
      return anc;
  }  
  
  public static void main(String[] args) {
  }
}