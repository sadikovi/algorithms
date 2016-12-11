import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  // naive approach O(32) ~ O(1)
  public static long flipBits(long value) {
    int i = 31;
    while (i >= 0) {
      value ^= 1L << i;
      i--;
    }
    return value;
  }

  public static long flipBitsUpdated(long value) {
    // create mask of all ones for 32 MSB
    // this runs faster than naive approach
    long mask = ~0L >>> 32;
    return value ^ mask;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int i = 0; i < t; i++) {
      System.out.println(flipBitsUpdated(in.nextLong()));
    }
  }
}
