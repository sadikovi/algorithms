package test;

/**
scala> test.Test.palindromeLen("")
res1: Int = 0

scala> test.Test.palindromeLen("a")
res2: Int = 1

scala> test.Test.palindromeLen("ab")
res3: Int = 0

scala> test.Test.palindromeLen("abc")
res4: Int = 0

scala> test.Test.palindromeLen("abca")
res5: Int = 3

scala> test.Test.palindromeLen("volvo")
res6: Int = 3

scala> test.Test.palindromeLen("merchant")
res7: Int = 0

scala> test.Test.palindromeLen("ghiabcdefhelloadamhelloabcdefghi")
res8: Int = 7

scala> test.Test.palindromeLen("abcxacxba")
res9: Int = 7
*/
public class Test {
  private static int internalLen(String str) {
    if (str == null || str.isEmpty()) return 0;
    int low = 0;
    int high = str.length() - 1;
    String left;
    String right;
    while (low < high) {
      left = str.substring(0, low + 1);
      right = str.substring(high);
      if (left.equals(right)) break;
      low++;
      high--;
    }
    if (low >= high) return 1;
    return 2 + internalLen(str.substring(low + 1, high));
  }

  public static int palindromeLen(String str) {
    int len = internalLen(str);
    if (len == 1 && str.length() > 1) return 0;
    return len;
  }
}
