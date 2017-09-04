import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

// Binary heap with HashMap to refresh priority and element access
public class HashMapQueue<T> {
  private int size; // size of the heap
  private T[] arr; // values
  private Comparator<T> comparator; // custom comparator
  private HashMap<T, Integer> map; // map of element-index pairs

  public HashMapQueue(int capacity, Comparator<T> comparator) {
    this.map = new HashMap<T, Integer>();
    this.arr = (T[]) new Object[capacity];
    this.size = 0;
    this.comparator = comparator;
  }

  public HashMapQueue(int capacity) {
    this(capacity, null);
  }

  public HashMapQueue(Comparator<T> comparator) {
    this(8, comparator);
  }

  public HashMapQueue() {
    this(8, null);
  }

  private void assertNonEmpty() {
    if (isEmpty()) {
      throw new UnsupportedOperationException("Empty heap");
    }
  }

  private void assertContains(T elem) {
    if (!map.containsKey(elem)) {
      throw new UnsupportedOperationException("Key does not exist: " + elem);
    }
  }

  private boolean less(T a, T b) {
    if (comparator == null) {
      return ((Comparable<T>) a).compareTo(b) < 0;
    } else {
      return comparator.compare(a, b) < 0;
    }
  }

  private void resize(int newCapacity) {
    if (size >= newCapacity) return;
    T[] tmp = (T[]) new Object[newCapacity];
    System.arraycopy(arr, 0, tmp, 0, size);
    arr = tmp;
  }

  private void swap(int a, int b) {
    T tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
    // update index in map for both elements
    map.put(arr[a], a);
    map.put(arr[b], b);
  }

  private void bubbleUp(int index) {
    while (index > 0) {
      int parent = (index % 2 == 0) ? (index - 2) / 2 : (index - 1) / 2;
      if (less(arr[index], arr[parent])) break;
      // propagate element up the tree, since index is larger than parent index
      swap(parent, index);
      index = parent;
    }
  }

  private void bubbleDown(int index) {
    while ((index << 1) < size) {
      int max = (index << 1) + 1; // choose max as left index
      int right = (index << 1) + 2;
      if (right < size && less(arr[max], arr[right])) {
        max = right;
      }
      // stop propagating down if current element is larger than max child element
      if (less(arr[max], arr[index])) break;
      swap(index, max);
      index = max;
    }
  }

  private boolean isHeap(int index) {
    if ((index << 1) >= size) return true;
    int left = (index << 1) + 1;
    int right = (index << 1) + 2;
    return !less(arr[index], arr[left]) && (right >= size || !less(arr[index], arr[right])) &&
      isHeap(left) && isHeap(right);
  }

  public boolean isHeap() {
    return isHeap(0);
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void insertOrRefresh(T elem) {
    if (map.containsKey(elem)) {
      // refresh priority for the element
      bubbleUp(map.get(elem));
    } else {
      if (size == arr.length) {
        resize(size << 1);
      }
      arr[size] = elem;
      map.put(elem, size);
      bubbleUp(size);
      ++size;
    }
  }

  public void refresh(T elem) {
    assertContains(elem);
    insertOrRefresh(elem);
  }

  public boolean contains(T elem) {
    return map.containsKey(elem);
  }

  // return stored element in map for provided 'elem'
  // finds first element that is equal to 'elem'
  public T storedElem(T elem) {
    assertContains(elem);
    return arr[map.get(elem)];
  }

  public T peek() {
    assertNonEmpty();
    return arr[0];
  }

  public T pop() {
    assertNonEmpty();
    T elem = arr[0];
    arr[0] = arr[--size];
    map.remove(elem);
    bubbleDown(0);
    return elem;
  }

  public ArrayList<T> topK(int k) {
    int i = 0;
    ArrayList<T> res = new ArrayList<T>();
    while (i < k && !isEmpty()) {
      res.add(pop());
      ++i;
    }
    for (T elem : res) {
      insertOrRefresh(elem);
    }
    return res;
  }

  @Override
  public String toString() {
    return "{size: " + size + ", arr: " + Arrays.toString(arr) + ", map: " + map + "}";
  }
}
