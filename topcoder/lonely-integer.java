import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // You can also solve this problem by either soring elements or building set to add/remove pairs.
  // But this solution runs in O(n) time and O(1) space complexity.
  private static int lonelyInteger(int[] a) {
    int xor = 0;
    for (int x : a) {
      xor ^= x;
    }
    return xor;
  }
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }
    System.out.println(lonelyInteger(a));
  }

}
