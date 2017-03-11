// The probability that ith element (including the last one) goes to last position is 1/n, because
// we randomly pick an element in first iteration.

// The probability that ith element goes to second last position can be proved to be 1/n by dividing
// it in two cases.
// Case 1: i = n-1 (index of last element):
//    The probability of last element going to second last position is = (probability that last
//    element doesn't stay at its original position) x (probability that the index picked in
//    previous step is picked again so that the last element is swapped)
//    So the probability = ((n-1)/n) x (1/(n-1)) = 1/n
// Case 2: 0 < i < n-1 (index of non-last):
//    The probability of ith element going to second position = (probability that ith element is not
//    picked in previous iteration) x (probability that ith element is picked in this iteration)
//    So the probability = ((n-1)/n) x (1/(n-1)) = 1/n
// We can easily generalize above proof for any other position.
public class Solution {
  private Random random;
  private int[] original;

  public Solution(int[] nums) {
    this.random = new Random();
    this.original = nums;
  }

  /** Resets the array to its original configuration and return it. */
  public int[] reset() {
    return this.original;
  }

  /** Returns a random shuffling of the array. */
  public int[] shuffle() {
    int[] arr = new int[this.original.length];
    System.arraycopy(this.original, 0, arr, 0, this.original.length);
    for (int i = arr.length - 1; i > 0; i--) {
      int j = this.random.nextInt(i + 1);
      int t = arr[i];
      arr[i] = arr[j];
      arr[j] = t;
    }
    return arr;
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
