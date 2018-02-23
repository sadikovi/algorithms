public class WordDictionary {
  static class TrieNode {
    boolean isWord;
    TrieNode[] children;
    TrieNode() { this.children = new TrieNode['z' - 'a' + 1]; }

    public void addWord(char[] word) {
      int index = 0;
      TrieNode node = this;
      while (index < word.length) {
        if (node.children[word[index] - 'a'] == null) {
          node.children[word[index] - 'a'] = new TrieNode();
        }
        node = node.children[word[index] - 'a'];
        if (index == word.length - 1) node.isWord = true;
        index++;
      }
    }

    public boolean exists(char[] word) {
      return exists(this, word, word.length, 0);
    }

    private boolean exists(TrieNode node, char[] word, int len, int index) {
      if (node == null || index > len) return false;
      if (index == len) return node.isWord;
      if (word[index] != '.') {
        return exists(node.children[word[index] - 'a'], word, len, index + 1);
      } else {
        for (TrieNode child : node.children) {
          if (exists(child, word, len, index + 1)) {
            return true;
          }
        }
        return false;
      }
    }
  }

  TrieNode root;

  /** Initialize your data structure here. */
  public WordDictionary() {
    root = new TrieNode();
  }

  /** Adds a word into the data structure. */
  public void addWord(String word) {
    root.addWord(word.toCharArray());
  }

  /**
   * Returns if the word is in the data structure.
   * A word could contain the dot character '.' to represent any one letter.
   */
  public boolean search(String word) {
    return root.exists(word.toCharArray());
  }
}

///////////////////////////////////////////////////////////////
// More verbose approach, but gives slightly faster solution
///////////////////////////////////////////////////////////////

public class WordDictionary {
  static class TrieNode {
    char value;
    boolean isWord;
    TrieNode[] children;
    TrieNode() { this.children = new TrieNode['z' - 'a' + 1]; }
    TrieNode(char value) { this(); this.value = value; }

    public void addWord(char[] word) {
      int index = 0;
      TrieNode node = this;
      while (index < word.length) {
        if (node.children[word[index] - 'a'] == null) {
          node.children[word[index] - 'a'] = new TrieNode(word[index]);
        }
        node = node.children[word[index] - 'a'];
        if (index == word.length - 1) node.isWord = true;
        index++;
      }
    }

    public boolean exists(char[] word) {
      return exists(word, 0);
    }

    private boolean exists(char[] word, int index) {
      if (index >= word.length) return false;
      char t = word[index];
      if (t != '.') {
        if (this.children[word[index] - 'a'] == null) return false;
        TrieNode node = this.children[word[index] - 'a'];
        if (index == word.length - 1) return node.isWord;
        return node.exists(word, index + 1);
      } else {
        for (TrieNode node : this.children) {
          if (index != word.length - 1 && node != null && node.exists(word, index + 1)) {
            return true;
          } else if (index == word.length - 1 && node != null && node.isWord) {
            return true;
          }
        }
        return false;
      }
    }
  }

  TrieNode root;

  /** Initialize your data structure here. */
  public WordDictionary() {
    root = new TrieNode();
  }

  /** Adds a word into the data structure. */
  public void addWord(String word) {
    root.addWord(word.toCharArray());
  }

  /**
   * Returns if the word is in the data structure.
   * A word could contain the dot character '.' to represent any one letter.
   */
  public boolean search(String word) {
    return root.exists(word.toCharArray());
  }
}

///////////////////////////////////////////////////////////////
// Faster solution, beats 98.69% of submission
///////////////////////////////////////////////////////////////

class WordDictionary {

  static class TrieNode {
    TrieNode[] children;
    boolean eow;

    TrieNode() {
      this.children = new TrieNode[26];
      this.eow = false;
    }
  }

  private TrieNode root;

  /** Initialize your data structure here. */
  public WordDictionary() {
    this.root = new TrieNode();
  }

  /** Adds a word into the data structure. */
  public void addWord(String word) {
    TrieNode tmp = this.root;
    for (int i = 0; i < word.length(); i++) {
      int t = word.charAt(i) - 'a';
      if (tmp.children[t] == null) {
        tmp.children[t] = new TrieNode();
      }
      tmp = tmp.children[t];
      if (i == word.length() - 1) {
        tmp.eow = true;
      }
    }
  }

  private boolean search(TrieNode n, String word, int pos) {
    for (int i = pos; i < word.length(); i++) {
      int t = word.charAt(i) - 'a';
      if (t < 0) {
        boolean res = false;
        for (TrieNode c : n.children) {
          if (c != null) {
            res = res || search(c, word, i + 1);
          }
        }
        return res;
      } else {
        if (n.children[t] == null) return false;
        n = n.children[t];
      }
    }
    return n.eow;
  }

  /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
  public boolean search(String word) {
    return search(this.root, word, 0);
  }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
