import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * This is essentially a sorting problem, we create Order class to maintain time + delay and also
 * order id. Extending Comparable allows to provide custom order in case of matching time + delay.
 */
public class Solution {
  static class Order implements Comparable<Order> {
    int id;
    int time;
    int delay;

    Order(int id, int time, int delay) {
      this.id = id;
      this.time = time;
      this.delay = delay;
    }

    public int eta() {
      return this.time + this.delay;
    }

    @Override
    public int compareTo(Order other) {
      if (this.eta() < other.eta()) return -1;
      if (this.eta() > other.eta()) return 1;
      if (this.id < this.id) return -1;
      if (this.id > this.id) return 1;
      return 0;
    }
  }

  private static void sort(Order[] orders) {
    Arrays.sort(orders);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    Order[] orders = new Order[n];
    for (int i = 0; i < n; i++) {
      int time = in.nextInt();
      int delay = in.nextInt();
      orders[i] = new Order(i + 1, time, delay);
    }

    sort(orders);
    for (Order order : orders) {
      System.out.print(order.id + " ");
    }
    System.out.println();
  }
}
