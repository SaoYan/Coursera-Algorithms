/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       9/06/2018
 *  Last updated:  9/06/2018
 *
 *  Compilation:   javac GenericStackLinkedList.java
 *  Execution:     java GenericStackLinkedList < test.txt
 *
 *  Stack - linked-list implementation
 * Using Java generics
 *  % java GenericStackLinkedList < test.txt
 *  to
 *  be
 *  not
 *  that
 *  or
 *  be
 *
 *  Display all the strings in the stack:
 *  is
 *  to
 *
 *----------------------------------------------------------------*/
import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class GenericStackLinkedList<T> implements Iterable<T> {
  private class Node {
    private T item;
    private Node next;
  }

  private Node first = null;

  public boolean isEmpty() {
    return first == null;
  }

  public void push(T item) {
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
  }

  public T pop() {
    T item = first.item;
    first = first.next;
    return item;
  }

  public Iterator<T> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<T> {
    private Node current = first;
    public boolean hasNext() { return current != null;}
    public void remove() { throw new UnsupportedOperationException(); }
    public T next() {
      T item = current.item;
      current = current.next;
      return item;
    }
  }

  /*
  public void display() {
    System.out.println("\nDisplay all the strings in the stack:");
    Node node = first;
    while(node != null) {
      System.out.println(node.item);
      node = node.next;
    }
  }
  */

  public static void main(String[] args) {
    GenericStackLinkedList<String> stack = new GenericStackLinkedList<String>();

    while(!StdIn.isEmpty()) {
      String s = StdIn.readString();
      if(s.equals("-")) System.out.println(stack.pop());
      else              stack.push(s);
    }

    // stack.display();
    System.out.println("\nDisplay all the strings in the stack:");
    for (String s: stack)
      System.out.println(s);
  }
}
