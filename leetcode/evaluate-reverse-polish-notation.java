// Nice solution, beats 63.24% submissions
public class Solution {
  public int evalRPN(String[] tokens) {
    Stack<Integer> numbers = new Stack<Integer>();
    for (int i = 0; i < tokens.length; i++) {
      if (isOperand(tokens[i])) {
        char t = tokens[i].charAt(0);
        int num2 = numbers.pop();
        int num1 = numbers.pop();
        int result;
        if (t == '+') {
          result = num1 + num2;
        } else if (t == '-') {
          result = num1 - num2;
        } else if (t == '*') {
          result = num1 * num2;
        } else {
          result = num1 / num2;
        }
        numbers.push(result);
      } else {
        numbers.push(Integer.parseInt(tokens[i]));
      }
    }
    return numbers.pop();
  }

  public boolean isOperand(String token) {
    return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
  }
}

// More compact solution, beats 95.70% submissions
class Solution {
  public int evalRPN(String[] tokens) {
    LinkedList<Integer> stack = new LinkedList<Integer>();
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].equals("+")) {
        stack.push(stack.pop() + stack.pop());
      } else if (tokens[i].equals("-")) {
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a - b);
      } else if (tokens[i].equals("*")) {
        stack.push(stack.pop() * stack.pop());
      } else if (tokens[i].equals("/")) {
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a / b);
      } else {
        stack.push(Integer.parseInt(tokens[i]));
      }
    }
    return stack.pop();
  }
}
