package test;

public class Test {
  public static boolean match(String value, String pattern) {
    return match(value, pattern, null, null);
  }

  private static boolean match(String value, String pattern, String a, String b) {
    if (value.isEmpty() && pattern.isEmpty()) return true;
    if (value.isEmpty() || pattern.isEmpty()) return false;
    char t = pattern.charAt(0);
    if (t == 'a') {
      if (a == null) {
        for (int i = 1; i <= value.length(); i++) {
          if (match(value, pattern, value.substring(0, i), b)) {
            return true;
          }
        }
        return false;
      } else {
        if (!value.startsWith(a)) return false;
        return match(value.substring(a.length()), pattern.substring(1), a, b);
      }
    } else {
      if (b == null) {
        for (int i = 1; i <= value.length(); i++) {
          if (match(value, pattern, a, value.substring(0, i))) {
            return true;
          }
        }
        return false;
      } else {
        if (!value.startsWith(b)) return false;
        return match(value.substring(b.length()), pattern.substring(1), a, b);
      }
    }
  }
}
