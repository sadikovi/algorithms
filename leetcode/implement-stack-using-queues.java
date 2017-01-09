class MyStack {
  LinkedList<Integer> queue = new LinkedList<Integer>();

  // Push element x onto stack.
  public void push(int x) {
    queue.add(x);
    int size = queue.size();
    while (size > 1) {
      queue.add(queue.poll());
      size--;
    }
  }

  // Removes the element on top of the stack.
  public void pop() {
    queue.poll();
  }

  // Get the top element.
  public int top() {
    return queue.peek();
  }

  // Return whether the stack is empty.
  public boolean empty() {
    return queue.isEmpty();
  }
}
