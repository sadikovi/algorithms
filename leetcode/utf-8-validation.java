// Solution runs in O(n) time and O(1) space, where n is the number of bytes in data
//
// This is how the UTF-8 encoding would work:
//
// Char. number range  |        UTF-8 octet sequence
//    (hexadecimal)    |              (binary)
// --------------------+---------------------------------------------
// 0000 0000-0000 007F | 0xxxxxxx
// 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
// 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
// 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
//
class Solution {
  public boolean validUtf8(int[] data) {
    int i = 0;
    while (i < data.length) {
      int bytes = numBytes(data[i]);
      if (bytes == -1 || (i + bytes) > data.length) return false;
      if (!utf(data, i, bytes)) return false;
      i += bytes;
    }
    return true;
  }

  private int numBytes(int octet) {
    if ((octet >> 7) == 0) return 1;
    if ((octet >> 3) == 30) return 4;
    if ((octet >> 4) == 14) return 3;
    if ((octet >> 5) == 6) return 2;
    return -1;
  }

  private boolean utf(int[] bytes, int start, int len) {
    for (int i = start + 1; i < start + len; i++) {
      if ((bytes[i] >> 6) != 2)  return false;
    }
    return true;
  }
}
