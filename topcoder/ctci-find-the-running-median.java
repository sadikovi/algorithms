import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
  private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

  private static void addNumber(int k) {
    if (maxHeap.size() == minHeap.size()) {
      if (minHeap.peek() != null && k > minHeap.peek()) {
        maxHeap.add(minHeap.poll());
        minHeap.add(k);
      } else {
        maxHeap.add(k);
      }
    } else {
      if (k < maxHeap.peek()) {
        minHeap.add(maxHeap.poll());
        maxHeap.add(k);
      } else {
        minHeap.add(k);
      }
    }
  }

  private static double getMedian() {
    if (maxHeap.size() == 0) return 0;
    if (minHeap.size() == maxHeap.size()) {
      return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2;
    } else {
      return maxHeap.peek();
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int a[] = new int[n];
    for(int a_i=0; a_i < n; a_i++){
      a[a_i] = in.nextInt();
      addNumber(a[a_i]);
      System.out.println(getMedian());
    }
  }
}
