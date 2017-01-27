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
