import org.junit.*;
import static org.junit.Assert.*;

public class BaseballEliminationTest {
  
  @Test
  public void constructorValid() {
    BaseballElimination bb = new BaseballElimination("teams4.txt");
    
    assertEquals("teams4 has 4 teams", 4, bb.numberOfTeams());
  }
  
  @Test
  public void reportWins() {
    BaseballElimination bb = new BaseballElimination("teams4.txt");
    
    assertEquals("Atlanta won 83", 83, bb.wins("Atlanta"));
    assertEquals("Philadelphia won 80", 80, bb.wins("Philadelphia"));
    assertEquals("New_York won 78", 78, bb.wins("New_York"));
    assertEquals("Montreal won 77", 77, bb.wins("Montreal"));    
  }
  
  @Test
  public void reportLost() {
    BaseballElimination bb = new BaseballElimination("teams4.txt");
    
    assertEquals("Atlanta lost 71", 71, bb.losses("Atlanta"));
    assertEquals("Philadelphia lost 79", 79, bb.losses("Philadelphia"));
    assertEquals("New_York lost 78", 78, bb.losses("New_York"));
  }
  
  @Test
  public void reportLeft() {
    BaseballElimination bb = new BaseballElimination("teams4.txt");
    
    assertEquals("Atlanta left 8", 8, bb.remaining("Atlanta"));
    assertEquals("Philadelphia left 3", 3, bb.remaining("Philadelphia"));
    assertEquals("New_York lost 6", 6, bb.remaining("New_York"));
  }
  
  @Test
  public void reportAgainst() {
    BaseballElimination bb = new BaseballElimination("teams4.txt");
    
    assertEquals("Atl - Atl", 0, bb.against("Atlanta", "Atlanta"));
    assertEquals("Atl - NY", 6, bb.against("Atlanta", "New_York"));
    assertEquals("Atl - NY", 6, bb.against("New_York", "Atlanta"));
  }
   
  @Test (expected = IllegalArgumentException.class )
  public void unknownNameWins()  {
    BaseballElimination bb = new BaseballElimination("teams4.txt");
    assertEquals("Atlanta won 83", 83, bb.wins("Atl"));
  }
  
  @Test (expected = IllegalArgumentException.class )
  public void unknownNameLosses()  {
    BaseballElimination bb = new BaseballElimination("teams4.txt");
    assertEquals("Atlanta won 83", 83, bb.losses("Atl"));
  }
  
  @Test (expected = IllegalArgumentException.class )
  public void unknownNameLleft()  {
    BaseballElimination bb = new BaseballElimination("teams4.txt");
    assertEquals("Atlanta won 83", 83, bb.remaining("Atl"));
  }
  
  @Test (expected = IllegalArgumentException.class )
  public void unknownNameAgainst()  {
    BaseballElimination bb = new BaseballElimination("teams4.txt");
    assertEquals("Atlanta won 83", 83, bb.against("Atl", "New_york"));
  } 
  
  @Test
  public void reportIsEliminated() {
    BaseballElimination bb = new BaseballElimination("teams4.txt");
    
    assertEquals("Atlanta not elim", false, bb.isEliminated("Atlanta"));
    assertEquals("Philly elim", true, bb.isEliminated("Philadelphia"));
    assertEquals("NY not elim", false, bb.isEliminated("New_York"));
    assertEquals("Montreal elim", true, bb.isEliminated("Montreal"));
  }
 
    @Test
  public void reportIsEliminated4a() {
    BaseballElimination bb = new BaseballElimination("teams4a.txt");
    
    assertEquals("!CIA", false, bb.isEliminated("CIA"));
    assertEquals("!Bin_Ladin", true, bb.isEliminated("Bin_Ladin"));
    assertEquals("Ghaddafi", true, bb.isEliminated("Ghaddafi"));
    assertEquals("!Obama", false, bb.isEliminated("Obama"));
  }
    
        
    @Test
  public void certificateElimination4a() {
    BaseballElimination bb = new BaseballElimination("teams4a.txt");
    
    Bag<String> b = new Bag<String>();
      for (String s :bb.certificateOfElimination("Ghaddafi")) {
        System.out.println(s);
        b.add(s);
      }
    assertEquals("two in cut", 2, b.size());
  }
}