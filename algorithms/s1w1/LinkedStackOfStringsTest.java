import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LinkedStackOfStringsTest
{
   @Test
   public void emptyTest() {
       LinkedStackOfStrings ls = new LinkedStackOfStrings();
       assertEquals("initial stack is empty", true, ls.isEmpty());
   }
   
   @Test
   public void pushPopTest() {
       LinkedStackOfStrings ls = new LinkedStackOfStrings();
       assertEquals("initial stack is empty", true, ls.isEmpty());
       ls.push("Hello");
       assertEquals("one item in stack", false, ls.isEmpty());
       ls.pop();
       assertEquals("initial stack is empty", true, ls.isEmpty());
   }
}