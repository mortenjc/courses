import java.io.*;

public class UWF
{
   private int[] id;
   private int[] sz;

   public UWF(int N)
   {
      id = new int[N]; 
      sz = new int[N]; 
      for (int i=0; i<N ; i++) {
         id[i]=i;
         sz[i]=1;
      }
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
      int i = root(p);
      int j = root(q);
      if (sz[i] < sz[j]) {
         id[i] = j; sz[j] += sz[i];
      } else {
         id[j] = i; sz[i] += sz[j];
      }
  } 

   public void dump()
   {
      for (int i=0; i<id.length; i++)
         System.out.print(id[i]+" ");
      System.out.println("");
      for (int i=0; i<sz.length; i++)
         System.out.print(sz[i]+" ");
      System.out.println("");
      System.out.println("");
   }

   public static void main(String[] args) {
       int N = 10;
       UWF un = new UWF(N);

       un.dump(); 
       un.union(2,4);
       un.dump(); 
       un.union(5,7);
       un.union(8,6);
       un.union(7,6);
       un.union(9,0);
       un.union(0,3);
       un.union(0,4);
       un.union(2,1);
       un.union(7,0);
       un.dump(); 
      
   }
}




