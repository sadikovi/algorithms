// Solution runs in O(n) + O(log(a+b)) time, which is still linear for small numbers.
// Uses O(n) space to store fractions.
// Beats 84.36% of submissions.
class Solution {
  public String fractionAddition(String expression) {
    String exp = (!expression.startsWith("-")) ? "+" + expression : expression;
    List<int[]> expr = new ArrayList<int[]>();
    int i = 0;
    while (i < exp.length()) {
      if (exp.charAt(i) == '-' || exp.charAt(i) == '+') {
        int[] fraction = new int[2];
        int j = i;
        while (exp.charAt(j) != '/') j++;
        fraction[0] = Integer.parseInt(exp.substring(i, j));
        i = ++j;
        while (j < exp.length() && exp.charAt(j) != '-' && exp.charAt(j) != '+') j++;
        fraction[1] = Integer.parseInt(exp.substring(i, j));
        expr.add(fraction);
        i = j;
      } else {
        i++;
      }
    }

    int[] res = new int[]{0, 1};
    for (int[] arr : expr) {
      fold(res, arr);
    }
    return res[0] + "/" + res[1];
  }

  // update res in place by adding/subtracting arr fraction
  private void fold(int[] res, int[] arr) {
    int denom = res[1] * arr[1];
    int num = res[0]*arr[1] + arr[0]*res[1];
    int g = gcd(Math.abs(num), Math.abs(denom));
    res[0] = num / g;
    res[1] = denom / g;
  }

  // time complexity O(log(a + b))
  private int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
  }
}
