// Solution with memoization, beats 76.18% of submissions.
// Runs in O(n^2 * m) time and O(n * m) space (?)
class Solution {
  public List<String> wordBreak(String s, List<String> wordDict) {
    return recur(s, wordDict, new HashMap<String, List<String>>());
  }

  private List<String> recur(String s, List<String> wordDict, HashMap<String, List<String>> memo) {
    if (memo.containsKey(s)) return memo.get(s);
    List<String> res = new ArrayList<String>();
    for (String word : wordDict) {
      if (s.equals(word)) {
        res.add(word);
      } else if (s.startsWith(word)) {
        for (String sentence : recur(s.substring(word.length()), wordDict, memo)) {
          res.add(word + " " + sentence);
        }
      }
    }
    memo.put(s, res);
    return res;
  }
}
