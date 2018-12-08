/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       12/07/2018
 *  Last updated:  12/07/2018
 *
 *  Compilation:
 *  Execution:
 *
 *
 *  % java
 *
 *----------------------------------------------------------------*/

 import edu.princeton.cs.algs4.StdIn;
 import edu.princeton.cs.algs4.StdOut;
 import java.util.Iterator;

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
     if (isEmpty()) {
       first = new Node();
       last = first;
       first.item = item;
       first.next = null;
       first.previous = null;
       last.next = null;
       last.previous = null;
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
     if (isEmpty()) {
       first = new Node();
       last = first;
       first.item = item;
       first.next = null;
       first.previous = null;
       last.next = null;
       last.previous = null;
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
     if (isEmpty()) throw new IllegalStateException("Unable to remove items from empty deque!";)
     Item item = first.item;
     Node oldFirst = first;
     first = oldFirst.next;
     first.previous = null;
     oldFirst.next = null;
     size --;
     return item;
   }

   // remove and return the item from the end
   public Item removeLast() {
     if (isEmpty()) throw new IllegalStateException("Unable to remove items from empty deque!";)
     Item item = last.item;
     Node oldLast = last;
     last = oldLast.previous;
     last.next = null;
     oldLast.previous = null;
     size --;
     return item;
   }

   // return an iterator over items in order from front to end
   public Iterator<Item> iterator() { return DequeIterator; }

   private class DequeIterator implements Iterator<Item> {
     private Node current = first;

     public boolean hasNext() { return  first != null;}

     public void remove() { throw new UnsupportedOperationException("This operation is not supported!"); }

     public Item next() {
       Item item = current.item;
       current = current.next;
       return item;
     }
   }

   // unit testing
   public static void main(String[] args) {
    Deque<Integer> deque = new Deque();
    int first = deque.removeFirst();
    int last = deque.removeLast();
   }
 }
