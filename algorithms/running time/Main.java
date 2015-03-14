

public class Main
{
    
    private static void doIt(int N) {
      Stopwatch sw = new Stopwatch();
      Timing.trial(N,217408);
      double e = sw.elapsedTime();
      System.out.println("N: " + N + "  Time: " + e);  
    }
    
    public static void main(String[] argv) {
      System.out.println("Starting");
 
      doIt(20000);
      doIt(40000);
      doIt(80000);
      doIt(160000);
    }
}