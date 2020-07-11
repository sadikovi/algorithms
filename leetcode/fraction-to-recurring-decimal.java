// My solution runs in O(F) time and uses O(F) space, where F is the length of the fractional part.
// Beats 99.76% of submissions.
//
// Test cases:
// 1/2, 1/7, 1/70, 2/1, 0/-5, -1/-2147483648, 2147483647/-2147483648
//
class Solution {
  public String fractionToDecimal(int numerator, int denominator) {
    if (denominator == 0) throw new IllegalArgumentException();
    if (numerator == 0) return "0";

    StringBuilder sb = new StringBuilder();

    // Figure out the sign
    if (numerator > 0 ^ denominator > 0) {
      sb.append("-");
    }

    // Integer part
    long num = Math.abs((long) numerator);
    long denom = Math.abs((long) denominator);

    sb.append(num / denom);

    // Fractional part
    HashMap<Long, Integer> fmap = new HashMap<Long, Integer>();

    num %= denom;

    if (num > 0) {
      sb.append(".");
    }

    while (num > 0 && !fmap.containsKey(num)) {
      fmap.put(num, sb.length());
      num *= 10;
      sb.append(num / denom);
      num %= denom;
    }

    if (fmap.containsKey(num)) {
      sb.insert(fmap.get(num), "(");
      sb.append(")");
    }

    return sb.toString();
  }
}

// My first solution, has the same time and space complexity but is slower than the first solution.
class Solution {
  public String fractionToDecimal(int numerator, int denominator) {
    if (denominator == 0) throw new IllegalArgumentException();
    if (numerator == 0) return "0";
    boolean negative = numerator > 0 ^ denominator > 0;
    long num = Math.abs((long) numerator);
    long denom = Math.abs((long) denominator);
    long dpart = num / denom;
    String fpart = fractional(num % denom, denom);
    return (negative ? "-" : "") + dpart + (fpart.isEmpty() ? "" : ("." + fpart));
  }

  private String fractional(long num, long denom) {
    StringBuilder sb = new StringBuilder();
    HashMap<Long, Integer> fmap = new HashMap<Long, Integer>();

    while (num > 0 && !fmap.containsKey(num)) {
      fmap.put(num, sb.length());
      num *= 10;
      sb.append(num / denom);
      num %= denom;
    }

    if (fmap.containsKey(num)) {
      sb.insert(fmap.get(num), "(");
      sb.append(")");
    }

    return sb.toString();
  }
}
