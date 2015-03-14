import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.io.File;

public class Fast {
  
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
   
   private static void dumpNdraw(Point ref, Point [] arr, int from, int to)
      {
         Point[] tmp; //= new Point[entries];
         
         tmp = Arrays.copyOfRange(arr, from, to);
         Arrays.sort(tmp);
         if (ref.compareTo(tmp[0]) > 0)
           return;
         /*
         StdOut.print(ref.toString());
         for (Point a :tmp)
           StdOut.print(" -> " + a.toString());
         StdOut.println();
         */
         ref.drawTo(tmp[tmp.length-1]);
       
      }
   
   private static void dump(Point [] a)
   {
     StdOut.println("---------------------");
     for (int i = 0; i < entries; i++)
     {
       show(a[i]);
       StdOut.println();
     }
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
      
      Arrays.sort(pts);
      //StdOut.println("Sorted Fileset");
      //dump(pts);
      for (Point a : pts)
        a.draw();
      
      StdDraw.setPenRadius(0.001);

      // Prepare a copy of pts for sorting only
      Point [] tbs = Arrays.copyOf(pts, pts.length);
      
      for (int outer = 0; outer < entries; outer++)
      {
          Point p = pts[outer];
          
          Arrays.sort(tbs, p.SLOPE_ORDER);
          
          double slope = p.slopeTo(tbs[0]);
          int count = 0;
          boolean newslope = true;
          
          int i;
          for (i = 0; i < entries;) {
              if ((p.slopeTo(tbs[i]) == slope) || newslope) {
                  count++;
                  i++;
                  newslope = false;
              } else {
                  if (count >= 3)
                    dumpNdraw(p, tbs, i-count, i);
                  
                  count = 0;
                  newslope = true;
                  slope = p.slopeTo(tbs[i]);
              } // else
          } // for
          if (count >= 3) 
            dumpNdraw(p, tbs, i-count, i);
      }
   }
}