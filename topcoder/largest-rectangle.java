import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static int largestRectangle(int[] heights) {
    LinkedList<Integer> values = new LinkedList<Integer>();
    LinkedList<Integer> columns = new LinkedList<Integer>();
    int area = 0;

    for (int height : heights) {
      if (values.isEmpty() || values.peek() < height) {
        values.push(height);
        columns.push(1);
      } else if (values.peek() == height) {
        columns.push(columns.pop() + 1);
      } else {
        int p = 0;
        while (!values.isEmpty() && values.peek() > height) {
          int v = values.pop();
          p += columns.pop();
          area = Math.max(area, v * p);
        }

        if (values.isEmpty()) {
          values.push(height);
          columns.push(p + 1);
        } else if (values.peek() == height) {
          columns.push(columns.pop() + p);
        } else {
          values.push(height);
          columns.push(p + 1);
        }
      }
    }

    int p = 0;
    while (!values.isEmpty()) {
      int v = values.pop();
      p += columns.pop();
      area = Math.max(area, v * p);
    }
    return area;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] heights = new int[n];
    for (int i = 0; i < n; i++) {
      heights[i] = in.nextInt();
    }
    System.out.println(largestRectangle(heights));
  }
}
