// My solution, runs in O(n^2) time and uses O(n) space
// (though I am not quite sure on time complexity)
// To avoid overflow we could have used BigInteger, but I decided to perform addition directly on
// strings.
// Solution beats 93.97% of all submissions.
class Solution {
  public boolean isAdditiveNumber(String num) {
    for (int i = 1; i < num.length(); i++) {
      if (num.charAt(0) == '0' && i > 1) return false;
      String num1 = num.substring(0, i);
      for (int j = i + 1; j < num.length(); j++) {
        if (num.charAt(i) == '0' && j - i > 1) break;
        String num2 = num.substring(i, j);
        if (next(num1, num2, num.substring(j))) return true;
      }
    }
    return false;
  }

  private boolean next(String num1, String num2, String in) {
    if (num1.length() == 0 || num2.length() == 0) return false;
    if (in.length() == 0) return true;
    String add = add(num1, num2);
    if (add.length() > in.length()) return false;
    for (int i = 0; i < add.length(); i++) {
      if (add.charAt(i) != in.charAt(i)) return false;
    }
    return next(num2, add, in.substring(add.length()));
  }

  private String add(String a, String b) {
    int carry = 0, j = 0;
    char[] res = new char[Math.max(a.length(), b.length()) + 1];
    for (int i = res.length - 1; i >= 0; i--) {
      int anum = j < a.length() ? (a.charAt(a.length() - j - 1) - '0') : 0;
      int bnum = j < b.length() ? (b.charAt(b.length() - j - 1) - '0') : 0;
      int v = carry + anum + bnum;
      res[i] = (char) ('0' + v % 10);
      carry = v / 10;
      j++;
    }
    if (res[0] == '0') return new String(res, 1, res.length - 1);
    return new String(res);
  }
}

// Solution from Leetcode (?)
public class Solution {
  public boolean isAdditiveNumber(String num) {
    for (int i = 0; i < num.length() / 2; i++) {
      for (int j = i + 1; j < num.length(); j++) {
        String a = num.substring(0, i + 1);
        String b = num.substring(i + 1, j + 1);
        String sum = num.substring(j + 1);
        if (!sum.isEmpty() && canStart(a) && canStart(b)) {
          if (additive(sum, Long.parseLong(a), Long.parseLong(b))) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean canStart(String num) {
    return num.equals("0") || !num.startsWith("0");
  }

  private boolean additive(String str, long a, long b) {
    if (str.isEmpty()) return true;
    if (str.startsWith("0")) return false;
    long sum = a + b;
    String strSum = "" + sum;
    return str.startsWith(strSum) &&
      additive(str.substring(strSum.length()), b, sum);
  }
}
