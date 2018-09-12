/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       9/11/2018
 *  Last updated:  9/11/2018
 *
 *  Compilation:   javac Date.java
 *  Execution:     java Date 2018 9 11
 *
 *  Date class
 *  implements Comparable so that sort can work
 *  %  java Date 2018 9 11
 *  year: 2018
 *  month: 09
 *  day: 11
 *
 *----------------------------------------------------------------*/

package yiqi;

public class Date implements Comparable<Date> {
  private final int year, month, day;

  public Date(int y, int m, int d) {
    year  = y;
    month = m;
    day   = d;
  }

  public void display() {
    System.out.printf("\nyear: %d month: %02d day: %02d\n", year, month, day);
  }

  public int compareTo(Date date) {
    if (this.year < date.year) return -1;
    if (this.year > date.year) return 1;
    if (this.month < date.month) return -1;
    if (this.month > date.month) return 1;
    if (this.day < date.day) return -1;
    if (this.day > date.day) return 1;
    return 0;
  }

  public static void main(String[] args) {
    Date date = new Date(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    date.display();
  }
}
