import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  static class RunningMedian {
    private PriorityQueue<Integer> left = null;
    private PriorityQueue<Integer> right = null;

    RunningMedian(int n) {
      left = new PriorityQueue(n, Collections.reverseOrder());
      right = new PriorityQueue(n);
    }

    public void add(int element) {
      if (right.isEmpty()) {
        right.add(element);
        return;
      }

      if (left.size() == right.size()) {
        if (element > left.peek()) {
          right.add(element);
        } else {
          right.add(left.poll());
          left.add(element);
        }
      } else {
        if (element > right.peek()) {
          left.add(right.poll());
          right.add(element);
        } else {
          left.add(element);
        }
      }
    }

    public double getMedian() {
      // rather throw exception in this case
      if (left.isEmpty() && right.isEmpty()) return -1.0;
      if (left.isEmpty() || left.size() < right.size()) {
        return right.peek();
      }
      int min = left.peek();
      int max = right.peek();
      return (min + max) / 2.0;
    }
  }

  public static void main(String args[] ) throws Exception {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    RunningMedian obj = new RunningMedian(n);
    for (int i = 0; i < n; i++) {
      int elem = in.nextInt();
      obj.add(elem);
      System.out.printf("%.1f", obj.getMedian());
      System.out.println();
    }
  }
}
