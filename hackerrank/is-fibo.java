import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static boolean isFibo(long n) {
    long a = 0, b = 1;
    while (a + b < n) {
      long c = a + b;
      a = b;
      b = c;
    }
    return (a + b) == n;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    while (t > 0) {
      long n = in.nextLong();
      System.out.println(isFibo(n) ? "IsFibo" : "IsNotFibo");
      t--;
    }
  }
}
