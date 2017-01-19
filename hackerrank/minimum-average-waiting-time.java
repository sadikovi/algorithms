import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  static class Order implements Comparable<Order> {
    int t; // time of arrival
    int l; // time to cook pizza
    Order(int t, int l) {
      this.t = t;
      this.l = l;
    }

    public int compareTo(Order that) {
      if (that.l == this.l) return 0;
      return (that.l > this.l) ? -1 : 1;
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    if (n <= 0) {
      System.out.println(-1);
      return;
    }

    PriorityQueue<Order> input = new PriorityQueue(n, new Comparator<Order>() {
      public int compare(Order a, Order b) {
        if (a.t == b.t) return 0;
        return (a.t > b.t) ? 1 : -1;
      }
    });

    for (int i = 0; i < n; i++) {
      input.add(new Order(in.nextInt(), in.nextInt()));
    }

    long eta = 0;
    long durations = 0;
    PriorityQueue<Order> heap = new PriorityQueue(n);
    while (!input.isEmpty()) {
      Order order = input.poll();
      while (!heap.isEmpty() && eta <= order.t) {
        Order curr = heap.poll();
        eta = (eta <= curr.t) ? (curr.t + curr.l) : (eta + curr.l);
        durations += eta - curr.t;
      }
      heap.add(order);
    }

    while (!heap.isEmpty()) {
      Order curr = heap.poll();
      eta = (eta <= curr.t) ? (curr.t + curr.l) : (eta + curr.l);
      durations += eta - curr.t;
    }

    System.out.println(durations / n);
  }
}
