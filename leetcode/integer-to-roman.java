// Solution runs in O(1) time and O(1) space.
// Fairly simple problem, we just encode all numbers in array and access elements by position.
class Solution {
  private static final String[] ones = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
  private static final String[] tens = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
  private static final String[] hundreds = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
  private static final String[] thousands = new String[]{"", "M", "MM", "MMM"};

  public String intToRoman(int num) {
    // Symbol	I	V	X	L	C	D	M
    // Value	1	5	10	50	100	500	1,000
    String a = ones[num % 10];
    String b = tens[(num / 10) % 10];
    String c = hundreds[(num / 100) % 10];
    String d = thousands[(num / 1000) % 10];
    return d + c + b + a;
  }
}

// Solution runs in O(1) time and O(1) space. We exploit fact that all ranges are similar and
// can be represented as lookup method.
class Solution {
  public String intToRoman(int num) {
    // Symbol	I	V	X	L	C	D	M
    // Value	1	5	10	50	100	500	1,000
    String ones = convert(num % 10, 'I', 'V', 'X');
    String tens = convert(num / 10 % 10, 'X', 'L', 'C');
    String hundreds = convert(num / 100 % 10, 'C', 'D', 'M');
    String thousands = convert(num / 1000 % 10, 'M', 'M', 'M');
    return thousands + hundreds + tens + ones;
  }

  private String convert(int num, char one, char five, char ten) {
    switch (num) {
      case 1: return "" + one;
      case 2: return "" + one + "" + one;
      case 3: return "" + one + "" + one + "" + one;
      case 4: return "" + one + "" + five;
      case 5: return "" + five;
      case 6: return "" + five + "" + one;
      case 7: return "" + five + "" + one + "" + one;
      case 8: return "" + five + "" + one + "" + one + "" + one;
      case 9: return "" + one + "" + ten;
      default: return "";
    }
  }
}
