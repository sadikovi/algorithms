// Naive recursive solution, runs in O(K!) time - K! branches, where K is the number of operators,
// and O(n) space for recursive calls, where n is the length of the string.
class Solution {
  public List<Integer> diffWaysToCompute(String input) {
    List<Integer> res = new ArrayList<Integer>();
    for (int i = 0; i < input.length(); i++) {
      char t = input.charAt(i);
      if (t == '+' || t == '-' || t == '*') {
        List<Integer> left = diffWaysToCompute(input.substring(0, i));
        List<Integer> right = diffWaysToCompute(input.substring(i+1));
        for (int a : left) {
          for (int b : right) {
            if (t == '+') res.add(a + b);
            else if (t == '-') res.add(a - b);
            else if (t == '*') res.add(a * b);
          }
        }
      }
    }
    if (res.size() == 0) {
      res.add(Integer.parseInt(input));
    }
    return res;
  }
}

// Solution with memoization, beats 94.97% of submissions.
class Solution {
  public List<Integer> diffWaysToCompute(String input) {
    return helper(input, new HashMap<String, List<Integer>>());
  }

  private List<Integer> helper(String input, HashMap<String, List<Integer>> memo) {
    if (memo.containsKey(input)) return memo.get(input);
    List<Integer> res = new ArrayList<Integer>();
    for (int i = 0; i < input.length(); i++) {
      char t = input.charAt(i);
      if (t == '+' || t == '-' || t == '*') {
        List<Integer> left = helper(input.substring(0, i), memo);
        List<Integer> right = helper(input.substring(i+1), memo);
        for (int a : left) {
          for (int b : right) {
            if (t == '+') res.add(a + b);
            else if (t == '-') res.add(a - b);
            else if (t == '*') res.add(a * b);
          }
        }
      }
    }
    if (res.size() == 0) {
      res.add(Integer.parseInt(input));
    }
    memo.put(input, res);
    return res;
  }
}
