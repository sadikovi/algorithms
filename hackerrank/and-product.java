import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static long bitwiseAnd(long a, long b) {
    long c = 0;
    int shift = 0;
    while (a > 0 && b > 0) {
      boolean bit = (a & 1) == 1 && (b & 1) == 1 && a == b;
      if (bit) c |= 1L << shift;
      shift++;
      a >>>= 1;
      b >>>= 1;
    }
    return c;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    while (t > 0) {
      long a = in.nextLong();
      long b = in.nextLong();
      System.out.println(bitwiseAnd(a, b));
      t--;
    }
  }
}
