public class Solution {
  // Key is checking leftmost bit, e.g. defining odd or even number.
  // If m < n then there is at least one number that has different bit, therefore resulting bit
  // will be set to zero. If m == n then we still need to check that leftmost bit on both values
  // is set to one, otherwise it will also be set to zero.
  public int rangeBitwiseAnd(int m, int n) {
    int shift = 0;
    int res = 0;
    while (m != 0 && n != 0) {
      if (m == n && (m & 1) == 1 && (n & 1) == 1) {
        res |= 1 << shift;
      }
      m >>= 1;
      n >>= 1;
      shift++;
    }
    return res;
  }
}
