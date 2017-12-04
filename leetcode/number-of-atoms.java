// Solution runs in O(n) time even with recursive calls and uses O(n) space (technically we need
// to store map of atom - count). Runtime varies - beats from 30% to 66% of submissions.
//
// Idea is recursive parsing, where for each "(" - ")" we recurse to parse inner expression and
// then merge maps.
class Solution {
  public String countOfAtoms(String formula) {
    TreeMap<String, Integer> map = counts(formula);
    StringBuilder sb = new StringBuilder();
    for (String key : map.keySet()) {
      sb.append(key);
      if (map.get(key) > 1) {
        sb.append(map.get(key));
      }
    }
    return sb.toString();
  }

  private TreeMap<String, Integer> counts(String formula) {
    TreeMap<String, Integer> map = new TreeMap<String, Integer>();
    int i = 0, n = formula.length();
    String last = null;
    while (i < n) {
      char t = formula.charAt(i);
      ++i;
      if (t == '(') {
        // find closing paren
        int open = 1, start = i;
        while (i < n && open > 0) {
          if (formula.charAt(i) == '(') open++;
          if (formula.charAt(i) == ')') open--;
          if (open == 0) break;
          ++i;
        }

        String tmp = formula.substring(start, i++);
        int pcount = 0;
        while (i < n && formula.charAt(i) >= '0' && formula.charAt(i) <= '9') {
          pcount = pcount * 10 + (formula.charAt(i) - '0');
          ++i;
        }
        if (pcount == 0) pcount = 1;
        TreeMap<String, Integer> res = counts(tmp);
        for (String key : res.keySet()) {
          map.put(key, map.getOrDefault(key, 0) + pcount * res.get(key));
        }
      } else if (t >= '0' && t <= '9') {
        StringBuilder sb = new StringBuilder();
        sb.append(t);
        while (i < n && formula.charAt(i) >= '0' && formula.charAt(i) <= '9') {
          sb.append(formula.charAt(i));
          ++i;
        }
        if (last != null) {
          map.put(last, map.getOrDefault(last, 0) + Integer.parseInt(sb.toString()));
          last = null;
        }
      } else if (t >= 'A' && t <= 'Z' || t >= 'a' && t <= 'z') {
        if (last != null) {
          map.put(last, map.getOrDefault(last, 0) + 1);
          last = null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(t);
        while (i < n && formula.charAt(i) >= 'a' && formula.charAt(i) <= 'z') {
          sb.append(formula.charAt(i));
          ++i;
        }
        last = sb.toString();
      }
    }
    if (last != null) {
      map.put(last, map.getOrDefault(last, 0) + 1);
    }
    return map;
  }
}
