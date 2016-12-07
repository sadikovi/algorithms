import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  static class TrieNode {
    char data;
    HashMap<Character, TrieNode> map;
    int count;
    boolean end;

    TrieNode(char data) {
      this.data = data;
      map = new HashMap<Character, TrieNode>();
      count = 0;
      end = false;
    }

    public void addName(String str) {
      if (str == null || str.length() == 0) {
        return;
      }
      int i = 0;
      int n = str.length();
      TrieNode node = this;
      while (i < n) {
        char t = str.charAt(i);
        if (!node.map.containsKey(t)) {
          node.map.put(t, new TrieNode(t));
        }
        node = node.map.get(t);
        node.count++;
        if (i == n - 1) {
          node.end = true;
        }
        i++;
      }
    }

    public int findPartial(String str) {
      if (str == null || str.length() == 0) {
        return 0;
      }
      int i = 0;
      int n = str.length();
      int found = 0;
      TrieNode node = this;
      while (i < n && node != null) {
        char t = str.charAt(i);
        if (node.map.containsKey(t)) {
          node = node.map.get(t);
          found = node.count;
        } else {
          node = null;
        }
        i++;
      }
      return (node != null) ? found : 0;
    }
  }


  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    TrieNode root = new TrieNode('#');
    for (int i = 0; i < n; i++) {
      if (in.next().equals("add")) {
        root.addName(in.next());
      } else {
        System.out.println(root.findPartial(in.next()));
      }
    }
  }
}
