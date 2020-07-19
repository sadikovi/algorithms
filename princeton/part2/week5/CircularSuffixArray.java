public class CircularSuffixArray {
  private final int n;
  private final int[] index;

  // circular suffix array of s
  public CircularSuffixArray(String s) {
    if (s == null) throw new IllegalArgumentException();
    this.n = s.length();
    this.index = new int[n];

    // System.out.println("Original suffixes");
    // for (int i = 0; i < n; i++) {
    //   char[] r = rotate(s, i);
    //   System.out.println(i + ":\t" + java.util.Arrays.toString(r));
    // }

    for (int i = 0; i < n; i++) {
      this.index[i] = i;
    }

    // Use three-way quicksort to sort suffixes efficiently.
    sort(this.index, 0, n - 1, s, new int[n]);

    // System.out.println("Sorted suffixes");
    // for (int i = 0; i < n; i++) {
    //   char[] r = rotate(s, index[i]);
    //   System.out.println(i + ":\t" + java.util.Arrays.toString(r));
    // }
    //
    // System.out.println(java.util.Arrays.toString(index));

    // int first = 3;
    // String t = "ARD!RCAAAABB";
    // int[] next = new int[t.length()];
  }

  // Rotates the string at index i
  // private char[] rotate(String s, int i) {
  //   char[] arr = new char[s.length()];
  //   int j = 0;
  //
  //   while (j < s.length() - i) {
  //     arr[j] = s.charAt(i + j);
  //     j++;
  //   }
  //
  //   while (j < s.length()) {
  //     arr[j] = s.charAt(i + j - s.length());
  //     j++;
  //   }
  //
  //   return arr;
  // }

  private static void sort(int[] arr, int start, int end, String s, int[] tmp) {
    if (start < end) {
      int mid = (start + end) / 2;
      sort(arr, start, mid, s, tmp);
      sort(arr, mid + 1, end, s, tmp);
      merge(arr, start, mid, end, s, tmp);
    }
  }

  private static void merge(int[] arr, int start, int mid, int end, String s, int[] tmp) {
    int s0 = start, e0 = mid;
    int s1 = mid + 1, e1 = end;

    for (int i = 0; i < tmp.length; i++) {
      tmp[i] = arr[i];
    }

    int k = start;
    while (s0 <= e0 && s1 <= e1) {
      int cmp = compare(s, tmp[s0], tmp[s1]);
      if (cmp <= 0) {
        arr[k++] = tmp[s0++];
      } else {
        arr[k++] = tmp[s1++];
      }
    }

    while (s0 <= e0) arr[k++] = tmp[s0++];
  }

  private static int compare(String s, int a, int b) {
    int len = s.length();
    for (int i = 0; i < len; i++) {
      char ca = s.charAt((a + i) % len);
      char cb = s.charAt((b + i) % len);
      if (ca < cb) return -1;
      if (ca > cb) return 1;
    }
    return 0;
  }

  // length of s
  public int length() {
    return n;
  }

  // returns index of ith sorted suffix
  public int index(int i) {
    if (i < 0 || i >= n) throw new IllegalArgumentException();
    return index[i];
  }

  // unit testing (required)
  public static void main(String[] args) {
    // CircularSuffixArray arr = new CircularSuffixArray("ABRACADABRA!");
    // CircularSuffixArray arr = new CircularSuffixArray("ARD!RCAAAABB");
    // CircularSuffixArray arr = new CircularSuffixArray("AAAAAAAAAA");
  }
}
