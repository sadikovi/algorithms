import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  // Faster solution, runs in O(n) time complexity, O(n) space complexity
  public static Set<Integer> findStones(int n, int a, int b) {
    Set<Integer> set = new HashSet<Integer>();
    for (int i = 0; i < n; i++) {
      set.add(i * a + (n - i - 1) * b);
    }
    return set;
  }

  // Approach using dynamic programming
  // runs in O(n^2) time complexity, O(n^2) space complexity
  public static Set<Integer> findStones2(int n, int a, int b) {
    if (n <= 0) return new HashSet<Integer>();

    List<Set<Integer>> arr = new ArrayList<Set<Integer>>(n);
    arr.add(new HashSet<Integer>());
    arr.get(0).add(0);

    for (int i = 1; i < n; i++) {
      arr.add(new HashSet<Integer>());
      for (int x : arr.get(i - 1)) {
        arr.get(i).add(x + a);
        arr.get(i).add(x + b);
      }
    }
    return arr.get(n - 1);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int i = 0; i < t; i++) {
      int n = in.nextInt();
      int a = in.nextInt();
      int b = in.nextInt();
      Set<Integer> set = findStones(n, a, b);
      Integer[] arr = new Integer[set.size()];
      set.toArray(arr);
      Arrays.sort(arr);
      for (int k : arr) {
        System.out.print(k + " ");
      }
      System.out.println();
    }
  }
}
