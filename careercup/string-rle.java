package test;

/*
scala> val lst = Array("aaa", "bbb", "aaabbc", "d")
lst: Array[String] = Array(aaa, bbb, aaabbc, d)
scala> test.Test.rle(lst)
res0: String = a31b31a3b2c11d11

scala> val lst = Array("abcd", "aaaaaaaaaaaaa", "aaaaabcbc")
lst: Array[String] = Array(abcd, aaaaaaaaaaaaa, aaaaabcbc)
scala> test.Test.rle(lst)
res1: String = abcd01a9a41a5b1c1b1c11
*/

public class Test {
  private static String rle(String str) {
    if (str == null || str.isEmpty()) return str;
    StringBuilder sb = new StringBuilder();
    char t = str.charAt(0);
    int counter = 1;
    for (int i = 1; i < str.length(); i++) {
      char c = str.charAt(i);
      if (t == c) {
        counter++;
      } else {
        sb.append(t);
        sb.append(counter);
        t = c;
        counter = 1;
      }

      if (counter == 10) {
        sb.append(t);
        sb.append(counter - 1); // keep only single digit counter
        counter = 1;
      }
    }

    sb.append(t);
    sb.append(counter);
    if (sb.length() > str.length() + 1) {
      return str + "0"; // suffix 0 to indicate end of string
    } else {
      return sb.toString();
    }
  }

  public static String rle(String[] strs) {
    StringBuilder sb = new StringBuilder();
    String last = null;
    int counter = 0;
    for (String str : strs) {
      String enc = rle(str);
      if (last != null && last.equals(enc)) {
        counter++;
      } else if (last == null) {
        last = enc;
        counter = 1;
      } else {
        sb.append(last);
        sb.append(counter);
        last = enc;
        counter = 1;
      }
    }

    if (last != null) {
      sb.append(last);
      sb.append(counter);
    }
    return sb.toString();
  }
}
