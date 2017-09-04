public class Counter implements Comparable<Counter> {
  private String key;
  private int value;

  public Counter(String key, int value) {
    this.key = key;
    this.value = value;
  }

  public Counter(String key) {
    this(key, 0);
  }

  public void inc() {
    value++;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof Counter)) return false;
    Counter that = (Counter) obj;
    // equality is always on key
    return that.key.equals(this.key);
  }

  @Override
  public int hashCode() {
    return key.hashCode();
  }

  @Override
  public int compareTo(Counter other) {
    if (this.value > other.value) return 1;
    if (this.value < other.value) return -1;
    return 0;
  }

  @Override
  public String toString() {
    return key + "[" + value + "]";
  }
}
