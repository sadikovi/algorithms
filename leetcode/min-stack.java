// Idea is keeping two stacks: one of values and another one for min value at each level, and
// push into both stacks and pop from both stacks.
class MinStack {
  private LinkedList<Integer> stack;
  private LinkedList<Integer> min;

  /** initialize your data structure here. */
  public MinStack() {
    this.stack = new LinkedList<Integer>();
    this.min = new LinkedList<Integer>();
  }

  public void push(int x) {
    stack.push(x);
    if (min.isEmpty()) {
      min.push(x);
    } else {
      min.push(Math.min(min.peek(), x));
    }
  }

  public void pop() {
    stack.pop();
    min.pop();
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return min.peek();
  }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
