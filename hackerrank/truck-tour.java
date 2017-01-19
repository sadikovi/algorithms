import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  public static int tourStart(int[] arr) {
    long sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
    }
    if (sum < 0) {
      return -1;
    }

    int index = 0;
    int iter = 0;
    int partial = 0;
    while (true) {
      if (partial < 0) {
        index = iter % arr.length;
        partial = arr[index];
        iter = (index + 1) % arr.length;
      } else {
        partial += arr[iter];
        iter = (iter + 1) % arr.length;
      }

      if (iter == index) {
        return index;
      }
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = in.nextInt() - in.nextInt();
    }
    System.out.println(tourStart(arr));
  }
}
