/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       12/07/2018
 *  Last updated:  12/07/2018
 *
 *  Compilation: javac Deque.java
 *  Execution: java Deque
 *
 *  Deque: linked-list implementation
 *
 *  % java Deque
 *  true
 *  0
 *
 *  4123
 *  4
 *
 *  4
 *  3
 *  12
 *  2
----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private class Node {
    private Item item;
    private Node next;
    private Node previous;
  }

  private Node first, last;
  private int size;

  // construct an empty deque
  public Deque() {
    first = null;
    last = null;
    size = 0;
  }

   // is the deque empty?
   public boolean isEmpty() {
     return first == null;
   }

   // return the number of items on the deque
   public int size() {
     return size;
   }

   // add the item to the front
   public void addFirst(Item item) {
     if (item == null) throw new IllegalArgumentException("Cannot add null item!");
     if (isEmpty()) {
       first = new Node();
       first.item = item;
       first.next = null;
       first.previous = null;
       last = first;
     }
     else {
       Node newFirst = new Node();
       newFirst.item = item;
       newFirst.next = first;
       newFirst.previous = null;
       first.previous = newFirst;
       first = newFirst;
     }
     size ++;
   }

   // add the item to the end
   public void addLast(Item item) {
     if (item == null) throw new IllegalArgumentException("Cannot add null item!");
     if (isEmpty()) {
       last = new Node();
       last.item = item;
       last.next = null;
       last.previous = null;
       first = last;
     }
     else {
       Node newLast = new Node();
       newLast.item = item;
       newLast.next = null;
       newLast.previous = last;
       last.next = newLast;
       last = newLast;
     }
     size ++;
   }

   // remove and return the item from the front
   public Item removeFirst() {
     if (isEmpty()) throw new NoSuchElementException("Unable to remove items from empty deque!");
     Item item = first.item;
     Node oldFirst = first;
     try {
       first = oldFirst.next;
       first.previous = null;
       oldFirst.next = null;
     }
     catch(NullPointerException e) {
       first = null;
       last = null;
     }
     size --;
     return item;
   }

   // remove and return the item from the end
   public Item removeLast() {
     if (isEmpty()) throw new NoSuchElementException("Unable to remove items from empty deque!");
     Item item = last.item;
     Node oldLast = last;
     try {
       last = oldLast.previous;
       last.next = null;
       oldLast.previous = null;
     }
     catch(NullPointerException e) {
       first = null;
       last = null;
     }
     size --;
     return item;
   }

   // return an iterator over items in order from front to end
   public Iterator<Item> iterator() { return new DequeIterator(); }

   private class DequeIterator implements Iterator<Item> {
     private Node current = first;

     public boolean hasNext() { return  current != null;}

     public void remove() { throw new UnsupportedOperationException("This operation is not supported!"); }

     public Item next() {
       Item item = current.item;
       current = current.next;
       return item;
     }
   }

   // unit testing
   public static void main(String[] args) {
    Deque<Integer> deque = new Deque<Integer>();
    int removed;

    // empty -> non-empty -> empty
    deque.addLast(1);
    removed = deque.removeFirst();
    StdOut.println(deque.isEmpty());
    StdOut.println(deque.size());
    StdOut.print('\n');

    // add
    deque.addFirst(1);
    deque.addLast(2);
    deque.addLast(3);
    deque.addFirst(4);
    for (int a : deque) StdOut.print(a);
    StdOut.print('\n');
    StdOut.println(deque.size());
    StdOut.print('\n');

    // remove
    removed = deque.removeFirst();
    StdOut.println(removed);
    removed = deque.removeLast();
    StdOut.println(removed);
    for (int a : deque) StdOut.print(a);
    StdOut.print('\n');
    StdOut.println(deque.size());
    StdOut.print('\n');
   }
}
