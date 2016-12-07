import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  static class TrieNode {
    char ch;
    TrieNode[] map;
    int count;

    TrieNode(char current) {
      ch = current;
      map = new TrieNode['z' - 'a' + 1];
      count = 1;
    }

    public void add(String name, int index) {
      if (name == null || index >= name.length()) return;
      char t = name.charAt(index);
      if (map[t - 'a'] == null) {
        map[t - 'a'] = new TrieNode(t);
      } else {
        map[t - 'a'].count += 1;
      }
      map[t - 'a'].add(name, index + 1);
    }

    public TrieNode find(String name, int index) {
      if (name == null || index >= name.length()) return null;
      char t = name.charAt(index);
      if (index + 1 == name.length() || map[t - 'a'] == null) return map[t - 'a'];
      return map[t - 'a'].find(name, index + 1);
    }
  }

  private static TrieNode root = new TrieNode('#');

  private static void add(String name) {
    root.add(name, 0);
  }

  private static int find(String partial) {
    TrieNode node = root.find(partial, 0);
    return (node == null) ? 0 : node.count;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    for(int a0 = 0; a0 < n; a0++){
      String op = in.next();
      String contact = in.next();
      if (op.equals("add")) {
        add(contact);
      } else {
        System.out.println(find(contact));
      }
    }
  }
}
