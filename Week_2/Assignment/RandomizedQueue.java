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
 *  % java Deque
 *
----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] a;

  // construct an empty randomized queue
  public RandomizedQueue() {
    a = new Item[1];
    a[0] = null;
  }

  private void resize(int capacity) {
    Item[] copy = new Item[capacity];
    for (int i = 0; i < a.length; i++) copy[i] = a[i];
    a = copy;
  }

  // is the randomized queue empty?
  public boolean isEmpty() { return (a.length == 1) && (a[0] == null); }

  // return the number of items on the randomized queue
  public int size() {
    if ((a.length == 1) && (a[0] == null)) return 0;
    else return a.length;
  }

  // add the item
  public void enqueue(Item item) {
    int s = size();
    int i = StdRandom.uniform(0, s);
  }

}

// public Item dequeue()                    // remove and return a random item
// public Item sample()                     // return a random item (but do not remove it)
// public Iterator<Item> iterator()         // return an independent iterator over items in random order
// public static void main(String[] args)   // unit testing (optional)
