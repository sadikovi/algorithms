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
