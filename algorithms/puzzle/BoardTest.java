import org.junit.*;
import static org.junit.Assert.*;

public class BoardTest {
  
  int [][] boardA = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
  int [][] boardB = {{8, 7, 6}, {3, 4, 5}, {6, 7, 8}};
  int [][] boardC = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
  int [][] boardD = {{0, 1, 2, 9},  {3, 4, 5,  10}, {6, 8, 7, 11}, {12, 13, 14, 15}};
  int [][] boardE = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
  int [][] boardFinal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
  Board a3 = new Board(boardA);
  Board b3 = new Board(boardB);
  Board c3 = new Board(boardC);
  Board d4 = new Board(boardD);
  Board e4 = new Board(boardE);
  Board fin = new Board(boardFinal);
  String notboard = new String();
  
  
  @Test
  public void testArrays()
  {
      assertEquals("final[0][0] == 1", 1, boardFinal[0][0]); 
      assertEquals("final[2][0] == 7", 7, boardFinal[2][0]);
      assertEquals("final[2][2] == 0", 0, boardFinal[2][2]);
  }  
  
  @Test
  public void testDimension()
  {
    assertTrue("Dimension 3", 3==a3.dimension());
    assertTrue("Dimension 4", 4==d4.dimension());
    assertFalse("a3 != b4", a3.dimension() == d4.dimension());
  }

  @Test
  public void testEqual()
  {
    assertTrue(" a3 == c3", a3.equals(c3));
    assertFalse(" a3 != b3", a3.equals(b3));
    assertFalse(" a3 != d4", a3.equals(d4));
    assertFalse(" notboard != a3", a3.equals(notboard));
  }
  
  
  @Test
  public void testGoal()
  {
    assertTrue("Check final board 3x3", fin.isGoal());
    assertTrue("Check final board 4x4", e4.isGoal());
    assertFalse("Check d4 ", d4.isGoal());
  }
  
  @Test
  public void testHamming()
  {
    assertEquals("Final hamming == 0", 0, fin.hamming());
    assertEquals("a3 hamming == 8", 8, a3.hamming());  
    assertEquals("e4 hamming == 0", 0, e4.hamming());
    assertEquals("d4 hamming == 15", 15, d4.hamming());
  }
  
  @Test 
  public void testManhattan()
  {
    assertEquals("Final Manhattan == 0", 0, fin.manhattan());
    assertEquals("a3 == 0", 12, a3.manhattan());
  }
  
  @Test 
  public void testNeighbors()
  {
    Queue<Board> myq = (Queue<Board>)a3.neighbors();
    assertEquals("a3 has 2 neighbors", 2, myq.size());
    Queue<Board> myq2 = (Queue<Board>)b3.neighbors();
    assertEquals("b3 (invalid board) has 0 neighbors", 0, myq2.size());
    
  }
  
}