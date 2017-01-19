import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // Solution runs in O(n) time with O(n) space complexity
  static int pairs(int[] a, int k) {
    HashSet<Integer> set = new HashSet<Integer>();
    for (int x : a) {
      set.add(x);
    }

    int count = 0;
    for (int x : a) {
      if (x > k && set.contains(x - k)) {
        count++;
      }
    }
    return count;
  }

  // Solution runs in O(n*log(n)) time with O(1) space complexity
  static int pairs2(int[] a, int k) {
    Arrays.sort(a);
    int count = 0;
    for (int i = 0; i < a.length; i++) {
      if (a[i] > k) {
        int x = a[i] - k;
        int l = 0;
        int r = i - 1;
        while (l <= r) {
          int m = (l + r) / 2;
          if (a[m] == x) {
            count++;
            break;
          } else if (a[m] > x) {
            r = m - 1;
          } else {
            l = m + 1;
          }
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int res;

    String n = in.nextLine();
    String[] n_split = n.split(" ");

    int _a_size = Integer.parseInt(n_split[0]);
    int _k = Integer.parseInt(n_split[1]);

    int[] _a = new int[_a_size];
    int _a_item;
    String next = in.nextLine();
    String[] next_split = next.split(" ");

    for(int _a_i = 0; _a_i < _a_size; _a_i++) {
      _a_item = Integer.parseInt(next_split[_a_i]);
      _a[_a_i] = _a_item;
    }

    res = pairs(_a,_k);
    System.out.println(res);
  }
}
