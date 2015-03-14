import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SAPTest
{
  @Test
  public void testConstructor() {
    In in = new In("wordnet/digraph1.txt");
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
  }
  
  @Test (expected = IndexOutOfBoundsException.class)
  public void testForLoopException() {
    In in = new In("loop.txt");
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
  }
  
  @Test (expected = IndexOutOfBoundsException.class)
  public void testForIndexOutofBounds() {
    In in = new In("vee.txt");
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    int length = sap.length(7,0);
    System.out.println("length" + length);
    assertEquals("invalid length", -1, length);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testForIndexOutofBounds1Digraph1() {
    In in = new In("wordnet/digraph1.txt");
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    
    int length = sap.length(-1,0);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testForIndexOutofBounds2Digraph1() {
    In in = new In("wordnet/digraph1.txt");
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    
    int length = sap.length(13,0);
  } 
  
  @Test
  public void testLength1() {
    In in = new In("vee.txt");
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    int length = sap.length(0, 2);
    assertEquals("length (2 - 0) == 2", 2, length);
  }
  
  @Test
  public void testAncestorVee() {
    In in = new In("vee.txt");
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    int ancestor = sap.ancestor(2, 3);
    assertEquals("ancestor (2,3) == 1", 1, ancestor);
    
    ancestor = sap.ancestor(3, 2);
    assertEquals("ancestor (3,2) == 1", 1, ancestor);
  }
  
   // Test against known digraph
   @Test
   public void testDigraph1Txt() {
     In in = new In("wordnet/digraph1.txt");
     Digraph G = new Digraph(in);
     SAP sap = new SAP(G);
     int length = sap.length(8, 0);
     assertEquals("length(8,0) == 3", 3, length);
     
     length = sap.length(2,6);
     assertEquals("length(2,6) == -1", -1, length);
   } 
   
   @Test
   public void testDigraph3Txt() {
     In in = new In("wordnet/digraph3.txt");
     Digraph G = new Digraph(in);
     SAP sap = new SAP(G);
   }   
      
   @Test
   public void testambiguousancestor() {
     In in = new In("wordnet/digraph-ambiguous-ancestor.txt");
     Digraph G = new Digraph(in);
     SAP sap = new SAP(G);
     int length = sap.length(8,3);
     assertEquals("length(8,3) == -1", -1, length);
   }  
   
}