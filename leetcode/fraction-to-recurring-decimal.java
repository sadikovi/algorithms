// Solution from leetcode:
// https://discuss.leetcode.com/topic/7876/my-clean-java-solution/2
class Solution {
  public String fractionToDecimal(int numerator, int denominator) {
    if (numerator == 0) return "0";
    StringBuilder sb = new StringBuilder();
    if (numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0) {
      sb.append("-");
    }
    long num = Math.abs((long) numerator);
    long denom = Math.abs((long) denominator);
    // integral part
    sb.append(num / denom);
    num %= denom;
    if (num == 0) return sb.toString();

    // fractional part
    sb.append(".");
    HashMap<Long, Integer> map = new HashMap<Long, Integer>();
    map.put(num, sb.length());
    while (num != 0) {
      num *= 10;
      sb.append(num / denom);
      num %= denom;
      if (map.containsKey(num)) {
        int index = map.get(num);
        sb.insert(index, "(");
        sb.append(")");
        break;
      }
      map.put(num, sb.length());
    }
    return sb.toString();
  }
}

// My recursive solution.
// Beats 28.35% of submissions
class Solution {
  public String fractionToDecimal(int numerator, int denominator) {
    boolean pos = numerator >= 0 && denominator >= 0 || numerator <= 0 && denominator <= 0;
    long num = Math.abs((long) numerator);
    long denom = Math.abs((long) denominator);
    StringBuilder sb = new StringBuilder();
    if (!pos) sb.append("-");
    sb.append(num / denom);
    if (num % denom != 0) {
      sb.append(".");
      fraction((num % denom) * 10, denom, sb.length(), sb, new HashMap<String, Integer>());
    }
    return sb.toString();
  }

  private void fraction(long num, long denom, int pos, StringBuilder sb, HashMap<String, Integer> map) {
    if (num % denom == 0) {
        sb.append(num / denom);
    } else {
      Integer idx = map.get(num + "-" + denom);
      if (idx != null) {
        sb.insert(idx, "(");
        sb.append(")");
      } else {
        map.put(num + "-" + denom, pos);
        sb.append(num / denom);
        fraction((num % denom) * 10, denom, pos+1, sb, map);
      }
    }
  }
}
