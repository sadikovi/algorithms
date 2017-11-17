class Trie {
  static class TrieNode {
    TrieNode[] children = new TrieNode['z' - 'a' + 1];
    boolean word = false;
  }

  private TrieNode root;

  /** Initialize your data structure here. */
  public Trie() {
    this.root = new TrieNode();
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    TrieNode tmp = root;
    for (int i = 0; i < word.length(); i++) {
      int t = word.charAt(i) - 'a';
      if (tmp.children[t] == null) {
        tmp.children[t] = new TrieNode();
      }
      tmp = tmp.children[t];
      tmp.word = tmp.word || i == word.length() - 1;
    }
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    TrieNode res = find(word);
    return res != null && res.word;
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
      return find(prefix) != null;
  }

  private TrieNode find(String prefix) {
    TrieNode tmp = root;
    for (int i = 0; i < prefix.length(); i++) {
      int t = prefix.charAt(i) - 'a';
      if (tmp.children[t] == null) return null;
      tmp = tmp.children[t];
    }
    return tmp;
  }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
