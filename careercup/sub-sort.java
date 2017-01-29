package test;

public class Test {
  static class Result {
    int i;
    int j;

    Result(int i, int j) {
      this.i = i;
      this.j = j;
    }

    @Override
    public String toString() {
      return "(i=" + i + ", j=" + j + ")";
    }
  }
  public static Result subsort(int[] arr) {
    if (arr == null) throw new IllegalArgumentException();
    int[] copy = new int[arr.length];
    System.arraycopy(arr, 0, copy, 0, arr.length);
    java.util.Arrays.sort(copy);

    int i = 0;
    int j = arr.length - 1;
    boolean foundI = false;
    boolean foundJ = false;
    while (i < j) {
      if (arr[i] == copy[i]) {
        i++;
      } else {
        foundI = true;
      }

      if (arr[j] == copy[j]) {
        j--;
      } else {
        foundJ = true;
      }

      if (foundI && foundJ) {
        return new Result(i, j);
      }
    }

    return null; // array is sorted
  }
}
