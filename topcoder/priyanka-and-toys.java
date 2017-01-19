import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  public static int toys(int[] arr) {
    Arrays.sort(arr);
    int counter = 0;
    int max = -1;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > max) {
        counter++;
        max = arr[i] + 4;
      }
    }
    return counter;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    int[] arr = new int[t];
    for (int i = 0; i < t; i++) {
      arr[i] = in.nextInt();
    }
    System.out.println(toys(arr));
  }
}
