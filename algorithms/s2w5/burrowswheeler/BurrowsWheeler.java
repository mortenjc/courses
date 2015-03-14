import java.util.Arrays;

public class BurrowsWheeler {
  // apply Burrows-Wheeler encoding, reading from 
  // standard input and writing to standard output
  public static void encode() {
    String a = BinaryStdIn.readString();
    String b = a + a;
    CircularSuffixArray csa = new CircularSuffixArray(a);
    
    int first = -1;
    for (int i = 0; i < a.length(); i++) {
       if (csa.index(i) == 0)
         first = i;
    }

    BinaryStdOut.write(first);
    for (int j = 0; j < a.length(); j++)
      BinaryStdOut.write((char) b.charAt(csa.index(j) + a.length()-1));
     
    BinaryStdOut.close();
  }
  
  // apply Burrows-Wheeler decoding, reading from 
  // standard input and writing to standard output
  public static void decode() {
    int first = BinaryStdIn.readInt();
    String t = BinaryStdIn.readString();
    int [] next = new int [t.length()];
    
    char [] srt = t.toCharArray();
    Arrays.sort(srt);
    // Sorted string - first column
    String s = new String(srt);
    
    //Where to start indexOf from
    int [] idx = new int [256];
    for (int i = 0; i < 256; i++)
      idx[i] = 0;
    
    for (int i = 0; i < t.length(); i++) {
      char c = s.charAt(i);   // pick i'th char in sorted string
      int ic = t.indexOf(c, idx[(int) c]); // Find index of 'c'in t[]
      next[i] = ic;
      idx[(int) c] = ic +1;
  
    }
    
    int ix = first;
    for (int i = 0; i < t.length(); i++)
    {
       char c = s.charAt(ix);
       BinaryStdOut.write((char) c);
       ix = next[ix];
    }
    
    BinaryStdOut.close();
  }
  
  // if args[0] is '-', apply Burrows-Wheeler encoding
  // if args[0] is '+', apply Burrows-Wheeler decoding
  public static void main(String[] args) {
    if (args[0].equals("-")) encode();
    else if (args[0].equals("+")) decode();
    else 
      throw new IllegalArgumentException("Illegal command line argument");
  }
}