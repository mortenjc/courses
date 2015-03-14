import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.NoSuchElementException;

public class RandomizedQueueTest
{
   @Test
   public void testConstructor() {
      RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
      assertEquals("Initial Queue must be empty", true, d.isEmpty());
      assertEquals("Initial size is 0", 0, d.size());
   }
   
   @Test (expected = NoSuchElementException.class)
   public void sampleEmptyQueue() {
      RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
      d.sample();
   }

   @Test (expected = NoSuchElementException.class)
   public void dequeueEmptyQueue() {
      RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
      d.dequeue();
   }

   @Test (expected = NullPointerException.class)
   public void enqueueNullObject() {
      RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
      d.enqueue(null);
   }
   
   @Test 
   public void sizeAfterEncDec() {
      RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
      int N = 100;
      
      for (int i = 0; i < N; i++)
          d.enqueue(N);
      assertEquals("Size == N?", N, d.size());
      for (int i = 0; i < N; i++)
          d.sample();
      assertEquals("Size == N?", N, d.size());
      for (int i = 0; i < N; i++)
          d.dequeue();
      assertEquals("Size == 0?", 0, d.size());
   }
   
      @Test 
   public void dequeueCheck() {
      RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
      int N = 1000;
      int res = 0;
      for (int i = 0; i < N; i++)
          d.enqueue(i);
   
      for (int i = 0; i < N; i++)
          res += d.dequeue();
      assertEquals("Sum ", N*(N-1)/2, res);
   }
   
   @Test 
   public void randomSample() {
      RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
      int N = 10;
      int val;
      StdOut.println("Start test");
      for (int i = 0; i < N; i++)
      {
          d.enqueue(N-i);
      }
      for (int i = 0; i < N; i++)
      {
          val = d.sample();
      }
      
   }
}