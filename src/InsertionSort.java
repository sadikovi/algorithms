public class InsertionSort {
  public static void sort(int[] arr) {
    int numExchanges = 0;

    for (int i = 0; i < arr.length; i++) {
      int j = i;
      while (j > 0 && arr[j] < arr[j - 1]) {
        int tmp = arr[j];
        arr[j] = arr[j - 1];
        arr[j - 1] = tmp;
        j--;

        numExchanges++;
      }
    }

    System.out.println("Number of exchanges: " + numExchanges);
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
