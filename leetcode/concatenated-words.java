// Recursive solution
public class Solution {
  public List<String> findAllConcatenatedWordsInADict(String[] words) {
    List<String> res = new ArrayList<String>();
    for (int i = 0; i < words.length; i++) {
      findWords(words, words[i], 0, res);
    }
    return res;
  }

  private boolean findWords(String[] words, String str, int start, List<String> res) {
    if (str.isEmpty() || start > str.length()) return false;
    if (start == str.length()) {
      res.add(str);
      return true;
    }
    for (String word : words) {
      if (!word.isEmpty() && word.length() < str.length() && str.startsWith(word, start)) {
        if (findWords(words, str, start + word.length(), res)) {
          return true;
        }
      }
    }
      return false;
  }
}

// DP solution
// taken from https://discuss.leetcode.com/topic/72113/java-dp-solution
public class Solution {
  public List<String> findAllConcatenatedWordsInADict(String[] words) {
    Arrays.sort(words, new Comparator<String>() {
      public int compare(String a, String b) {
        return a.length() - b.length();
      }
    });

    List<String> res = new ArrayList<String>();
    HashSet<String> preWords = new HashSet<String>();
    for (int i = 0; i < words.length; i++) {
      if (canForm(words[i], preWords)) {
        res.add(words[i]);
      }
      preWords.add(words[i]);
    }
    return res;
  }

  private boolean canForm(String word, HashSet<String> preWords) {
    if (preWords.isEmpty()) return false;
    boolean[] dp = new boolean[word.length() + 1];
    dp[0] = true;
    for (int i = 1; i <= word.length(); i++) {
      for (int j = 0; j < i; j++) {
        if (!dp[j]) continue;
        if (preWords.contains(word.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[word.length()];
  }
}
