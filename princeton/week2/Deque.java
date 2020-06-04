import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private class Node {
    Item item;
    Node prev;
    Node next;
  }

  private Node head;
  private Node tail;
  private int size;

  // construct an empty deque
  public Deque() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() {
    return size;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) throw new IllegalArgumentException();

    Node node = new Node();
    node.item = item;

    if (head == null) {
      head = node;
      tail = node;
    } else {
      node.next = head;
      head.prev = node;
      head = node;
    }

    size++;
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null) throw new IllegalArgumentException();

    Node node = new Node();
    node.item = item;

    if (head == null) {
      head = node;
      tail = node;
    } else {
      node.prev = tail;
      tail.next = node;
      tail = node;
    }

    size++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) throw new NoSuchElementException();
    Item item = head.item;
    if (head == tail) {
      head = null;
      tail = null;
    } else {
      head = head.next;
      head.prev = null;
    }

    size--;

    return item;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (isEmpty()) throw new NoSuchElementException();
    Item item = tail.item;
    if (head == tail) {
      head = null;
      tail = null;
    } else {
      tail = tail.prev;
      tail.next = null;
    }

    size--;

    return item;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new Iterator<Item>() {
      private Node tmp = head;

      @Override
      public boolean hasNext() {
        return tmp != null;
      }

      @Override
      public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        Item item = tmp.item;
        tmp = tmp.next;
        return item;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<String> dq = new Deque<String>();

    System.out.println(dq.isEmpty());
    dq.addFirst("1");
    System.out.println(dq.isEmpty());
    dq.addFirst("2");
    System.out.println(dq.isEmpty());
    dq.addLast("3");
    System.out.println(dq.isEmpty());
    dq.addLast("4");
    System.out.println(dq.isEmpty());
    dq.addLast("5");
    System.out.println("size: " + dq.size());

    Iterator<String> iter = dq.iterator();
    while (iter.hasNext()) {
      System.out.print(iter.next() + " ");
    }
    System.out.println();

    System.out.println(dq.removeLast());
    System.out.println(dq.removeFirst());
    System.out.println(dq.removeFirst());
    System.out.println(dq.removeFirst());
    System.out.println("size: " + dq.size());
    System.out.println(dq.removeLast());
    System.out.println("size: " + dq.size());
  }
}
