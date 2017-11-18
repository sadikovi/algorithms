// Solution beats 80.91% of submissions.
//
// Runs in O(N*M + K*W), where N and M are dimensions of the board, and K is length of dictionary
// and W is the length of the word.
//
// Space complexity is O(W), where W is the length of the word, there is also a constant for
// children array storage, but asymptotically will be linear to the word.
// This also includes recursion for depth W.
//
class Solution {
  static class TrieNode {
    TrieNode[] children = new TrieNode['z' - 'a' + 1];
    String word = null;
  }

  public List<String> findWords(char[][] board, String[] words) {
    List<String> res = new ArrayList<String>();
    TrieNode root = new TrieNode();
    // build trie
    for (String word : words) {
      add(word, root);
    }
    // search words
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        search(board, i, j, root, res);
      }
    }
    return res;
  }

  private void add(String word, TrieNode root) {
    for (int i = 0; i < word.length(); i++) {
      int idx = word.charAt(i) - 'a';
      if (root.children[idx] == null) {
        root.children[idx] = new TrieNode();
      }
      root = root.children[idx];
    }
    root.word = word;
  }

  private void search(char[][] board, int i, int j, TrieNode node, List<String> res) {
    if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || node == null || board[i][j] == '?') return;
    char t = board[i][j];
    TrieNode next = node.children[t - 'a'];
    if (next == null) return;
    if (next.word != null) {
      res.add(next.word);
      next.word = null;
    }
    board[i][j] = '?';
    search(board, i+1, j, next, res);
    search(board, i-1, j, next, res);
    search(board, i, j+1, next, res);
    search(board, i, j-1, next, res);
    board[i][j] = t;
  }
}
