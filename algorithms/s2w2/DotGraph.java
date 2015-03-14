class DotGraph {
  
  private EdgeWeightedGraph G;
  private KruskalMST mst;
  private ST<String, Integer> mstedges;
  
  public DotGraph(EdgeWeightedGraph g)  {
    G = new EdgeWeightedGraph(g);
    mst = new KruskalMST(G);
    mstedges = new ST<String, Integer>();

    for (Edge e : mst.edges())
      mstedges.put(e.either() + " -- " + e.other(e.either()), 1);
  }
  
  public void writeToFile(String name)
  {
    Out out = new Out(name);
    out.println("graph mst {");
    out.println("  node [fixedsize=true, label=\"\", width=0.1, shape=point, color=black];");
      
    out.println("  edge [color = grey, penwidth=0.75];");
    for (Edge e : G.edges()){
      String tmp = e.either() + " -- " + e.other(e.either());
      if (!mstedges.contains(tmp))
        out.println(tmp);
    } 
    
    out.println("  edge [color = black, penwidth=2.5];");
    for (String st : mstedges.keys())
      out.println(st);
    out.println("}");
    out.close();
  }
  
  public static void main(String [] args) {
    In in = new In("mediumEWG.txt");
    EdgeWeightedGraph G = new EdgeWeightedGraph(in);
    
    DotGraph dot = new DotGraph(G);
    dot.writeToFile("tmp.dot");
  } 
}