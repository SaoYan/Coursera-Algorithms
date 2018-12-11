/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       12/10/2018
 *  Last updated:  12/10/2018
 *
 *  Compilation: javac Permutation.java
 *  Execution: java Permutation 4 < test/distinct.txt
 *
 *  A client program Permutation.java that takes an integer k as a command-line argument;
 *  reads in a sequence of strings from standard input using StdIn.readString();
 *  and prints exactly k of them, uniformly at random
----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
   public static void main(String[] args) {
     int k = Integer.parseInt(args[0]);
     RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();

     /*
     while (!StdIn.isEmpty()) {
       String s = StdIn.readString();
       randomizedQueue.enqueue(s);
     }

     int n = 1;
     for (String item : randomizedQueue) {
       if (n > k) break;
       n++;
       StdOut.println(item);
     }
     */

     if (k > 0) {
       for (int i = 0; !StdIn.isEmpty(); i++) {
         String s = StdIn.readString();
         if (i < k) randomizedQueue.enqueue(s);
         else if (StdRandom.uniform() < 0.5) {
           randomizedQueue.dequeue();
           randomizedQueue.enqueue(s);
         }
       }
     }
     for (String item : randomizedQueue) StdOut.println(item);
   }
}
