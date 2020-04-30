/*----------------------------------------------------------------
 *  Author:        Yiqi Yan
 *  Written:       9/11/2018
 *  Last updated:  9/11/2018
 *
 *  Compilation:   javac Date.java
 *  Execution:     java Date 2018 9 11
 *
 *  Date class
 *  implements Comparator to sort in terms of different keys
 *  %  java Date 2018 9 11
 *  year: 2018
 *  month: 09
 *  day: 11
 *
 *----------------------------------------------------------------*/

import java.util.Comparator;

public class Date implements Comparable<Date> {
  public static final Comparator<Date> BY_YEAR = new ByYear();
  public static final Comparator<Date> BY_MONTH = new ByMonth();
  public static final Comparator<Date> BY_DAY = new ByDay();
  private final int year, month, day;

  public void display() {
    System.out.printf("year: %d month: %02d day: %02d\n", year, month, day);
  }

  public Date(int y, int m, int d) {
    year  = y;
    month = m;
    day   = d;
  }

  // the natural order
  public int compareTo(Date date) {
    if (this.year < date.year) return -1;
    if (this.year > date.year) return 1;
    if (this.month < date.month) return -1;
    if (this.month > date.month) return 1;
    if (this.day < date.day) return -1;
    if (this.day > date.day) return 1;
    return 0;
  }

  private static class ByYear implements Comparator<Date> {
    public int compare(Date d1, Date d2) { return d1.year - d2.year; }
  }

  private static class ByMonth implements Comparator<Date> {
    public int compare(Date d1, Date d2) { return d1.month - d2.month; }
  }

  private static class ByDay implements Comparator<Date> {
    public int compare(Date d1, Date d2) { return d1.day - d2.day; }
  }

  public static void main(String[] args) {
    Date date = new Date(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    date.display();
  }
}
