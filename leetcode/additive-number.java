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
