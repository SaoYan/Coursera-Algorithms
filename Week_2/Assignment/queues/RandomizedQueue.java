/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       12/10/2018
 *  Last updated:  12/10/2018
 *
 *  Compilation: javac RandomizedQueue.java
 *  Execution: java RandomizedQueue
 *
 *  Randomized queue: resizing-array implementation
 *
 *  % java RandomizedQueue
 *  true
 *  false
 *  1
 *  20
 *  true
 *  0
 *
 *  (random order of 5 6 7 8)
 *  4
 *
 *  (random dequeue)
 *  3
 *  (random iterator)
 *  (random sample)
 *  3
 *  (random iterator)
----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] a;
  private int n;

  // construct an empty randomized queue
  public RandomizedQueue() {
    a = (Item[]) new Object[1];
    n = 0;
  }

  // is the randomized queue empty?
  public boolean isEmpty() { return n == 0; }

  // return the number of items on the randomized queue
  public int size() { return n; }

  // resize the array
  private void resize(int capacity) {
    Item[] copy = (Item[]) new Object[capacity];
    for (int i = 0; i < n; i++) copy[i] = a[i];
    a = copy;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) throw new IllegalArgumentException("Cannot add null item!");
    if (n == a.length) resize(2 * a.length);
    a[n++] = item;
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException("Unable to dequeue from empty deque!");
    int idx = StdRandom.uniform(0, n);
    Item item = a[idx];
    for (int i = idx + 1; i < n; i++) a[i-1] = a[i];
    n--;
    if (n > 0 && n == a.length / 4) resize(a.length / 2);
    return item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if (isEmpty()) throw new NoSuchElementException("Unable to sample from empty deque!");
    int idx = StdRandom.uniform(0, n);
    return a[idx];
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() { return new RandomizedQueueIterator(); }

  private class RandomizedQueueIterator implements Iterator<Item> {
    private final Item[] copy = (Item[]) new Object[n];
    private int current = 0;

    public RandomizedQueueIterator() {
      for (int i = 0; i < n; i++) copy[i] = a[i];
      StdRandom.shuffle(copy);
    }

    public boolean hasNext() {
      if (copy.length != n) throw new ConcurrentModificationException("Error detected: the randomized queue is structurally modified before it is done iterating!");
      return current < n;
    }

    public void remove() { throw new UnsupportedOperationException("This operation is not supported!"); }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException("No next item to return!");
      if (copy.length != n) throw new ConcurrentModificationException("Error detected: the randomized queue is structurally modified before it is done iterating!");
      Item item = copy[current++];
      return item;
    }
  }

  public static void main(String[] args) {
    RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();

    // empty -> non-empty -> empty
    StdOut.println(randomizedQueue.isEmpty());
    randomizedQueue.enqueue(20);
    StdOut.println(randomizedQueue.isEmpty());
    StdOut.println(randomizedQueue.size());
    StdOut.println(randomizedQueue.dequeue());
    StdOut.println(randomizedQueue.isEmpty());
    StdOut.println(randomizedQueue.size());
    StdOut.print('\n');

    // enqueue
    randomizedQueue.enqueue(5);
    randomizedQueue.enqueue(6);
    randomizedQueue.enqueue(7);
    randomizedQueue.enqueue(8);
    for (int item : randomizedQueue) StdOut.print(item);
    StdOut.print('\n');
    StdOut.println(randomizedQueue.size());
    StdOut.print('\n');

    // dequeue & sample
    StdOut.println(randomizedQueue.dequeue());
    StdOut.println(randomizedQueue.size());
    for (int item : randomizedQueue) StdOut.print(item);
    StdOut.print('\n');
    StdOut.println(randomizedQueue.sample());
    StdOut.println(randomizedQueue.size());
    for (int item : randomizedQueue) StdOut.print(item);
    StdOut.print('\n');

    // multiple iterator
  }
}
