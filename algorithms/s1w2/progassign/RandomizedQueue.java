import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private int N;
    private int capacity;
    private Item [] items;
    private StdRandom rand;
    
    public RandomizedQueue() {        // construct an empty randomized queue
        items = (Item []) new Object[1];
        capacity = 1;
        //rand.setSeed(1234);
    }
    
    public boolean isEmpty() {        // is the queue empty?
        return N == 0;
    }
    
    public int size() { return N; }   // return the number of items on the queue
    
    public void enqueue(Item item) {  // add the item
        if (item == null)
            throw new NullPointerException();
        if (isAboveThreshold())
            resize(capacity*2);
        items[N] = item;
        N++;
    }
    
    public Item dequeue() {           // delete and return a random item
        if (isEmpty())
            throw new NoSuchElementException();
        if (isBelowThreshold())
            resize(capacity/2);
        int index = rand.uniform(N);
        Item val = items[index];
        for (int i = index+1; i < N; i++)
            items[i-1] = items[i];
        N--;
        return val;
    }
    
    public Item sample() {            // return (but not delete) a random item
        if (isEmpty())
            throw new NoSuchElementException();
        int index = rand.uniform(N);
        return items[index];
    }
    
    public Iterator<Item> iterator() { // return an independent iterator
        return new RandomizedQueueIterator();
    }
    
    
    // Iterator for RandomizedQueue objects
    private class RandomizedQueueIterator implements Iterator<Item> {
   
        private int current = 0;
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() { 
            if (!hasNext())
                throw new NoSuchElementException();
            Item ret = items[current];
            current++;
            return ret; 
        }
        public boolean hasNext() { 
            return !(current == N); 
        }
    }
    
    private boolean isAboveThreshold()
    { return (2*N >= capacity); }
    
    private boolean isBelowThreshold()
    { return (4*N <= capacity); }
    
    private void resize(int newcapacity)
    {
        capacity = newcapacity;
        Item [] newarray = (Item []) new Object[capacity];
        for (int i = 0; i < N; i++)
            newarray[i] = items[i];
        items = newarray;
    }
}