import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static int digits(int n) {
    int d = 0;
    while (n > 0) {
      n /= 10;
      d++;
    }
    return d;
  }

  public static void printKaprekarNumbers(int p, int q) {
    boolean found = false;
    for (int i = p; i <= q; i++) {
      int d = digits(i);
      long sq = (long) Math.pow(i, 2);
      long del = (long) Math.pow(10, d);
      long r = sq % del;
      long l = sq / del;
      if (l + r == i) {
        found = true;
        System.out.print(i + " ");
      }
    }
    if (!found) {
      System.out.print("INVALID RANGE");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int p = in.nextInt();
    int q = in.nextInt();
    printKaprekarNumbers(p, q);
  }
}
