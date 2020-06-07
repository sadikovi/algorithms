public class MergeSort {
  public static void sort(int[] arr) {
    int[] tmp = new int[arr.length];
    sort(arr, 0, arr.length - 1, tmp);
  }

  private static void sort(int[] arr, int start, int end, int[] tmp) {
    if (start < end) {
      int mid = start + (end - start) / 2;
      sort(arr, start, mid, tmp);
      sort(arr, mid + 1, end, tmp);
      merge(arr, start, mid, end, tmp);
    }
  }

  private static void merge(int[] arr, int start, int mid, int end, int[] tmp) {
    for (int i = start; i <= end; i++) {
      tmp[i] = arr[i];
    }

    int s1 = start, s2 = mid + 1, k = start;

    while (s1 <= mid && s2 <= end) {
      if (tmp[s1] < tmp[s2]) {
        arr[k++] = tmp[s1++];
      } else {
        arr[k++] = tmp[s2++];
      }
    }

    while (s1 <= mid) {
      arr[k++] = tmp[s1++];
    }
  }

  public static void main(String[] args) {
    // int[] arr = new int[] { 10, 2, 3, 4, 1, 0, 6, 9, 8, 5, 7 };
    // int[] arr = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    int[] arr = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    System.out.println("Input: " + java.util.Arrays.toString(arr));
    sort(arr);
    System.out.println("Result: " + java.util.Arrays.toString(arr));
  }
}
