public class Solution {
  public List<String> restoreIpAddresses(String s) {
    List<String> res = new ArrayList<String>();
    if (s == null || s.length() < 4 || s.length() > 12) return res;
    restore(s, "", 4, res);
    return res;
  }

  private void restore(String s, String prefix, int octets, List<String> arr) {
    if (s.isEmpty() && octets != 0) return;
    if (s.isEmpty() && octets == 0) {
      arr.add(prefix);
      return;
    }

    int octetLen = Math.min(3, s.length());
    for (int i = 1; i <= octetLen; i++) {
      String pr = s.substring(0, i);
      if (isValidOctet(pr)) {
        String ext = prefix.isEmpty() ? pr : prefix + "." + pr;
        restore(s.substring(i), ext, octets - 1, arr);
      }
    }
  }

  private boolean isValidOctet(String str) {
    if (str.length() > 1 && str.charAt(0) == '0') return false;
    return str.length() <= 3 && Integer.parseInt(str) < 256;
  }
}
