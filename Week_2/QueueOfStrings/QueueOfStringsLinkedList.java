/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       9/06/2018
 *  Last updated:  9/06/2018
 *
 *  Compilation:   javac QueueOfStringsLinkedList.java
 *  Execution:     java QueueOfStringsLinkedList < test.txt
 *
 *  Queue of strings - linked-list implementation
 *  % java QueueOfStringsLinkedList < test.txt
 *  to
 *  be
 *  or
 *  not
 *  to
 *  be
 *
 *  Display all the strings in the stack:
 *  that
 *  is
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueOfStringsLinkedList {
  private class Node {
    private String item;
    private Node next;
  }

  private Node first=null, last=null;

  public boolean isEmpty() {
    return first == null;
  }

  public void enqueue(String item) {
    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    if (isEmpty()) first = last;
    else           oldLast.next = last;
  }

  public String dequeue() {
    String item = first.item;
    first = first.next;
    if (isEmpty()) last = null;
    return item;
  }

  public void display() {
    System.out.println("\nDisplay all the strings in the queue:");
    Node node = first;
    while(node != null) {
      System.out.println(node.item);
      node = node.next;
    }
  }

  public static void main(String[] args) {
    QueueOfStringsLinkedList queue = new QueueOfStringsLinkedList();

    while(!StdIn.isEmpty()) {
      String s = StdIn.readString();
      if(s.equals("-")) System.out.println(queue.dequeue());
      else              queue.enqueue(s);
    }

    queue.display();
  }
}
