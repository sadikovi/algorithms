// Solution runs in O(n) time and O(n) space, where n is max(num1.length(), num2.length())
class Solution {
  public String addStrings(String num1, String num2) {
    if (num1.length() == 0 || num2.length() == 0) return "";
    int len = Math.max(num1.length(), num2.length());
    int carry = 0;
    char[] arr = new char[len + 1];
    for (int i = 0; i < len; i++) {
      int d1 = i < num1.length() ? (num1.charAt(num1.length() - i - 1) - '0') : 0;
      int d2 = i < num2.length() ? (num2.charAt(num2.length() - i - 1) - '0') : 0;
      int sum = d1 + d2 + carry;
      carry = sum / 10;
      sum %= 10;
      arr[arr.length - i - 1] = (char) ('0' + sum);
    }
    if (carry > 0) {
      arr[0] = (char) ('0' + carry);
      return new String(arr);
    } else {
      return new String(arr, 1, len);
    }
  }
}
