/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       7/17/2018
 *  Last updated:  7/17/2018
 *
 *  Compilation:   javac StackOfStringsLinkedList.java
 *  Execution:     java StackOfStringsLinkedList < test.txt
 *
 *  % java StackOfStringsLinkedList < test.txt
 *
 *
 *
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackOfStringsLinkedList {
  private class Node {
    private String item;
    private Node next;
  }

  private Node first = null;

  public void push(String item) {
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
  }

  public String pop() {
    String item = first.item;
    first = first.next;
    return item;
  }

  public void display() {
    System.out.println("\nDisplay all the strings in the stack:");
    Node node = first;
    while(node != null) {
      System.out.println(node.item);
      node = node.next;
    }
  }

  public static void main(String[] args) {
    StackOfStringsLinkedList stack = new StackOfStringsLinkedList();

    while(!StdIn.isEmpty()) {
      String s = StdIn.readString();
      if(s.equals("-")) System.out.println(stack.pop());
      else              stack.push(s);
    }

    stack.display();
  }
}
