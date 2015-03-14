import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class WordNetTest
{
  @Test
  public void testConstructor() {
    WordNet wn = new WordNet("mjcsyn.txt", "mjchyp.txt");
 
    assertEquals("act is a noun", true, wn.isNoun("act"));
    assertEquals("human_action is a noun", true, wn.isNoun("human_action"));
    assertEquals("change is a noun", true, wn.isNoun("change"));
    assertEquals("gylle is not a noun", false, wn.isNoun("gylle"));
  }
   
  @Test 
  public void testDistances() {
    WordNet wn = new WordNet("mjcsyn.txt", "mjchyp.txt");
    int distance;
    
    distance = wn.distance("act", "act");
    assertEquals("d(act,act) = 0", 0, distance);
    
    distance = wn.distance("act", "group_action");
    assertEquals("d(act,group_action) = 1", 1, distance);
 
    distance = wn.distance("act", "change");
    assertEquals("d(act,change) = 3", 3, distance);    
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testForLoop() {
    WordNet wn = new WordNet("mjcsyn.txt", "loop.txt");
  }
     
  @Test
  public void testSap() {
    WordNet wn = new WordNet("mjcsyn.txt", "mjchyp.txt");
 
    // First test for unconnected cases
    String sap = wn.sap("miracle", "group_action");
    assertEquals("ancestor(miracle,group_action) == event", true, sap.equals("event"));
    
    sap = wn.sap("group_action", "miracle");
    assertEquals("ancestor(group_action,miracle) == event", true, sap.equals("event"));
    
    // Then check self case
    sap = wn.sap("act", "act");
    System.out.println("sap: "+ sap);
    assertEquals("ancestor(act,act) == act", true, sap.equals("act"));
    
    // Then check connected case
    sap = wn.sap("act", "event");
    assertEquals("ancestor(act,event) == event", true, sap.equals("event"));
    
    sap = wn.sap("event", "act");
    assertEquals("ancestor(event,act) == event", true, sap.equals("event"));
 
  }
  
  @Test
  public void testKnownSap() {
    WordNet wn = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
 
    // First test for unconnected cases
    String sap = wn.sap("miracle", "group_action");
    assertEquals("ancestor(miracle,group_action) == event", true, sap.equals("event"));
    
    sap = wn.sap("group_action", "miracle");
    assertEquals("ancestor(group_action,miracle) == event", true, sap.equals("event"));
    
    // Then check self case
    sap = wn.sap("act", "act");
    System.out.println("sap: "+ sap);
    assertEquals("ancestor(act,act) == act", true, sap.equals("act"));
    
    // Then check connected case
    sap = wn.sap("act", "event");
    assertEquals("ancestor(act,event) == event", true, sap.equals("event"));
    
    sap = wn.sap("event", "act");
    assertEquals("ancestor(event,act) == event", true, sap.equals("event"));
    
    sap = wn.sap("shawm", "element");
    assertEquals("ancestor(shawm,element) == 'object physical_object'", true, sap.equals("object physical_object"));
  }
}