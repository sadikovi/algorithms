// Solution runs in O(N^2 + N * M), where N is the length of the words array and M is the max
// length of the string. Note that with large array it simply becomes O(N^2). Space is O(N).
class Solution {
  public int maxProduct(String[] words) {
    // build sets
    int[] sets = new int[words.length];
    for (int i = 0; i < words.length; i++) {
      sets[i] = bitset(words[i]);
    }

    int product = 0;
    // compare sets and find max product
    for (int i = 0; i < sets.length; i++) {
      for (int j = i+1; j < sets.length; j++) {
        if ((sets[i] & sets[j]) == 0) {
          product = Math.max(product, words[i].length() * words[j].length());
        }
      }
    }
    return product;
  }

  private int bitset(String str) {
    int set = 0;
    for (int i = 0; i < str.length(); i++) {
      set |= 1 << (str.charAt(i) - 'a');
    }
    return set;
  }
}
