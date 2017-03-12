public class Solution {
  // you need treat n as an unsigned value
  public int reverseBits(int n) {
    boolean flip = (n & (1 << 31)) == 0;
    n |= 1 << 31;
    int res = 0;
    while (n != 0) {
      res <<= 1;
      res |= n & 1;
      n >>>= 1;
    }
    return flip ? (res ^ 1) : res;
  }
}
