package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {
  public static List<String> permutations(String str) {
    List<String> res = new ArrayList<String>();
    if (str == null || str.isEmpty()) return res;
    if (str.length() == 1) {
      res.add(str);
    } else {
      List<String> perm = permutations(str.substring(1));
      for (String p : perm) {
        for (int i = 0; i <= p.length(); i++) {
          res.add(p.substring(0, i) + str.charAt(0) + p.substring(i));
        }
      }
    }
    return res;
  }

  public static List<String> permutationsWithDup(String str) {
    List<String> result = new ArrayList<String>();
    if (str == null || str.isEmpty()) return result;
    HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
    for (int i = 0; i < str.length(); i++) {
      char t = str.charAt(i);
      if (!freq.containsKey(t)) {
        freq.put(t, 0);
      }
      freq.put(t, freq.get(t) + 1);
    }

    printPerms(freq, "", str.length(), result);
    return result;
  }

  private static void printPerms(
      HashMap<Character, Integer> freq, String prefix, int remaining, List<String> result) {
    if (remaining == 0) {
      result.add(prefix);
      return;
    }

    for (Character c : freq.keySet()) {
      int count = freq.get(c);
      if (count > 0) {
        freq.put(c, count - 1);
        printPerms(freq, prefix + c, remaining - 1, result);
        freq.put(c, count);
      }
    }
  }
}
