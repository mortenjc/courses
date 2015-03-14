import org.junit.*;
//import static org.junit.Assert.*;

public class SolverTest {
  
  int [][] boardA = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
  int [][] boardB = {{8, 7, 6}, {3, 4, 5}, {6, 7, 8}};
  int [][] boardC = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
  int [][] boardD = {{0, 1, 2, 9},  {3, 4, 5,  10}, {6, 8, 7, 11}, {12, 13, 14, 15}};
  int [][] boardE = {{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}, {4, 8, 12, 0}};
  int [][] boardFinal = {{1, 4, 7}, {2, 5, 8}, {3, 6, 0}};
  Board a3 = new Board(boardA);
  Board b3 = new Board(boardB);
  Board c3 = new Board(boardC);
  Board d4 = new Board(boardD);
  Board e4 = new Board(boardE);
  Board fin = new Board(boardFinal);
  String notboard = new String();
  
  
  @Test
  public void testPQ()
  {
        
  }  
}  