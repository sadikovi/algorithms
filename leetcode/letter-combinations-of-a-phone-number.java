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

// Can also be solved like this:
// Time complexity is O(4^N), where N is the length of "digits".
// Space complexity is O(4^N), where N is the length of "digits".
class Solution {
  public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<String>();
    if (digits.length() == 0) return res;

    res.add("");
    for (int i = 0; i < digits.length(); i++) {
      char d = digits.charAt(i);
      List<String> tmp = new ArrayList<String>();
      for (String s : res) {
        for (String a : letters(d)) {
          tmp.add(s + a);
        }
      }
      res = tmp;
    }
    return res;
  }

  private String[] letters(char digit) {
    switch (digit) {
      case '2': return new String[]{"a", "b", "c"};
      case '3': return new String[]{"d", "e", "f"};
      case '4': return new String[]{"g", "h", "i"};
      case '5': return new String[]{"j", "k", "l"};
      case '6': return new String[]{"m", "n", "o"};
      case '7': return new String[]{"p", "q", "r", "s"};
      case '8': return new String[]{"t", "u", "v"};
      case '9': return new String[]{"w", "x", "y", "z"};
      default: return new String[]{};
    }
  }
}
