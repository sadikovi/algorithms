// We assume the following:
// - input has at least 3 elements
// - input length is always odd
// - numbers are from 1 to n without any gaps or duplicates
//
// Examples:
// [3, 9, 1, 5, 8, 2, 4, 11, 7, 6, 10]; // answer: 4
// [3, 9, 8, 5, 1, 2, 7, 11, 4, 6, 10]; // answer: 2
// [2, 4, 3, 5, 1, 6, 7]; // answer: 4
// [2, 3, 6, 1, 7, 5, 9, 4, 8]; // answer: 5

class GFG {
  private static int tripletMean(int[] arr) {
    int[] tmp = new int[arr.length];
    System.arraycopy(arr, 0, tmp, 0, arr.length); // so we don't modify input

    int len = arr.length;

    while (len > 1) {
      int i = 0;
      while (i < len - 2) {
        tmp[i] = mean(tmp[i], tmp[i + 1], tmp[i + 2]);
        i++;
      }
      len -= 2;
    }
    return tmp[0];
  }

  private static int mean(int a, int b, int c) {
    int[] tmp = new int[] {a, b, c};
    java.util.Arrays.sort(tmp);
    return tmp[1];
  }

  public static void main (String[] args) {
    // int[] arr = new int[] {3, 9, 1, 5, 8, 2, 4, 11, 7, 6, 10}; // answer: 4
    int[] arr = new int[] {3, 9, 8, 5, 1, 2, 7, 11, 4, 6, 10}; // answer: 2
    System.out.println(tripletMean(arr));
  }
}
