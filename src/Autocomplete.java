import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Autocomplete dictionary that maintains top k words for each prefix.
// Every inserted word is updated as most recently used.
public class Autocomplete {
  static class ListNode {
    String word = null; // only null for dummy node
    ListNode next = null;

    public String toString() {
      return "" + word + (next == null ? "" : ", " + next.toString());
    }
  }

  static class TrieNode {
    private final int k; // number of words to keep
    private TrieNode[] children; // stores ASCII characters only
    private HashMap<String, ListNode> map; // for efficient removal
    private ListNode list; // head of priority list (recently used)

    TrieNode(int k) {
      this.k = k;
      this.children = null;
      this.map = new HashMap<String, ListNode>();
      this.list = new ListNode(); // dummy node for deletion of a tail element
    }

    private void removeFromList(ListNode node) {
      if (node == null) return;
      if (this.list == node) {
        this.list = node.next;
      } else if (node.next != null) {
        node.word = node.next.word; // next is never null
        node.next = node.next.next;
      }
    }

    private void update(String word) {
      removeFromList(map.get(word));
      map.remove(word);

      ListNode node = new ListNode();
      node.word = word; // link the new word
      node.next = this.list;
      this.list = node; // update head
      map.put(word, node); // update map

      ListNode tmp = this.list;
      int len = k;
      while (tmp != null && tmp.next != null) {
        if (len <= 0) {
          if (tmp.word != null) {
            map.remove(tmp.word);
          }
          removeFromList(tmp);
        } else {
          tmp = tmp.next;
          len--;
        }
      }
    }

    /** Adds or updates word in the trie */
    public void add(String word) {
      TrieNode node = this;
      for (int i = 0; i < word.length(); i++) {
        node.update(word);
        if (node.children == null) {
          node.children = new TrieNode[128];
        }
        int p = word.charAt(i);
        if (node.children[p] == null) {
          node.children[p] = new TrieNode(k);
        }
        node = node.children[p];
      }
      node.update(word);
    }

    /** Returns node for a prefix or null if prefix does not exist */
    public TrieNode find(String prefix) {
      if (children == null) return null; // not initialised
      TrieNode node = this;
      for (int i = 0; i < prefix.length(); i++) {
        if (node == null || node.children == null) return null;
        node = node.children[prefix.charAt(i)];
      }
      return node;
    }

    /** Returns top words for the node */
    public List<String> topWords(String prefix) {
      TrieNode node = find(prefix);
      if (node != null) {
        List<String> words = new ArrayList<String>();
        ListNode tmp = node.list;
        while (tmp != null && tmp.next != null) {
          words.add(tmp.word);
          tmp = tmp.next;
        }
        return words;
      }
      return null;
    }

    private String toString(String offset) {
      StringBuilder sb = new StringBuilder();
      if (children != null) {
        for (int i = 0; i < children.length; i++) {
          if (children[i] != null) {
            sb.append("\n");
            sb.append(offset);
            sb.append("  ");
            sb.append((char) i);
            sb.append(":");
            sb.append(children[i].map.keySet());
            sb.append(", ");
            sb.append("[" + children[i].list + "]");
            sb.append(children[i].toString(offset + "  "));
          }
        }
      }
      return sb.toString();
    }

    public String toString() {
      return toString("");
    }
  }

  public static void main(String[] args) {
    List<String> words = new ArrayList<String>();
    words.add("b0");
    words.add("b1");
    words.add("b2");
    words.add("b0");

    TrieNode root = new TrieNode(3);
    System.out.println(root);

    for (String word : words) {
      System.out.println("\n=== word " + word + " ===");
      root.add(word);
      System.out.println(root);
      System.out.println("top words for 'b': " + root.topWords("b"));
    }
  }
}
