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
 *
 *  4
 *  2
 *  1
 *  3
 *
 *  4

 *  2
 *  1
 *  3

 *  3

 *  2
 *  1
 *
 *  2
 *  1
 *----------------------------------------------------------------*/

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
     if (item == null) throw new IllegalArgumentException("Cannot add null item!");
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
     if (isEmpty()) throw new NoSuchElementException("Unable to remove items from empty deque!");
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
     if (isEmpty()) throw new NoSuchElementException("Unable to remove items from empty deque!");
     Item item = last.item;
     Node oldLast = last;
     last = oldLast.previous;
     last.next = null;
     oldLast.previous = null;
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

   public void displayAll(boolean reverse) {
     StdOut.println('\n');
     if (reverse) {
       Node node = last;
       while (node != null) {
         StdOut.println(node.item);
         node = node.previous;
       }
     }
     else {
       Node node = first;
       while (node != null) {
         StdOut.println(node.item);
         node = node.next;
       }
     }
     StdOut.println('\n');
   }

   // unit testing
   public static void main(String[] args) {
    Deque<Integer> deque = new Deque<Integer>();

    deque.addFirst(1);
    deque.addFirst(2);
    deque.addLast(3);
    deque.addFirst(4);
    deque.displayAll(false);

    int removed;

    removed = deque.removeFirst();
    StdOut.println(removed);
    deque.displayAll(false);

    removed = deque.removeLast();
    StdOut.println(removed);
    deque.displayAll(false);

    for (int a : deque) {
      StdOut.println(a);
    }
   }
 }
