/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       9/11/2018
 *  Last updated:  9/11/2018
 *
 *  Compilation:   javac SortSimpleDemo.java
 *  Execution:     java SortSimpleDemo
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
import yiqi.Date;

public class SortSimpleDemo {
  public static void sort(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      int min = i;
      for (int j = i+1; j < a.length; j++) {
        if (less(a, j, min))
          min = j;
      }
      exchange(a, i, min);
    }
  }

  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      if (less(a, i, i-1))
        return false;
    }
    return true;
  }

  private static boolean less(Comparable[] a, int i, int j) {
    // retun true if a[i] is less than a[j]
    return (a[i].compareTo(a[j]) < 0);
  }

  private static void exchange(Comparable[] a, int i, int j) {
    // exchange the ith and the jth elements in a
    Comparable temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    Date dates[] = {new Date(2018,7,12), new Date(1996,5,31), new Date(1970,10,1), new Date(2018,9,11)};

    System.out.println("\nOriginal Array:");
    for (int i = 0; i < dates.length; i ++) {
      dates[i].display();
    }

    SortSimpleDemo.sort(dates);

    System.out.println("\nSorted Array:");
    for (int i = 0; i < dates.length; i ++) {
      dates[i].display();
    }

    System.out.println("\nIs the array sorted?");
    System.out.println(SortSimpleDemo.isSorted(dates));
  }
}
