// Solution to the problem of finding shortest transformation path from one word to the other.
// CTCI 17.22
//
// Example:
// WordTransformer.transform("abc", "cca", Array("ccc", "cca", "bcc", "aaa", "aba", "abb", "abc", "aac", "bba", "bbc"))
// res6: java.util.List[String] = [abc, bbc, bcc, ccc, cca]
//
// WordTransformer.transform("abc", "cca", Array("damp", "lamp", "limp", "lime", "like"))
// res7: java.util.List[String] = null
//
// WordTransformer.transform("like", "damp", Array("damp", "lamp", "limp", "lime", "like"))
// res8: java.util.List[String] = [like, lime, limp, lamp, damp]
//
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WordTransformer {
  static class Node {
    String value;
    Node prev;

    Node(String value, Node prev) {
      this.value = value;
      this.prev = prev;
    }

    Node(String value) {
      this(value, null);
    }
  }

  public static List<String> transform(String start, String stop, String[] words) {
    if (start.length() != stop.length()) return null;
    HashMap<String, List<String>> graph = buildGraph(words);
    LinkedList<Node> queue = new LinkedList<Node>();
    queue.add(new Node(start));
    while (!queue.isEmpty()) {
      Node t = queue.remove();
      if (t.value.equals(stop)) {
        return reversePath(t);
      } else {
        List<String> children = graph.get(t.value);
        if (children != null) {
          for (String w : children) {
            queue.add(new Node(w, t));
          }
        }
        graph.remove(t.value);
      }
    }
    return null;
  }

  static HashMap<String, List<String>> buildGraph(String[] words) {
    HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    for (int i = 0; i < words.length; i++) {
      for (int j = i + 1; j < words.length; j++) {
        if (oneCharDiff(words[i], words[j])) {
          if (!map.containsKey(words[i])) {
            map.put(words[i], new LinkedList<String>());
          }
          if (!map.containsKey(words[j])) {
            map.put(words[j], new LinkedList<String>());
          }
          map.get(words[i]).add(words[j]);
          map.get(words[j]).add(words[i]);
        }
      }
    }
    return map;
  }

  static List<String> reversePath(Node t) {
    LinkedList<String> res = new LinkedList<String>();
    while (t != null) {
      res.push(t.value);
      t = t.prev;
    }
    return res;
  }

  static boolean oneCharDiff(String a, String b) {
    if (a.length() != b.length()) return false;
    int diff = 0;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) != b.charAt(i)) {
        diff++;
      }
    }
    return diff == 1;
  }
}
