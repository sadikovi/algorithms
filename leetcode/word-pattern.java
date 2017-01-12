public class Solution {
  public boolean wordPattern(String pattern, String str) {
    if (pattern == null || str == null || pattern.isEmpty() || str.isEmpty()) return false;
    String[] arr = str.split(" ");
    if (pattern.length() != arr.length) return false;

    String[] map = new String[128];
    HashSet<String> set = new HashSet<String>();
    for (int i = 0; i < arr.length; i++) {
      if (map[pattern.charAt(i)] == null) {
        if (set.contains(arr[i])) return false;
        map[pattern.charAt(i)] = arr[i];
        set.add(arr[i]);
      } else if (!map[pattern.charAt(i)].equals(arr[i])) {
        return false;
      }
    }

    return true;
  }
}
