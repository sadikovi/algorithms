public class Solution {
  private static final String[] map = new String[10];
  static {
    map[2] = "abc";
    map[3] = "def";
    map[4] = "ghi";
    map[5] = "jkl";
    map[6] = "mno";
    map[7] = "pqrs";
    map[8] = "tuv";
    map[9] = "wxyz";
  }

  public List<String> letterCombinations(String digits) {
    for (int i = 0; i < digits.length(); i++) {
      if (digits.charAt(i) < '2' || digits.charAt(i) > '9') {
        return new ArrayList<String>();
      }
    }
    return permutations(digits);
  }

  private List<String> permutations(String digits) {
    List<String> all = new ArrayList<String>();
    if (digits.length() == 1) {
      String letters = map[digits.charAt(0) - '0'];
      for (int i = 0; i < letters.length(); i++) {
        all.add("" + letters.charAt(i));
      }
    } else if (digits.length() > 1) {
      List<String> tmp = letterCombinations(digits.substring(1));
      String letters = map[digits.charAt(0) - '0'];
      for (int i = 0; i < letters.length(); i++) {
        for (String str : tmp) {
          all.add("" + letters.charAt(i) + str);
        }
      }
    }
    return all;
  }
}
