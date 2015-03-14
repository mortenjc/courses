
import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.io.File;

public class Brute {
  
   private static int entries; 
   private static int maxx, maxy;
   private static int minx, miny;
   private static Point [] pts;
  
   private static void show(Point p)
   {
       StdOut.print(p.toString());
   }
   
   private static void initGraphics(double lox, double hix, double loy, double hiy)
   {
      StdDraw.setXscale(lox, hix);
      StdDraw.setYscale(loy, hiy);
      StdDraw.setPenRadius(0.005);
   }
   
   private static int readFile(String filename)
   {
      int ix, iy;
      
      try {
        Scanner scanner = new Scanner(new File(filename));
        
        entries = scanner.nextInt();
        pts = new Point[entries];
        
        for (int i = 0; i < entries; i++)
        {
          ix = scanner.nextInt();
          iy = scanner.nextInt();  
          minx = Math.min(minx, ix);
          maxx = Math.max(maxx, ix);
          miny = Math.min(miny, iy);
          maxy = Math.max(maxy, iy);
          pts[i] = new Point(ix, iy);
        }
      } catch (IOException e) {
        StdOut.println(e);
        return 0;
      }
      return entries;
   }
   
   private static void col(Point p, Point q, Point r, Point s)
   {
      if (p.slopeTo(q) != q.slopeTo(r))
        return;
      else if (q.slopeTo(r) != q.slopeTo(s))
        return;
          
        //StdOut.println(p.toString() + " -> " + q.toString() + " -> " 
        //             + r.toString() + " -> " + s.toString());
        p.drawTo(s);
   }
  
   public static void main(String [] argv)
   {
      int res = readFile(argv[0]);
      if (res == 0)
      {
        StdOut.println("Exiting...");
        return;
      }
      
      initGraphics(minx, maxx, miny, maxy);
      
      for (int i = 0; i < entries; i++)
      {
         //show(pts[i]);
         pts[i].draw();
      }
      
      Arrays.sort(pts);
      StdDraw.setPenRadius(0.001);

      Point p, q, r, s;
      Point [] al = new Point[4];
      
      for (int i = 0; i < entries; i++)
       for (int j = i+1; j < entries; j++)
        for (int k = j+1; k < entries; k++)
         for (int l = k+1; l < entries; l++)
         {
            al[0] = pts[i];
            al[1] = pts[j];
            al[2] = pts[k];
            al[3] = pts[l];
            Arrays.sort(al);
            col(al[0], al[1], al[2], al[3]);
         }
      
   }
  
}