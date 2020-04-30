/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       12/12/2018
 *  Last updated:  12/12/2018
 *
 *  Compilation:   javac ComparatorDemo.java
 *  Execution:     java ComparatorDemo
 *
 *  Demo of Java Comparator interface
 *
 *  % java ComparatorDemo
 *
 *----------------------------------------------------------------*/

import java.util.Comparator;
import java.util.Arrays;

public class ComparatorDemo {
  public static boolean isSorted(Object[] a, Comparator comparator) {
    for (int i = 1; i < a.length; i++) {
      if (less(comparator, a[i], a[i-1]))
        return false;
    }
    return true;
  }

  /*
  public static void sort(Object[] a, Comparator comparator) {
    // Selection Sort
    for (int i = 0; i < a.length; i++) {
      int min = i;
      for (int j = i+1; j < a.length; j++) {
        if (less(comparator, a[j], a[min]))
          min = j;
      }
      exchange(a, i, min);
    }
  }
  */

  private static boolean less(Comparator c, Object u, Object v) {
    return (c.compare(u,v) < 0);
  }

  private static void exchange(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  public static void main(String[] args) {
    Date[] dates = {new Date(2018,7,12), new Date(1996,5,31), new Date(1970,10,1), new Date(2018,9,11)};

    System.out.println("\nOriginal Array:");
    for (int i = 0; i < dates.length; i ++) {
      dates[i].display();
    }

    Arrays.sort(dates); //natural order
    System.out.println("\nSorted Array - Natural Order:");
    for (int i = 0; i < dates.length; i ++) {
      dates[i].display();
    }

    System.out.println("\nIs the array sorted according to customer order?");
    System.out.println(ComparatorDemo.isSorted(dates, Date.BY_DAY));

    // ComparatorDemo.sort(dates, Date.BY_DAY);
    Arrays.sort(dates, Date.BY_DAY); //customer order
    System.out.println("\nSorted Array - Customer Order:");
    for (int i = 0; i < dates.length; i ++) {
      dates[i].display();
    }

    System.out.println("\nIs the array sorted according to customer order?");
    System.out.println(ComparatorDemo.isSorted(dates, Date.BY_DAY));
  }
}
