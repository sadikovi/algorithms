class RandomizedSet {
  private int size;
  private int[] arr;
  private HashMap<Integer, Integer> index;
  private Random rand;

  /** Initialize your data structure here. */
  public RandomizedSet() {
    size = 0;
    arr = new int[64];
    index = new HashMap<Integer, Integer>();
    rand = new Random();
  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    if (index.containsKey(val)) return false;
    resize();
    arr[size] = val;
    index.put(val, size);
    ++size;
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if (size == 0 || !index.containsKey(val)) return false;
    int idx = index.remove(val);
    if (idx < size - 1) {
      arr[idx] = arr[size - 1];
      index.put(arr[idx], idx);
    }
    --size;
    return true;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    int idx = rand.nextInt(size);
    return arr[idx];
  }

  private void resize() {
    if (size == arr.length) {
      int[] tmp = new int[arr.length * 2];
      System.arraycopy(arr, 0, tmp, 0, arr.length);
      arr = tmp;
    }
  }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
