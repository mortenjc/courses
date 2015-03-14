import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node pre, post;
    private int N;
    
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    
    public Deque() {                  // construct an empty deque
        pre  = new Node();
        post = new Node();
        pre.next  = post;
        post.prev = pre;
    }
    
    public boolean isEmpty() {        // is the deque empty?
        return size() == 0;
    }
    
    public int size() {               // return the number of items on the deque
        return N;
    }
    
    public void addFirst(Item item) { // insert the item at the front
        if (item == null)
            throw new NullPointerException();
        
        Node first = pre.next;
        Node x = new Node();
        x.item = item;
        x.prev = pre;
        x.next = first;
        first.prev = x;
        pre.next = x;
        N++;
    }
    
    public void addLast(Item item) {  // insert the item at the end
       if (item == null)
           throw new NullPointerException();
       
       Node last = post.prev;
       Node x = new Node();
       x.item = item;
       x.next = post;
       x.prev = last;
       post.prev = x;
       last.next = x;
       N++;
    }
    
    public Item removeFirst() {       // delete and return the item at the front
        if (isEmpty())
            throw new NoSuchElementException();
        
        Item val = pre.next.item;
        pre.next = pre.next.next;
        pre.next.prev = pre;
        N--;
        return val;
    }
    
    public Item removeLast() {        // delete and return the item at the end
        if (isEmpty())
            throw new NoSuchElementException();
 
        Item val = post.prev.item;
        post.prev = post.prev.prev;
        post.prev.next = post;
        N--;
        return val;
    }
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = pre.next;
        private int index = 0;
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() { 
            if (!hasNext())
                throw new NoSuchElementException();
            current = current.next;
            index++;
            return current.item; 
        }
        public boolean hasNext() { 
            return index < N; 
        }
    }
    
}
