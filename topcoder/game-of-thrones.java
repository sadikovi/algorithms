import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  public static boolean canBePalindrome(String str) {
    int[] arr = new int['z' - 'a' + 1];
    for (char t : str.toCharArray()) {
      arr[t - 'a'] += 1;
    }
    boolean foundOdd = false;
    for (int a : arr) {
      if (a % 2 != 0) {
        if (foundOdd) return false;
        foundOdd = true;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner myScan = new Scanner(System.in);
    String inputString = myScan.nextLine();

    String ans = canBePalindrome(inputString) ? "YES" : "NO";
    System.out.println(ans);
    myScan.close();
  }
}
