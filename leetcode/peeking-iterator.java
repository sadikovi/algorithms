// Alternative implementation (less code, beats 96.52% of submissions)
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
  private Integer current;
  private Iterator<Integer> parent;

  public PeekingIterator(Iterator<Integer> iterator) {
    // initialize any member here.
    current = null;
    parent = iterator;
  }

  // Returns the next element in the iteration without advancing the iterator.
  public Integer peek() {
    if (!hasNext()) throw new IllegalArgumentException();
    return current;
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public Integer next() {
    if (!hasNext()) throw new IllegalArgumentException();
    Integer tmp = current;
    current = null;
    return tmp;
  }

  @Override
  public boolean hasNext() {
    if (current != null) return true;
    if (!parent.hasNext()) return false;
    current = parent.next();
    return true;
  }
}

package test;

import java.util.Iterator;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
public class PeekingIterator implements Iterator<Integer> {
  Iterator<Integer> parent;
  Integer next = null;

  public PeekingIterator(Iterator<Integer> iterator) {
    // initialize any member here.
    this.parent = iterator;
  }

  private void advanceNext() {
    if (next == null && this.parent.hasNext()) {
      next = this.parent.next();
    }
  }

  // Returns the next element in the iteration without advancing the iterator.
  public Integer peek() {
    advanceNext();
    return next;
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public Integer next() {
    advanceNext();
    Integer value = next;
    next = null;
    return value;
  }

  @Override
  public boolean hasNext() {
    advanceNext();
    return next != null;
  }

  @Override
  public void remove() { }
}
