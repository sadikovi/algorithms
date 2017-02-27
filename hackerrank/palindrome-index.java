import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  private static boolean isPalindrome(String str) {
    int i = 0, j = str.length() - 1;
    while (i < j) {
      if (str.charAt(i) != str.charAt(j)) return false;
      i++;
      j--;
    }
    return true;
  }

  private static int charIndex(String str) {
    int i = 0, j = str.length() - 1;
    while (i < j) {
      if (str.charAt(i) != str.charAt(j)) {
        if (isPalindrome(str.substring(0, i) + str.substring(i + 1))) {
          return i;
        } else {
          return j;
        }
      }
      i++;
      j--;
    }
    return -1;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    in.nextLine();
    while (t > 0) {
      System.out.println(charIndex(in.nextLine()));
      t--;
    }
  }
}
