// Problem statement:
// Select triplets and compute medians of each, then take the result and do the same by computing
// triplet medians over and over until there is only 1 number left. Return this number.
//
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
//
// The O(n^2) solution is simple but results in Time Limit Exceeded,
// O(n * log(n)) solution is more involved but it passes 10^6 test case.
//
class GFG {
  // O(n^2) solution
  public static int tripletMedian(int[] arr) {
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

  // O(n * log(n)) solution
  public static int tripletMedian2(int[] arr) {
    boolean[] buf = new boolean[arr.length];
    int start = 1, end = arr.length; // arr contains numbers from 1 to n
    while (start < end) {
      int mid = (start + end) / 2;
      if (!computeBinary(arr, mid, buf)) { // false = 0, true = 1
        end = mid;
      } else {
        start = mid + 1;
      }
    }
    return start;
  }

  private static int mean(int a, int b, int c) {
    int[] tmp = new int[] {a, b, c};
    java.util.Arrays.sort(tmp);
    return tmp[1];
  }

  private static boolean computeBinary(int[] arr, int mid, boolean[] buf) {
    // generate sequence of 0s and 1s
    for (int i = 0; i < arr.length; i++) {
      buf[i] = arr[i] > mid;
    }

    int len = buf.length;
    while (len >= 3) {
      int m = len / 2;
      // if middle element and it's neighbour are of the same value then we can return directly,
      // because they would be carried on to the end.
      // e.g. [.. 1, 1, ..] or [.. 0, 0, ..].
      if (buf[m - 1] && buf[m] || buf[m] && buf[m + 1]) return true;
      if (!buf[m - 1] && !buf[m] || !buf[m] && !buf[m + 1]) return false;

      for (int i = 0; i < len - 2; i++) {
        // it is 1 when [1, 1, 0], [0, 1, 1], or [1, 0, 1], otherwise it is 0
        buf[i] = buf[i] && buf[i + 1] || buf[i] && buf[i + 2] || buf[i + 1] && buf[i + 2];
      }
      len -= 2;
    }
    return buf[0]; // will be the first element
  }

  // generates permutation for the first n numbers
  private static int[] permutation(int n) {
    int[] arr = new int[n];
    java.util.List<Integer> tmp = new java.util.ArrayList<Integer>();
    for (int i = 1; i <= n; i++) {
      tmp.add(i);
    }
    java.util.Collections.shuffle(tmp);
    for (int i = 0; i < n; i++) {
      arr[i] = tmp.get(i);
    }
    return arr;
  }

  public static void main (String[] args) {
    // int[] arr = new int[] {3, 9, 1, 5, 8, 2, 4, 11, 7, 6, 10}; // answer: 4
    int[] arr = new int[] {3, 9, 8, 5, 1, 2, 7, 11, 4, 6, 10}; // answer: 2
    System.out.println(tripletMean(arr));

    int[] arr2 = permutation(1000001);
    System.out.println(tripletMedian2(arr2));
  }
}
