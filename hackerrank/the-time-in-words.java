import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  private static HashMap<Integer, String> map = new HashMap<Integer, String>();
  static {
    map.put(1, "one");
    map.put(2, "two");
    map.put(3, "three");
    map.put(4, "four");
    map.put(5, "five");
    map.put(6, "six");
    map.put(7, "seven");
    map.put(8, "eight");
    map.put(9, "nine");
    map.put(10, "ten");
    map.put(11, "eleven");
    map.put(12, "twelve");
    map.put(13, "thirteen");
    map.put(14, "fourteen");
    map.put(15, "fifteen");
    map.put(16, "sixteen");
    map.put(17, "seventeen");
    map.put(18, "eighteen");
    map.put(19, "nineteen");
    map.put(20, "twenty");
  }

  private static String convertMin(int min) {
    String minutes = (min == 1 || min % 20 == 1) ? "minute" : "minutes";
    String part = (min > 20) ? (map.get(20) + " " + map.get(min % 20)) : map.get(min);
    return part + " " + minutes;
  }

  private static String timeToText(int hours, int minutes) {
    if (minutes == 0) {
      return map.get(hours) + " o' clock";
    } else if (minutes == 30) {
      return "half past " + map.get(hours);
    } else if (minutes == 15) {
      return "quarter past " + map.get(hours);
    } else if (minutes == 45) {
      return "quarter to " + map.get((hours + 1) % 12);
    } else if (minutes < 30) {
      return convertMin(minutes) + " past " + map.get(hours);
    } else {
      return convertMin(60 - minutes) + " to " + map.get((hours + 1) % 12);
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int h = in.nextInt();
    int m = in.nextInt();
    System.out.println(timeToText(h, m));
  }
}
