package Ques;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
        private int size;
        private Item[] items;
        
    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        items = (Item[]) new Object[1];     
    }
    

    // is the queue empty?
    public boolean isEmpty() {
        return size==0;
    }
    //return the number of items in the queue
    public int size() {
        return size;
    }
    //add the item
    public void enqueue(Item item) {
       if (item == null) throw new NullPointerException("Object to add is NULL!");
       if (size == items.length ) {
           resize(2*items.length);
       }
       items[size] = item;
       size++;    
    }
    //delete and return a random item
    public Item dequeue () {
        emptyCheck();
        int rand = StdRandom.uniform(size);
        Item elt = items[rand];
        if (size-1 == rand) {
            items[rand] = null;
        }
        else { 
            items[rand] = items[size-1];    //index will always be zero to length-1
            items[size-1] = null;
        }
        if (size> 0 && size == items.length/4) {
            resize(items.length/2);
        }
        size--;
        return elt;
    }
    //return (but do not delete) a random item
    public Item sample() {
        emptyCheck();
        int rand = StdRandom.uniform(size);
        return items[rand];
       
    }
    
    
    private void emptyCheck() {
       if (size == 0) {
           throw new NoSuchElementException();
       }
   }
    private void resize (int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }
    //return an independent iterator over items in random order
   public Iterator<Item> iterator() {
       return new RandomizedIterator();
   }
   
   private class RandomizedIterator implements Iterator<Item> {
       int eltsinlist = size;
       public boolean hasNext() {
           return eltsinlist != 0;
       }
       public Item next () { 
           if (!hasNext()) {
               throw new NoSuchElementException();
           }
           int rand = StdRandom.uniform(eltsinlist);
           Item itemtoreturn = items[rand];
           if (rand == eltsinlist - 1) {
               eltsinlist--;
               return itemtoreturn;
           }
           else {
               items[rand] = items[eltsinlist-1];
               items[eltsinlist-1] = itemtoreturn;
               eltsinlist--;
               return itemtoreturn;
           }

       }
       public void remove () {
           throw new UnsupportedOperationException();
       }
   }
    
    
    public static void main(String[] args) {
       RandomizedQueue<Integer> rqueue = new RandomizedQueue<Integer>();
       rqueue.enqueue(1);
       rqueue.enqueue(2);
       rqueue.enqueue(3);
       rqueue.enqueue(4);
       rqueue.enqueue(5);
       rqueue.enqueue(6);
       rqueue.enqueue(7);
       rqueue.enqueue(8);
       Iterator<Integer> it = rqueue.iterator();
       while (it.hasNext()) {
           int elt = it.next();
           System.out.println(elt + " ");
       }

   }
}
