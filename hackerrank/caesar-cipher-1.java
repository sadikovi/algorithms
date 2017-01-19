import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static char rotate(char t, int k) {
    if (t >= 'a' && t <= 'z') {
      int offset = (t - 'a' + k) % ('z' - 'a' + 1);
      return (char) (offset + 'a');
    } else if (t >= 'A' && t <= 'Z') {
      int offset = (t - 'A' + k) % ('Z' - 'A' + 1);
      return (char) (offset + 'A');
    } else {
      return t;
    }
  }

  private static String cipher(int n, String s, int k) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append(rotate(s.charAt(i), k));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    String s = in.next();
    int k = in.nextInt();
    System.out.println(cipher(n, s, k));
  }
}
