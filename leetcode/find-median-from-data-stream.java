public class MedianFinder {
  PriorityQueue<Integer> minHeap;
  PriorityQueue<Integer> maxHeap;

  /** initialize your data structure here. */
  public MedianFinder() {
    minHeap = new PriorityQueue<Integer>(11, Collections.reverseOrder());
    maxHeap = new PriorityQueue<Integer>(11);
  }

  public void addNum(int num) {
    if (maxHeap.isEmpty()) {
      maxHeap.add(num);
    } else if (maxHeap.peek() <= num) {
      maxHeap.add(num);
      if (maxHeap.size() > minHeap.size() + 1) {
        minHeap.add(maxHeap.poll());
      }
    } else {
      minHeap.add(num);
      if (minHeap.size() > maxHeap.size()) {
        maxHeap.add(minHeap.poll());
      }
    }
  }

  public double findMedian() {
    if (maxHeap.isEmpty()) return 0.0;
    return maxHeap.size() == minHeap.size() ? (maxHeap.peek() + minHeap.peek()) / 2.0 : maxHeap.peek();
  }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

// Alternative solution, beats 90.65% of submissions.
// Add operation runs in O(log n) time and O(1) space, depending on heap insertion.
// Find median operation runs in O(1) time and O(1) space.
class MedianFinder {
  private PriorityQueue<Integer> left;
  private PriorityQueue<Integer> right;

  /** initialize your data structure here. */
  public MedianFinder() {
    left = new PriorityQueue<Integer>(Collections.reverseOrder());
    right = new PriorityQueue<Integer>();
  }

  public void addNum(int num) {
    if (right.size() == 0) {
      right.add(num);
    } else if (left.size() == right.size()) {
      if (num < left.peek()) {
        right.add(left.poll());
        left.add(num);
      } else {
        right.add(num);
      }
    } else {
      if (num > right.peek()) {
        left.add(right.poll());
        right.add(num);
      } else {
        left.add(num);
      }
    }
  }

  public double findMedian() {
    if (right.size() == 0) throw new IllegalArgumentException();
    if (right.size() > left.size()) return right.peek();
    return (right.peek() + left.peek()) / 2.0;
  }
}
