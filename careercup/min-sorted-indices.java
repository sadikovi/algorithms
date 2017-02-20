package test;

public class Test {
  static class Result {
    int m;
    int n;

    @Override
    public String toString() {
      return "(m=" + m + ", n=" + n + ")";
    }
  }
  public static Result minSortedIndices(int[] arr) {
    int min = -1, max = -1;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i - 1] > arr[i]) {
        if (min == -1 || arr[min] > arr[i]) min = i;
      }
    }

    if (min == -1) return null;

    for (int i = arr.length - 2; i >= 0; i--) {
      if (arr[i + 1] < arr[i]) {
        if (max == -1 || arr[max] < arr[i]) max = i;
      }
    }

    int m = 0, n = arr.length - 1;
    while (m < min && arr[m] < arr[min]) m++;
    while (n > max && arr[n] > arr[max]) n--;

    Result res = new Result();
    res.m = m;
    res.n = n;
    return res;
  }
}
