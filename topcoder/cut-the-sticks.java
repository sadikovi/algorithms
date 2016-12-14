import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // Can be solved simpler with set, but this will require O(n) space complexity
  public static void printSticks(int[] arr) {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < arr.length; i++) {
      min = Math.min(min, arr[i]);
    }

    int count = arr.length;
    int offset = 0;
    while (count > 0 && min > 0) {
      offset += min;
      min = 0;
      count = 0;
      for (int elem : arr) {
        if (elem > offset) {
          count++;
          min = (min == 0) ? elem - offset : Math.min(min, elem - offset);
        } else if (elem == offset) {
          count++;
        }
      }

      if (count > 0) {
        System.out.println(count);
      }
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int arr[] = new int[n];
    for(int arr_i=0; arr_i < n; arr_i++){
      arr[arr_i] = in.nextInt();
    }
    printSticks(arr);
  }
}
