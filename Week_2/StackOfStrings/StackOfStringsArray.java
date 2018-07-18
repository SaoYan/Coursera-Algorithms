/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       7/17/2018
 *  Last updated:  7/17/2018
 *
 *  Compilation:   javac StackOfStringsArray.java
 *  Execution:     java StackOfStringsArray < test.txt
 *
 *  Stack of strings - array implementation
 *  % java StackOfStringsArray < test.txt
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

 import edu.princeton.cs.algs4.StdIn;
 import edu.princeton.cs.algs4.StdOut;

 public class StackOfStringsArray {
   private String[] s;
   private int N = 0;

   public StackOfStringsArray(int capacity) {
     s = new String[capacity];
   }

   public boolean isEmpty() {
     return N == 0;
   }

   public void push(String item) {
     s[N++] = item;
   }

   public String pop() {
     // return s[--N] // Loitering
     // Avoid Loitering
     String item = s[--N];
     s[N] = null;
     return item;
   }

   public void display() {
     System.out.println("\nDisplay all the strings in the stack:");
     for(int i = N-1; i >= 0; i--) System.out.println(s[i]);
   }

   public static void main(String[] args) {
     StackOfStringsArray stack = new StackOfStringsArray(20);

     while(!StdIn.isEmpty()) {
       String s = StdIn.readString();
       if(s.equals("-")) System.out.println(stack.pop());
       else              stack.push(s);
     }

     stack.display();
   }
 }
