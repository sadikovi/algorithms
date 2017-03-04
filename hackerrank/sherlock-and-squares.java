import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  static int countSquares(int a, int b) {
    int counter = 0;
    int i = (int) Math.sqrt(a);
    while (i * i <= b) {
      if (i * i >= a) counter++;
      i++;
    }
    return counter;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    while (t > 0) {
      int a = in.nextInt();
      int b = in.nextInt();
      System.out.println(countSquares(a, b));
      t--;
    }
  }
}
