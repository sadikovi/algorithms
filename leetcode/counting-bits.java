// Naive solution, runs in O(n * size of integer) time, O(n) space
public class Solution {
  public int[] countBits(int num) {
    int[] bits = new int[num + 1];
    for (int i = 0; i <= num; i++) {
      bits[i] = numBits(i);
    }
    return bits;
  }

  private int numBits(int i) {
    int bits = 0;
    while (i != 0) {
      if ((i & 1) == 1) bits++;
      i >>>= 1;
    }
    return bits;
  }
}

// Solution runs in O(n) time and O(n) space (like a boss).
// Idea is that there are three types of situations:

// ... -> power of 2 -> odd -> even -> odd -> even -> ...

// 1. number is power of 2, this always has 1 bit.
// 2. number is odd, this always has previous number of bits + 1, since there is empty slot at LSB.
// 3. number is even, personally think this is gotcha, you need to find that even number has the
// same number of bits as this number / 2. In other words, if ith number is even, it has the same
// number of bits as (i/2)-th number. It works because division by 2 is right shift by 1 (num >> 1).
// It is easy to discover by building sequence of bits from 0 to 16. You will observe that on
// transitions 5 -> 6, 9 -> 10, 11 -> 12.
public class Solution {
  public int[] countBits(int num) {
    int[] bits = new int[num + 1];
    for (int i = 1; i <= num; i++) {
      if ((i & (i - 1)) == 0) {
        bits[i] = 1;
      } else if (i % 2 == 1) {
        bits[i] = bits[i - 1] + 1;
      } else {
        bits[i] = bits[i / 2]; // same as bits[i >> 1]
      }
    }
    return bits;
  }
}
