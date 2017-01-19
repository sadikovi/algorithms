import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // Running time is O(n) and space complexity is O(1)
  public static int pairs(int[] arr) {
    int[] colours = new int[101];
    for (int sock : arr) {
      colours[sock]++;
    }
    int cnt = 0;
    for (int i = 0; i < colours.length; i++) {
      cnt += colours[i] / 2;
    }
    return cnt;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int c[] = new int[n];
    for(int c_i=0; c_i < n; c_i++){
      c[c_i] = in.nextInt();
    }
    System.out.println(pairs(c));
  }
}
