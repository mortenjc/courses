import java.util.Random;

public class PercolationStats {
  
  private double [] values;
  
  public PercolationStats(int N, int T) {    
    if (N <= 0 || T <= 0)
      throw new IllegalArgumentException("Values of N or T is illegal as input");
    
    values = new double [T];   // T testresults to be saved
    Random rand = new Random();
    
    for (int i = 0; i < T; i++)
    {
      int count = 0;
      values[i] = 0.0;
      
      System.out.print(".");
      Percolation pc = new Percolation(N);
      
      while (!pc.percolates())
      {
        int ix = rand.nextInt(N)+1;
        int jx = rand.nextInt(N)+1;
        
        if (!pc.isOpen(ix, jx))
        {
          pc.open(ix, jx);
          count++;
        }  
      }
      
      values[i] = 1.0*count/(N*N);
    }

  }
  
  public double mean() {                 
    double mean = 0.0;
    for (int i = 0; i < values.length; i++)
      mean += values[i];
    return mean/values.length;
  }
  
  public double stddev() {                  
    double dev = 0.0;
    double mymean = mean();
    for (int i = 0; i < values.length; i++)
      dev += (values[i]-mymean)*(values[i]-mymean);
    return Math.sqrt(dev/(values.length - 1));
  }
  
  public static void main(String[] args) {   // test client, described below
    
    int T = 100;
    int N = 1000;
    
    PercolationStats ps = new PercolationStats(N, T);
    double mymean   = ps.mean();
    double mystddev = ps.stddev();
    double mylow    =  mymean - 1.96*mystddev/(Math.sqrt(T));
    double myhigh   =  mymean + 1.96*mystddev/(Math.sqrt(T));
    
    System.out.println();
    System.out.println("mean                      " + mymean);
    System.out.println("stddev                    " + mystddev);
    System.out.println("95% confidence interval = " + mylow + ", " + myhigh);
    
  }
}