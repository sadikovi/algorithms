import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static long cuts(int k) {
    // idea is to maximize the area that is cut
    long area = (k % 2 == 0) ? k/2 : (k/2 + 1);
    return area * (k/2);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    while (t > 0) {
      System.out.println(cuts(in.nextInt()));
      t--;
    }
  }
}
