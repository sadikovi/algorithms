// Code to implement Rabin-Karp substring search
public class RabinKarpSearch {
  // Naive implementation of substring search
  private static int naiveFindSubstring(String word, String text) {
    int total = 0;
    if (word.length() == 0 || text.length() < word.length()) return total;

    int hits = 0;
    for (int i = 0; i <= text.length() - word.length(); i++) {
      if (text.charAt(i) == word.charAt(0)) {
        hits++;
        boolean match = true;
        for (int j = 1; j < word.length(); j++) {
          if (word.charAt(j) != text.charAt(i + j)) {
            match = false;
            break;
          }
        }
        if (match) {
          total++;
        }
      }
    }
    System.out.println("Naive hits: " + hits);
    return total;
  }

  private static int code(char t) {
    return 1 << (7 * t);
  }

  // Find all occurrences of "word" in "text" using Rabin-Karp substring search
  // characters are a-z
  private static int findSubstrings(String word, String text) {
    int total = 0, b = text.length(), s = word.length();
    if (s == 0 || b < s) return total;
    // compute hash of word
    int wordHash = 0;
    for (int i = 0; i < s; i++) {
      wordHash += code(word.charAt(i));
    }

    // compute hash table of text
    int[] codes = new int[b];
    for (int i = 0; i < s; i++) {
      codes[0] += code(text.charAt(i));
    }
    for (int i = 1; i <= b - s; i++) {
      codes[i] = codes[i - 1] - code(text.charAt(i - 1)) + code(text.charAt(i - 1 + s));
    }

    // iterate and compare hash codes, if match - increment total
    int hits = 0;
    for (int i = 0; i <= b - s; i++) {
      if (codes[i] == wordHash) {
        hits++;
        boolean match = true;
        for (int j = 0; j < s; j++) {
          if (word.charAt(j) != text.charAt(i + j)) {
            match = false;
            break;
          }
        }
        if (match) {
          total++;
        }
      }
    }

    System.out.println("Rabin-Karp hits: " + hits);

    return total;
  }

  // == Performance comparison ==

  private static String loadText() {
    java.io.InputStream in = RabinKarpSearch.class.getResourceAsStream("/text.txt");
    byte[] values = new byte[15216];
    try {
      in.read(values);
      return new String(values);
    } catch (java.io.IOException err) {
      throw new RuntimeException(err);
    }
  }

  private static void benchNaive() {
    String text = loadText();
    String word = "ete";

    long start = System.nanoTime();
    int result = naiveFindSubstring(word, text);
    long end = System.nanoTime();

    System.out.println("Naive: found " + result + " occurrences, took " +
      ((end - start) / 1e6) + " ms");
  }

  private static void benchRabinKarp() {
    String text = loadText();
    String word = "ete";

    long start = System.nanoTime();
    int result = findSubstrings(word, text);
    long end = System.nanoTime();

    System.out.println("Rabin-Karp: found " + result + " occurrences, took " +
      ((end - start) / 1e6) + " ms");
  }

  public static void main(String[] args) {
    // Naive implementation
    int occurrences = naiveFindSubstring("ear", "doearehearingmearea");
    System.out.println("Found " + occurrences + " occurrences");

    occurrences = naiveFindSubstring("", "abc");
    System.out.println("Found " + occurrences + " occurrences");

    occurrences = naiveFindSubstring("", "");
    System.out.println("Found " + occurrences + " occurrences");

    // Rabin-Karp substring search
    occurrences = findSubstrings("ear", "doearehearingmearea");
    System.out.println("Found " + occurrences + " occurrences");

    occurrences = findSubstrings("", "abc");
    System.out.println("Found " + occurrences + " occurrences");

    occurrences = findSubstrings("", "");
    System.out.println("Found " + occurrences + " occurrences");

    // == Run benchmarks
    benchNaive();
    benchRabinKarp();
  }
}
