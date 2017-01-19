// Fairly efficient solution, passes test cases, beats 31.04% of other solutions
public class Solution {
  public List<String> wordBreak(String s, List<String> wordDict) {
    return wordBreak(s, new HashSet<String>(wordDict), new HashMap<String, List<String>>());
  }

  private List<String> wordBreak(String s, HashSet<String> wordDict, HashMap<String, List<String>> memo) {
    if (memo.containsKey(s)) return memo.get(s);
    List<String> res = new ArrayList<String>();
    for (int i = 0; i < s.length(); i++) {
      String left = s.substring(0, i + 1);
      if (wordDict.contains(left)) {
        String right = s.substring(i + 1);
        if (right.isEmpty()) {
          res.add(left); // add left part as an entire sentence
        } else {
          List<String> sentences = wordBreak(right, wordDict, memo);
          if (!sentences.isEmpty()) {
            for (String str : sentences) {
              res.add(left + " " + str);
            }
          }
        }
      }
    }
    memo.put(s, res);
    return res;
  }
}
