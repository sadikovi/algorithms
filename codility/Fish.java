// simpler solution and much neater
class Solution {
  public int solution(int[] A, int[] B) {
    int[] dFish = new int[A.length];
    int di = -1;
    int upstreamCount = 0;

    for (int i=0; i<A.length; i++) {
      if (B[i] == 1) {
        dFish[++di] = A[i];
      } else {
        while (di >= 0) {
          if (di < 0 || A[i] < dFish[di])
            break;
          di--;
        }

        if (di < 0)
          upstreamCount++;
      }
    }
    return (di + 1) + upstreamCount;
  }
}

// even simpler solution [recommended]
class Solution {
  public int solution(int[] A, int[] B) {
    // collect downstream fish in stack
    int[] stack = new int[A.length];
    int top = -1;
    int numFish = 0;
    for (int i=0; i<A.length; i++) {
      if (B[i] == 1) {
        stack[++top] = A[i];
      } else {
        while (top >= 0 && A[i] > stack[top]) {
          top--;
        }
        // if no downstream fish alive increment numFish
        if (top < 0) {
          numFish++;
        }
      }
    }

    return numFish + top + 1;
  }
}

// longer solution and perhaps more complicated
class Solution {
  int[] fish;
  private int index = -1;

  public int solution(int[] A, int[] B) {
    fish = new int[A.length];
    int alivefish = 0;
    boolean isFirstDownstream = false;
    for (int i=0; i<A.length; i++) {
      if (B[i] == 0) {
        if (!isFirstDownstream) {
          alivefish += 1;
        } else {
          if (size(fish) == 0) {
            alivefish += 1;
          } else {
            while(size(fish) > 0 && A[peek(fish)] < A[i])
              pop(fish);
            if (size(fish) == 0) alivefish += 1;
          }
        }
      } else {
        isFirstDownstream = true;
        push(fish, i);
      }
    }

    alivefish += size(fish);
    return alivefish;
  }

  private void push(int[] a, int i) {
    index++;
    a[index] = i;
  }

  private void pop(int[] a) {
    if (index >= 0) index--;
  }

  private int peek(int[] a) {
    if (index < 0 || index > (a.length-1)) return -1;
    return a[index];
  }

  private int size(int[] a) {
    return (index >= 0)?index+1:0;
  }
}
