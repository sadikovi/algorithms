// Solution runs in O(n) time and O(n) space. Idea is generating sequence of such magical string
// with starting values 1, 2, 2. We use 2 pointers: one slow for sequence that is produced by
// counting occurrences in the same sequence, and fast that is tip of the sequence. Slow is always
// behind fast. Depending on the value of slow we need to add new values (either single number of
// two numbers) at fast pointer. XOR is used to select counterpart of the number (if slow is 1 we
// need to insert 2 and vice versa).
// Then we simply count "1" values in the sequence up to n length.
//
// This solution runs in 41 ms. We can make it run faster by using array, see below.
class Solution {
  public int magicalString(int n) {
    List<Integer> arr = new ArrayList<Integer>();
    arr.add(1);
    arr.add(2);
    arr.add(2);

    int slow = 2, fast = 2;
    while (fast < n) {
      if (arr.get(slow) == 1) {
        addOneNumber(arr, arr.get(fast) ^ 3);
        fast++;
      } else {
        addTwoNumbers(arr, arr.get(fast) ^ 3);
        fast += 2;
      }
      slow++;
    }

    int ones = 0;
    for (int i = 0; i < n; i++) {
      if (arr.get(i) == 1) ones++;
    }
    return ones;
  }

  private void addOneNumber(List<Integer> arr, int num) {
    arr.add(num);
  }

  private void addTwoNumbers(List<Integer> arr, int num) {
    arr.add(num);
    arr.add(num);
  }
}

// Optimised version of the solution, runs in 14 ms
class Solution {
  public int magicalString(int n) {
    int[] arr = new int[n+3];
    arr[0] = 1;
    arr[1] = 2;
    arr[2] = 2;

    int slow = 2, fast = 2;
    while (fast < n) {
      int val = arr[fast] ^ 3;
      if (arr[slow] == 1) {
        arr[++fast] = val;
      } else {
        arr[++fast] = val;
        arr[++fast] = val;
      }
      slow++;
    }

    int ones = 0;
    for (int i = 0; i < n; i++) {
      if (arr[i] == 1) ones++;
    }
    return ones;
  }
}
