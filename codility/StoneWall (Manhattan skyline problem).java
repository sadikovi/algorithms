class Solution {
  public int solution(int[] H) {
    int blocks = 0;
    int[] stack = new int[H.length];
    int index = -1;

    for (int i=0; i<H.length; i++) {
      while (index >= 0 && stack[index] > H[i])
        index--;

      if (index == -1 || stack[index] != H[i]) {
        blocks++;
        stack[++index] = H[i];
      }
    }

    return blocks;
  }
}

// second solution
class Solution {
  public int solution(int[] H) {
    // do not worry about stack overflow
    int[] stack = new int[H.length];
    int top = -1;
    // number of blocks
    int numBlocks = 0;
    for (int i=0; i<H.length; i++) {
      int a = H[i];
      // peek element from the top
      while (top >= 0 && a < stack[top]) {
        // end of the current block, evict block until a > stack[top]
        top--;
        numBlocks++;
      }

      if (top < 0 || a > stack[top]) {
        // start of new block, add to the stack
        stack[++top] = a;
      }
    }
    // return acquired number of blocks and what is left in the stack
    return numBlocks + top + 1;
  }
}
