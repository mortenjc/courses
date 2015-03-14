public class Outcast {
  private static final int INFINITY = Integer.MAX_VALUE;
  private final WordNet WN;
  
  // Constructor
  public Outcast(WordNet wn) {
     WN = wn;
  }
  
  // given an array of WordNet nouns, return an outcast 
  public String outcast(String[] nouns) {
    
    int max = 0;
    String retval = "gylle";
    int distance;
    
    if (nouns.length == 1)
      return nouns[0];
    
    for (String word1 : nouns) {
      distance = 0;
      for (String word2 : nouns) {
        if (!word1.equals(word2)) {
          distance += WN.distance(word1, word2);
        }
      }
      if (distance > max) {
        max = distance;
        retval = word1;  
      }
    }
    return retval;
  }
  
  public static void main(String [] args) {
  }
}