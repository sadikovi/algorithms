import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // brute-force approach just iterate over all pairs of integers and print max
  static int maxXor(int l, int r) {
    int max = 0;
    for (int i = l; i < r; i++) {
      for (int j = i + 1; j <= r; j++) {
        max = Math.max(max, i ^ j);
      }
    }
    return max;
  }

  // bit-shift approach, to '|' is used to maximize XOR difference for every bit.
  static int maxXor2(int l, int r) {
    int v = l ^ r;
    v |= v >> 1;
    v |= v >> 2;
    v |= v >> 4;
    v |= v >> 8;
    v |= v >> 16;
    return v;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int res;
    int _l;
    _l = Integer.parseInt(in.nextLine());

    int _r;
    _r = Integer.parseInt(in.nextLine());

    res = maxXor2(_l, _r);
    System.out.println(res);
  }
}
