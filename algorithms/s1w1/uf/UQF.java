import java.io.*;

public class UQF
{
   private int[] id;

   public UQF(int N)
   {
      id = new int[N]; 
      for (int i=0; i<N ; i++)
         id[i]=i;
   }
 
   public boolean connected(int p, int q) {
      return id[p] == id[q];
   } 

   public void union(int p, int q)
   {
      int pint = id[p];
      int qint = id[q];
      for (int i=0; i<id.length ; i++)
         if (id[i]==pint) id[i]=qint;
   }

   public void dump()
   {
      for (int i=0; i<id.length; i++)
         System.out.print(id[i]+" ");
      System.out.println("");
   }

   public static void main(String[] args) {
       int N = 10;
       UQF un = new UQF(N);

       un.dump(); 
       un.union(2,7);
       un.union(2,4);
       un.union(8,7);
       un.union(8,3);
       un.union(2,6);
       un.union(9,3);
       un.dump(); 
      
   }
}




