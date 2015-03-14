import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DequeTest
{
   @Test
   public void testConstructor() {
      Deque<Integer> d = new Deque<Integer>();
      assertEquals("Initial deque must be empty", true, d.isEmpty());
      assertEquals("Initial size is 0", 0, d.size());
   }
   
   @Test
   public void testAddFirst() {
      Deque<Integer> d = new Deque<Integer>();
      d.addFirst(0);
      assertEquals("Initial size is 1", 1, d.size());
      assertEquals("No longer empty", false, d.isEmpty());
      for (int i = 1; i < 10; i++) 
          d.addFirst(i);
      assertEquals("Initial size is 10", 10, d.size());
      assertEquals("No longer empty", false, d.isEmpty());
   }   
   
   @Test
   public void testAddLast() {
      Deque<Integer> d = new Deque<Integer>();
      d.addLast(0);
      assertEquals("Initial size is 1", 1, d.size());
      assertEquals("No longer empty", false, d.isEmpty());
      for (int i = 1; i < 10; i++) 
          d.addLast(i);
      assertEquals("Initial size is 10", 10, d.size());
      assertEquals("No longer empty", false, d.isEmpty());
   } 
   
   @Test
   public void testAddFirstRemoveFirst() {
      Deque<Integer> d = new Deque<Integer>();
      int val = 5;
      d.addFirst(val);
      assertEquals("Initial size is 1", 1, d.size());
      assertEquals("No longer empty", false, d.isEmpty());
      int ret = d.removeFirst();
      assertEquals("Return 5", val, ret);
      assertEquals("Initial deque must be empty", true, d.isEmpty());
   }   
   
      @Test
      public void testAddFirstRemoveLast() {
      Deque<Integer> d = new Deque<Integer>();
      int val = 5;
      d.addFirst(val);
      assertEquals("Initial size is 1", 1, d.size());
      assertEquals("No longer empty", false, d.isEmpty());
      int ret = d.removeLast();
      assertEquals("Return matches?", val, ret);
   }  
      
   @Test
   public void testAddMany() {
      Deque<Integer> d = new Deque<Integer>();
      int N = 100;
      for (int i = 0; i < N; i++) 
          d.addFirst(i);
      assertEquals("Check size", N, d.size());
      assertEquals("No longer empty", false, d.isEmpty());
      for (int i = 0; i < N; i++)
      {
        int val = d.removeLast();
        assertEquals("Test", i, val);
      }     
   } 
   
   @Test
   public void testIterator() {
      Deque<Integer> d = new Deque<Integer>();
      int N = 100;
      int count=0;
      int val;
      for (int i = 0; i < N; i++) 
          d.addFirst(i);
      
       for (Integer x : d)
       {
           count++;
       }
       assertEquals("Test", count, N);
   }  
   
   @Test
   public void testNonEmptyEmptyCycle() {
      Deque<Integer> d = new Deque<Integer>();
      d.addFirst(5);
      assertEquals("Assert non empty", false, d.isEmpty());
      int a = d.removeFirst();
      assertEquals("Assert empty", true, d.isEmpty());
      d.addFirst(7);
      assertEquals("Assert non empty", false, d.isEmpty());
   }
}