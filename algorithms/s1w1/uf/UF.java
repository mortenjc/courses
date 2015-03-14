import java.io.*;

public class UF
{
   private int[] id;

   public UF(int N)
   {
      id = new int[N]; 
      for (int i=0; i<N ; i++)
         id[i]=i;
   }

   private int root(int p) {
      while (p != id[p]) p=id[p];
      return p;
   }

   public boolean connected(int p, int q) {
      return root(p) == root(q);
   } 

   public void union(int p, int q)
   {
      int pint = root(p);
      int qint = root(q);
      id[pint] = qint;
   }

   public static void main(String[] args) {
       int N = 10;
       UF un = new UF(N);
      
       un.union(2,7);
       un.union(2,4);
   }
}




