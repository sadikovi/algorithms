// Like a boss: single pass of O(n) time and O(n) space
// Idea is using dynamic programming to reuse previous results.
// Every power of 2 has 1 bit set, every odd number is produced
// adding 1 bit to the previous (even) number. Even number has
// the same number of bits set as number >> 1 position, because
// even number has at least one trailing zero that allows to
// bit shift (can use logical shift there).
public class Solution {
  public int[] countBits(int num) {
    int[] bits = new int[num + 1];
    for (int i = 1; i <= num; i++) {
      if (((i - 1) & i) == 0) {
        bits[i] = 1;
      } else if ((i & 1) > 0) {
        bits[i] = bits[i - 1] + 1;
      } else {
        bits[i] = bits[i >> 1];
      }
    }
    return bits;
  }
}
