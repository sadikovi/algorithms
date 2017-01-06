import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // This solution generalizes problem to array of numbers and finding smallest and largest sums
  // for n - 1 numbers. Runtime is O(n) and space complexity is O(1), not considering input
  // variables, we just reuse input array.
  private static void printMinMax(long[] arr) {
    long min = Long.MAX_VALUE;
    long max = Long.MIN_VALUE;

    for (int i = 1; i < arr.length; i++) {
      arr[i] += arr[i - 1];
    }

    long roll = 0;
    // condition on (i == 0) is done to avoid array-index-out-of-bound exception, could easily be
    // implemented as loop without i == 0 and lower bound as i > 0 and subsequent update of min/max
    // using `roll` variable.
    for (int i = arr.length - 1; i >= 0; i--) {
      long l = (i == 0) ? 0 : arr[i - 1];
      long r = roll;
      min = Math.min(min, l + r);
      max = Math.max(max, l + r);
      roll += (i == 0) ? 0 : (arr[i] - arr[i - 1]);
    }

    System.out.println(min + " " + max);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    long[] arr = new long[5];
    arr[0] = in.nextLong();
    arr[1] = in.nextLong();
    arr[2] = in.nextLong();
    arr[3] = in.nextLong();
    arr[4] = in.nextLong();
    printMinMax(arr);
  }
}
