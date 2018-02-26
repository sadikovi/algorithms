// Simple Trie implementation with support of following methods:
// - add word
// - check contains
// - check prefix
// - remove word
public class Trie {
  static class TrieNode {
    // trie for ASCII string
    TrieNode[] children = new TrieNode['z' - 'a' + 1];
    boolean eow = false;
    int words = 0;

    @Override
    public String toString() {
      return "{eow: " + eow + ", words: " + words + ", children: " +
        java.util.Arrays.toString(children) + "}";
    }
  }

  private TrieNode root;

  public Trie() {
    this.root = new TrieNode();
  }

  private int idx(char t) {
    return t - 'a';
  }

  public void add(String s) {
    TrieNode tmp = this.root;
    for (int i = 0; i < s.length(); i++) {
      int t = idx(s.charAt(i));
      if (tmp.children[t] == null) {
        tmp.children[t] = new TrieNode();
      }
      tmp.words++;
      tmp = tmp.children[t];
      tmp.eow = tmp.eow || (i == s.length() - 1);
    }
  }

  private TrieNode exists(String s) {
    TrieNode tmp = this.root;
    for (int i = 0; i < s.length(); i++) {
      int t = idx(s.charAt(i));
      if (tmp.children[t] == null) return null;
      tmp = tmp.children[t];
    }
    return tmp;
  }

  public boolean contains(String s) {
    TrieNode res = exists(s);
    return res != null && res.eow;
  }

  public boolean prefix(String s) {
    return exists(s) != null;
  }

  public void remove(String s) {
    TrieNode tmp = exists(s);
    if (tmp == null || !tmp.eow) return;
    tmp.eow = false;
    tmp = this.root;
    for (int i = 0; i < s.length(); i++) {
      int t = idx(s.charAt(i));
      tmp.words--;
      if (tmp.words == 0) {
        tmp.children[t] = null;
        return;
      }
      tmp = tmp.children[t];
    }
  }

  @Override
  public String toString() {
    return "Trie: " + this.root;
  }
}
