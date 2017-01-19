import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // You can also solve this problem by doing global Math.min between 3 situations (one-liner):
  // 1. buying black and white for their prices
  // 2. buying black for their price and white for black price and convert black to white
  // 3. vice versa
  private static long minimizeCost(long b, long w, long x, long y, long z) {
    boolean convert = Math.abs(x - y) > z;
    if (!convert) return b * x + w * y;
    if (x > y) {
      return w * y + b * (y + z);
    } else {
      return b * x + w * (x + z);
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for(int a0 = 0; a0 < t; a0++){
      long b = in.nextLong();
      long w = in.nextLong();
      long x = in.nextLong();
      long y = in.nextLong();
      long z = in.nextLong();

      System.out.println(minimizeCost(b, w, x, y, z));
    }
  }
}
