
public class MoveToFront {
  
  private static char [] seq = new char [256];
  
  private static void init() {
    for (int i = 0; i < 256; i++)
      seq[i] = (char) i;
  }
  
  private static int indexOf(char c) {
    for (int i = 0; i < 256; i++)
      if (seq[i] == c)
      return i;
    throw new IndexOutOfBoundsException();
  }
  
  private static void toFront(int idx) {
    if ((idx < 0) || (idx > 256))
      throw new IndexOutOfBoundsException();
    if (idx == 0) {
      return;
    }  
    // not at first position
    char c0 = seq[idx];  
    for (int i = idx; i > 0; i--)
    {
      seq[i] = seq[i-1];
    }
    seq[0] = c0;
  }
  
  
  public static void encode() {
    init();

    while (!BinaryStdIn.isEmpty()) {
      char c = BinaryStdIn.readChar();
      int idx = indexOf(c);
      BinaryStdOut.write((byte) idx);
      toFront(idx);
    }
    BinaryStdOut.close();
  }
  
  public static void decode() {
    init();
     
    while (!BinaryStdIn.isEmpty()) {
      int i = (int) BinaryStdIn.readChar();
      BinaryStdOut.write((byte) seq[i]);
      toFront(i);
    }
    BinaryStdOut.close();
  }
  
  public static void main(String [] args) {
    if (args[0].equals("-")) encode();
    else if (args[0].equals("+")) decode();
    else
      throw new IllegalArgumentException("Illegal command line argument");
  }
}