/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
  private int index;
  private List<Integer> list;

  public NestedIterator(List<NestedInteger> nestedList) {
    this.list = new ArrayList<Integer>();
    preprocess(nestedList, this.list);
    this.index = 0;
  }

  private void preprocess(List<NestedInteger> nestedList, List<Integer> list) {
    for (NestedInteger ni : nestedList) {
      if (ni.isInteger()) {
        list.add(ni.getInteger());
      } else {
        preprocess(ni.getList(), list);
      }
    }
  }

  @Override
  public Integer next() {
    return list.get(index++);
  }

  @Override
  public boolean hasNext() {
    return index < list.size();
  }
}

// As alternative, here we treat input as iterator and maintain a queue of all values for a single
// nested integer, because it has to fit in memory.
public class NestedIterator implements Iterator<Integer> {
  private LinkedList<Integer> queue;
  private Iterator<NestedInteger> iter;

  public NestedIterator(List<NestedInteger> nestedList) {
    iter = nestedList.iterator();
    queue = new LinkedList<Integer>();
  }

  private void unpack(NestedInteger n) {
    if (n.isInteger()) {
      queue.add(n.getInteger());
    } else {
      for (NestedInteger t : n.getList()) {
        unpack(t);
      }
    }
  }

  @Override
  public Integer next() {
    return queue.poll();
  }

  @Override
  public boolean hasNext() {
    while (queue.isEmpty() && iter.hasNext()) {
      unpack(iter.next());
    }
    return !queue.isEmpty();
  }
}
