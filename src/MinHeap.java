import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinHeap {
  private int[] arr;
  private int size;

  public MinHeap(int capacity) {
    this.arr = new int[capacity];
    this.size = 0;
  }

  public MinHeap() {
    this(16);
  }

  // True, if heap is empty
  public boolean isEmpty() {
    return size == 0;
  }

  // Insert value into the heap
  public void insert(int elem) {
    if (size == arr.length) {
      resize();
    }
    arr[size] = elem;
    bubbleUp(size);
    ++size;
  }

  // Peek min value
  public int min() {
    if (isEmpty()) throw new NoSuchElementException("Empty heap");
    return arr[0];
  }

  // Remove and return min value
  public int extractMin() {
    int min = min();
    --size;
    arr[0] = arr[size];
    bubbleDown(0);
    return min;
  }

  // == private methods ==

  private void resize() {
    int[] tmp = new int[arr.length * 2];
    System.arraycopy(arr, 0, tmp, 0, arr.length);
    arr = tmp;
  }

  private void bubbleUp(int i) {
    while (i > 0) {
      int k = (i % 2 == 0) ? (i - 2) / 2 : (i - 1) / 2;
      if (arr[k] <= arr[i]) break;
      swap(arr, k, i);
      i = k;
    }
  }

  private void bubbleDown(int i) {
    while (i < size) {
      int l = i * 2 + 1, r = i * 2 + 2;
      if (r < size && arr[r] < arr[l]) {
        l = r;
      }
      if (l < size && arr[l] < arr[i]) {
        swap(arr, l, i);
        i = l;
      } else {
        break;
      }
    }
  }

  private static void swap(int[] arr, int a, int b) {
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }

  @Override
  public String toString() {
    return "{size=" + size + ", arr=" + Arrays.toString(arr) + "}";
  }
}
