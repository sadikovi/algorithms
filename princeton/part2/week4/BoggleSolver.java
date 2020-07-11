import java.util.HashSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver {
  private static class TrieNode {
    private String word;
    private TrieNode[] children;

    TrieNode() {
      this.word = null;
      this.children = null;
    }

    public void setWord(String word) {
      this.word = word;
    }

    // Returns true if the node indicates the "end of word".
    public boolean isEOW() {
      return this.word != null;
    }

    public String getWord() {
      return this.word;
    }
  }

  private static boolean add(TrieNode root, String word) {
    if (word == null || word.length() == 0) return false;
    TrieNode curr = root;
    for (int i = 0; i < word.length(); i++) {
      int tid = word.charAt(i) - 'A';
      if (curr.children == null) {
        curr.children = new TrieNode['Z' - 'A' + 1];
      }
      if (curr.children[tid] == null) {
        curr.children[tid] = new TrieNode();
      }
      curr = curr.children[tid];
    }
    if (curr.isEOW()) return false;
    curr.setWord(word);
    return true;
  }

  private static TrieNode get(TrieNode curr, char t) {
    int tid = t - 'A';
    if (curr.children == null || curr.children[tid] == null) return null;
    return curr.children[tid];
  }

  private static TrieNode get(TrieNode root, String word) {
    if (word == null || word.length() == 0) return null;
    TrieNode curr = root;
    for (int i = 0; i < word.length(); i++) {
      int tid = word.charAt(i) - 'A';
      if (curr.children == null || curr.children[tid] == null) return null;
      curr = curr.children[tid];
    }
    return curr;
  }

  private final TrieNode root;

  // Initializes the data structure using the given array of strings as the dictionary.
  // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
  public BoggleSolver(String[] dictionary) {
    if (dictionary == null) throw new IllegalArgumentException();
    this.root = new TrieNode();
    for (String word : dictionary) {
      add(root, word);
    }
  }

  private boolean containsWord(String word) {
    TrieNode tmp = get(root, word);
    if (tmp == null || !tmp.isEOW()) return false;
    return true;
  }

  // Returns the set of all valid words in the given Boggle board, as an Iterable.
  public Iterable<String> getAllValidWords(BoggleBoard board) {
    if (board == null) throw new IllegalArgumentException();
    HashSet<String> words = new HashSet<String>();

    for (int i = 0; i < board.rows(); i++) {
      for (int j = 0; j < board.cols(); j++) {
        boolean[][] visited = new boolean[board.rows()][board.cols()];
        findWords(board, visited, root, i, j, words);
      }
    }
    return words;
  }

  private boolean canExplore(BoggleBoard board, boolean[][] visited, int i, int j) {
    return i >= 0 && i < board.rows() && j >= 0 && j < board.cols() && !visited[i][j];
  }

  private int score(String word) {
    switch (word.length()) {
      case 0:
      case 1:
      case 2:
        return 0;
      case 3: return 1;
      case 4: return 1;
      case 5: return 2;
      case 6: return 3;
      case 7: return 5;
      default: return 11; // score is 8+
    }
  }

  private void findWords(BoggleBoard board, boolean[][] visited, TrieNode curr, int i, int j, HashSet<String> words) {
    if (!canExplore(board, visited, i, j)) return;
    char t = board.getLetter(i, j);
    TrieNode next = get(curr, t);
    // search for "Qu" sequence
    if (next != null && t == 'Q') {
      next = get(next, 'U');
    }
    if (next == null) return;

    if (next.isEOW() && score(next.getWord()) > 0) {
      words.add(next.getWord());
    }

    visited[i][j] = true;

    if (canExplore(board, visited, i + 1, j)) findWords(board, visited, next, i + 1, j, words);
    if (canExplore(board, visited, i - 1, j)) findWords(board, visited, next, i - 1, j, words);
    if (canExplore(board, visited, i, j + 1)) findWords(board, visited, next, i, j + 1, words);
    if (canExplore(board, visited, i, j - 1)) findWords(board, visited, next, i, j - 1, words);
    if (canExplore(board, visited, i - 1, j - 1)) findWords(board, visited, next, i - 1, j - 1, words);
    if (canExplore(board, visited, i - 1, j + 1)) findWords(board, visited, next, i - 1, j + 1, words);
    if (canExplore(board, visited, i + 1, j - 1)) findWords(board, visited, next, i + 1, j - 1, words);
    if (canExplore(board, visited, i + 1, j + 1)) findWords(board, visited, next, i + 1, j + 1, words);

    visited[i][j] = false;
  }

  // Returns the score of the given word if it is in the dictionary, zero otherwise.
  // (You can assume the word contains only the uppercase letters A through Z.)
  public int scoreOf(String word) {
    if (word == null) throw new IllegalArgumentException();
    if (containsWord(word)) {
      return score(word);
    } else {
      return 0;
    }
  }

  public static void main(String[] args) {
    In in = new In(args[0]);
    String[] dictionary = in.readAllStrings();
    BoggleSolver solver = new BoggleSolver(dictionary);

    BoggleBoard board = new BoggleBoard(args[1]);
    int score = 0, cnt = 0;
    for (String word : solver.getAllValidWords(board)) {
      StdOut.println(word);
      score += solver.scoreOf(word);
      cnt++;
    }
    StdOut.println("Score = " + score + ", number of words: " + cnt);

    // long start = System.nanoTime();
    // long end = start;
    // int cnt = 0;
    // while ((end - start) / 1e6 < 1000.0) {
    //   BoggleBoard board = new BoggleBoard(10, 10);
    //   solver.getAllValidWords(board);
    //   cnt++;
    //   end = System.nanoTime();
    // }
    // System.out.println("Solved " + cnt + " boards in " + ((end - start) / 1e6) + " ms");
  }
}
