import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class OutCastTest
{
  @Test
  public void testConstructor() {
    WordNet wn = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
    Outcast oc = new Outcast(wn);
    String[] nouns = In.readStrings("wordnet/outcast5.txt");
    
    String outcast = oc.outcast(nouns);
    System.out.println("outcast: " + outcast);
    assertEquals("outcast 'table'", true, outcast.equals("table"));
    
    nouns = In.readStrings("wordnet/outcast8.txt");
    outcast = oc.outcast(nouns);
    System.out.println("outcast: " + outcast);
    assertEquals("outcast 'bed'", true, outcast.equals("bed"));
    
  }

  @Test
  public void testKnownOutcasts() {
    WordNet wn = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
    Outcast oc = new Outcast(wn);
    String[] nouns = In.readStrings("outcast1.txt");
    String outcast = oc.outcast(nouns);
    System.out.println("outcast: " + outcast);
    assertEquals("outcast 'Turing'", true, outcast.equals("Turing"));    
  }  
  
}