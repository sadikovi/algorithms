import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private static final int INIT_CAPACITY = 8;

  private Item[] items;
  private int size;

  // construct an empty randomized queue
  public RandomizedQueue() {
    this(INIT_CAPACITY);
  }

  public RandomizedQueue(int capacity) {
    this.items = (Item[]) new Object[capacity];
    this.size = 0;
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return size;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) throw new IllegalArgumentException();
    resize();
    items[size++] = item;
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException();
    int idx = StdRandom.uniform(size);
    Item res = items[idx];
    items[idx] = (idx == size - 1) ? null : items[size - 1];
    size--;
    return res;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if (isEmpty()) throw new NoSuchElementException();
    int idx = StdRandom.uniform(size);
    return items[idx];
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    Item[] arr0 = (Item[]) new Object[size];
    System.arraycopy(items, 0, arr0, 0, size);

    return new Iterator<Item>() {
      private int len = size;
      private Item[] arr = arr0;

      @Override
      public boolean hasNext() {
        return len > 0;
      }

      @Override
      public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        int idx = StdRandom.uniform(len);
        Item res = arr[idx];
        arr[idx] = (idx == len - 1) ? null : arr[len - 1];
        len--;
        return res;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  private void resize() {
    if (size == items.length) {
      Item[] tmp = (Item[]) new Object[items.length * 2];
      System.arraycopy(items, 0, tmp, 0, size);
      items = tmp;
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
    for (int i = 0; i < 10; i++) {
      q.enqueue(i);
    }

    for (int i = 0; i < 10; i++) {
      System.out.print(q.dequeue() + " ");
    }
    System.out.println();

    for (int i = 0; i < 10; i++) {
      q.enqueue(i);
    }

    Iterator<Integer> iter = q.iterator();
    while (iter.hasNext()) {
      System.out.print(iter.next() + " ");
    }
    System.out.println();

    System.out.println(q.isEmpty());
  }
}
