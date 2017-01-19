import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  static class Trie {
    char data;
    boolean isEnd;
    Trie[] children;

    Trie(char data) {
      this.data = data;
      this.isEnd = false;
      children = new Trie['j' - 'a' + 1];
    }

    public void add(String t) {
      if (t == null || t.length() == 0) {
        return;
      }
      char ch = t.charAt(0);
      if (children[ch - 'a'] == null) {
        children[ch - 'a'] = new Trie(ch);
      }
      Trie node = children[ch - 'a'];
      // mark as final element
      if (t.length() == 1) {
        node.isEnd = true;
      } else {
        node.add(t.substring(1));
      }
    }

    /** Vice versa check for long string and short string */
    public boolean hasPrefix(String t) {
      Trie node = this;
      int i = 0;
      int n = t.length();
      while (i < n && node != null && !node.isEnd) {
        // when element does not exist in Trie => false
        node = node.children[t.charAt(i++) - 'a'];
      }
      if (node == null) return false;
      // assume that string is non-empty
      return (i >= n) || node.isEnd;
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    Trie root = new Trie('#');
    for (int i = 0; i < n; i++) {
      String str = in.next();
      if (root.hasPrefix(str)) {
        System.out.println("BAD SET");
        System.out.println(str);
        return;
      } else {
        root.add(str);
      }
    }
    System.out.println("GOOD SET");
  }
}
