
public class CircularSuffixArray {
  private int N;
  private int [] ia;
  
  public CircularSuffixArray(String s) {  // circular suffix array of s
   
    N = s.length();
    String sa = s + s;
    ia = new int [N];
    for (int i = 0; i < N; i++)
       ia[i] = i;
    Quick3stringIdx.sort(ia, sa);
  }
  
  public int length() {                  // length of s
    return N;
  }
  
  public int index(int i) {              // returns index of ith sorted suffix
    return ia[i];
  }
  
  
  public static void main(String [] args) {
    CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
    for (int i = 0; i < csa.length(); i++)
      System.out.println("Index["+i+"] = " + csa.index(i));
    System.out.println(csa);
    
    csa = new CircularSuffixArray("AAAAAA");
    for (int i = 0; i < csa.length(); i++)
      System.out.println("Index["+i+"] = " + csa.index(i));

  }
}