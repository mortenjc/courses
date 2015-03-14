
public class WordNet {
  private static final int INFINITY = Integer.MAX_VALUE;
  
  private ST<String, Bag<Integer>> syns; // (nouns, index) 
  private ST<Integer, String> synsi;
  private ST<Integer, String> synsetsi;
  private Digraph DG;     // hypernyms DAG
  private SAP sap;
  private int v = 0;      // Vertices in DAG (number of lines in synsets.txt)
  
  // constructor takes the name of the two input files public 
  public WordNet(String synsets, String hypernyms)  {
    String [] fields;
    In in = new In(synsets);
    syns  = new ST<String, Bag<Integer>>();
    synsi = new ST<Integer, String>();
    synsetsi = new ST<Integer, String>();
    int index;
    
    while (in.hasNextLine()) {
      v++;
      String line = in.readLine();
      fields = line.split(",");
      index = Integer.parseInt(fields[0]);
      synsetsi.put(index, fields[1]);
      fields = fields[1].split(" ");
      for (String field : fields) {
        synsi.put(index, field);
        if (syns.contains(field)) {
          Bag<Integer> bag = syns.get(field);
          bag.add(index);
          syns.put(field, bag);
        } else {
          Bag<Integer> bag = new Bag<Integer>();
          bag.add(index);
          syns.put(field, bag);
        }
      }
    }
    
    DG = new Digraph(v);
    in = new In(hypernyms);
    while (in.hasNextLine()) {
      String line = in.readLine();
      fields = line.split(",");
      int v1 = Integer.parseInt(fields[0]);
      for (int i = 1; i < fields.length; i++) {
        int w = Integer.parseInt(fields[i]);
        DG.addEdge(v1, w);
      }
    }
     
    // Throw exception if digraph has a cycle
    DirectedCycle cyc = new DirectedCycle(DG);
    if (cyc.hasCycle())
      throw new IllegalArgumentException();
    
    // Throw exception if digraph has no root
    int roots = 0;
    for (int i = 0; i < DG.V(); i++) {
      int sum = 0;
      for (int w : DG.adj(i))
        sum++;
      
      if (sum == 0) {
        roots++;
      }
    }
    if (roots != 1) 
      throw new IllegalArgumentException();
    
     sap = new SAP(DG);
  }
  
  // returns all WordNet nouns 
  public Iterable<String> nouns() {
    return syns;
  }
  
  // is the word a WordNet noun?
  public boolean isNoun(String word) {   
    return syns.contains(word);
  }
  
  // distance between nounA and nounB
  public int distance(String nounA, String nounB) {
    if (!isNoun(nounA) || !isNoun(nounB))
      throw new IllegalArgumentException();
    
    if (nounA.equals(nounB))
      return 0;
    
    Bag<Integer> v1 = syns.get(nounA);
    Bag<Integer> v2 = syns.get(nounB);
    
    return sap.length(v1, v2);
  }
  
  // a synset (second field of synsets.txt) that is the common ancestor 
  // of nounA and nounB in a shortest ancestral path (defined below) 
  public String sap(String nounA, String nounB) {
    if (!isNoun(nounA) || !isNoun(nounB))
      throw new IllegalArgumentException();
    
    if (nounA.equals(nounB))
      return nounA;
    
    Bag<Integer> v1 = syns.get(nounA);
    Bag<Integer> v2 = syns.get(nounB);
         
    return synsetsi.get(sap.ancestor(v1, v2));
  }
  
  // for unit testing of this class 
  public static void main(String[] args) { 
  }
  
}