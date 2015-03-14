import java.util.Comparator;

public class Point implements Comparable<Point> {
  
   public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();
  
   private int x, y;
  
   private class SlopeOrder implements Comparator<Point> {
       public int compare(Point p, Point q) {   
         double sl1 = slopeTo(p);
         double sl2 = slopeTo(q);
         if (p.compareTo(q) == 0)
           return 0;
         if (sl1 == Double.POSITIVE_INFINITY && sl2 == Double.POSITIVE_INFINITY)
           return 0;
           //
         if ((sl1 - sl2) < 0)
           return -1;
         else if ((sl1-sl2) == 0)
         {
           return 0;
         }
         else 
           return 1;
       }
   }
 
   public Point(int newx, int newy) {             // construct the point (x, y)
     this.x = newx;
     this.y = newy;
   }

   public void draw() {                           // draw this point
     StdDraw.point(x, y);
   }
   
   public   void drawTo(Point that) {   // draw line segment from this to that
     StdDraw.line(this.x, this.y, that.x, that.y);
   }
   
   public String toString() {                     // string representation
      String pt = "("+x+", "+y+")";
      return pt;
   }

   public int compareTo(Point that) { // is this pt smaller than that point?
      int diff = this.y - that.y;
      if (diff != 0)
        return diff;
      else 
        return (this.x - that.x);
   }
   
   public double slopeTo(Point that) {  // the slope between this point and that 
      int dx = that.x - this.x;
      int dy = that.y - this.y;
      
      if (dx != 0)
        return 1.0*dy/dx;
      else if (dy == 0)
        return Double.NEGATIVE_INFINITY;
      else
        return Double.POSITIVE_INFINITY;
   }
}