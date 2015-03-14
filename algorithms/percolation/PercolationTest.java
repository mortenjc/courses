import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PercolationTest {
  @Test
  public void percolationInitTest() {
    Percolation pc = new Percolation(100);
    assertEquals("Initial disconnect", false, pc.isOpen(1,1));
    assertEquals("Initial disconnect", false, pc.isOpen(100,100));
  }
  
  @Test
  public void percolationOpenTest() {
    Percolation pc = new Percolation(100);
    assertEquals("Initial close", false, pc.isOpen(5,100));
    pc.open(5,100);
    assertEquals("Initial close", true, pc.isOpen(5,100));
  }

  @Test
  public void percolatesVerticalTest() {
    Percolation pc = new Percolation(3);
    assertEquals("Initial no percolation", false, pc.percolates());
    pc.open(1,2);
    assertEquals("open (1,2) no percolation", false, pc.percolates());
    pc.open(2,2);
    assertEquals("open (2,2) no percolation", false, pc.percolates());
    pc.open(3,2);
    assertEquals("open (3,2) percolation!!!", true, pc.percolates());    
  }
  
  @Test
  public void percolatesDiagonalTest() {
    Percolation pc = new Percolation(3);
    assertEquals("Initial no percolation", false, pc.percolates());
    pc.open(1,1);
    assertEquals("open (1,1) no percolation", false, pc.percolates());
    pc.open(2,2);
    assertEquals("open (2,2) no percolation", false, pc.percolates());
    pc.open(3,3);
    assertEquals("open (3,3) no percolation", false, pc.percolates());    
  }

  @Test
  public void isFullVerticalTest() {
    Percolation pc = new Percolation(100);
    assertEquals("Initial no filled sited", false, pc.isFull(1,1));
    pc.open(1,1);
    assertEquals("(1,1) is now full", true, pc.isFull(1,1));
    pc.open(2,1);
    assertEquals("2,1) is now full", true, pc.isFull(2,1));
    pc.open(2,2);
    assertEquals("(2,2) is now full", true, pc.isFull(2,2));
  }  

  @Test (expected = IndexOutOfBoundsException.class)
  public void outOfBoundsTest() {
    Percolation pc = new Percolation(100);
    pc.open(101,1);
  }    

  @Test
  public void printTest() {
    Percolation pc = new Percolation(3);
    assertEquals("Initial no filled sited", false, pc.isFull(1,1));
    pc.open(1,1);    
    pc.open(2,2);
    pc.open(3,3);
    System.out.println("--------------");
     for (int i = 1; i < 3 + 1; i++)
     {
       for (int j = 1; j < 3 + 1; j++)
          if (pc.isOpen(i,j))
            System.out.print("O");
          else
            System.out.print("X");
        System.out.println("");
     }
             
  }  
  
  
}