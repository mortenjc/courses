

public class DFA {
  
  private final int R;
  private int [][] dfa;
  private char [] pattern;
  
  DFA(char[] pattern, int R) {
    this.R = R;
    this.pattern = new char[pattern.length];
    for (int j = 0; j < pattern.length; j++)
      this.pattern[j] = pattern[j];
    
    // build DFA from pattern
    int M = pattern.length;
    dfa = new int[R][M]; 
    dfa[pattern[0]][0] = 1; 
    for (int X = 0, j = 1; j < M; j++) {
      for (int c = 0; c < R; c++) 
        dfa[c][j] = dfa[c][X];     // Copy mismatch cases. 
      dfa[pattern[j]][j] = j+1;      // Set match case. 
      X = dfa[pattern[j]][X];        // Update restart state. 
    } 
  }
  
  public String toString() {
    String res = "";
    for (int j = 0; j < R; j++) {
    for (int i = 0; i < pattern.length; i++) {
      res = res + " " + dfa[j][i];
    }
      res = res + "\n";
    }
    return res;
  }
  
  public static void main(String [] args) {
    char [] pat = "AABCAABBACCA".toCharArray();
    DFA dfa = new DFA(pat, 69);
    System.out.println(dfa);
  }
  
}