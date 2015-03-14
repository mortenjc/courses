import org.junit.*;
import static org.junit.Assert.*;

public class PointTest {
  
  @Test
  public void testCompare()
  {
    Point a = new Point(1,1);
    Point b = new Point(1,1);
    Point c = new Point(2,1);
    Point d = new Point(2,2);

    assertEquals("a == b", 0, a.compareTo(b));
    assertTrue("a < c", (a.compareTo(c) < 0) );
    assertTrue("c > a", (c.compareTo(a) > 0) );
    assertTrue("d > a", (d.compareTo(a) > 0) );
  }
  
  @Test
  public void testSlope()
  {
    Point a = new Point(1,1);
    Point b = new Point(1,1);
    Point c = new Point(2,1);
    Point d = new Point(2,2);
    Point e = new Point(3,1);
    assertEquals(" a a -Inf", true , a.slopeTo(a) == Float.NEGATIVE_INFINITY);
    assertEquals(" c d +Inf", true , c.slopeTo(d) == Float.POSITIVE_INFINITY);
    assertEquals(" a c 0", true , a.slopeTo(c) == 0.0);
    assertEquals(" a d != 0", true , a.slopeTo(d) != 0.0);
    assertEquals(" a d 1", true,  a.slopeTo(d) == 1.0);
    assertEquals(" d a 1", true,  d.slopeTo(a) == 1.0);
    assertEquals(" d e -11", true,  d.slopeTo(e) == -1.0);
  }

  @Test 
  public void testSlopeNegative()
  {
    Point a = new Point(-1,-1);
    Point b = new Point(-1,-1);
    Point c = new Point(-2,-1);
    Point d = new Point(-2,-2);
    Point e = new Point(-3,-1);
    assertEquals(" a a -Inf", true , a.slopeTo(a) == Float.NEGATIVE_INFINITY);
    assertEquals(" c d +Inf", true , c.slopeTo(d) == Float.POSITIVE_INFINITY);
    assertEquals(" a c 0", true , a.slopeTo(c) == 0.0);
    assertEquals(" a d != 0", true , a.slopeTo(d) != 0.0);
    assertEquals(" a d 1", true,  a.slopeTo(d) == 1.0);
    assertEquals(" d a 1", true,  d.slopeTo(a) == 1.0);
    assertEquals(" d e -11", true,  d.slopeTo(e) == -1.0);
  }   
  
  @Test
  public void testCompareSlope()
  {
    Point a = new Point(98,179);
    Point b = new Point(98,153);
    Point c = new Point(98,206);
    Point d = new Point(98,179);
    
    StdOut.println("Slope ab: " + a.slopeTo(b));
    StdOut.println("Slope ab: " + a.slopeTo(c));

    assertEquals("Slope ab == slope ac", 0, a.SLOPE_ORDER.compare(b,c));
    assertEquals("Compare identical", 0, a.SLOPE_ORDER.compare(d,d));
  }
  
}