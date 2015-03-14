import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PercolationStatsTest {
  @Test
  public void statInitTest() {
    PercolationStats ps = new PercolationStats(10,1);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void statInitErrorTest() {
    PercolationStats ps = new PercolationStats(10,0);
  }
}