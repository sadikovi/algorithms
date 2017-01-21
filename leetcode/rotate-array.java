public class Solution {
  // rotate array by copying parts 0-k and k-(n-k)
  // O(n) time and O(n) space complexity
  public void rotate1(int[] nums, int k) {
    int n = nums.length;
    k = k % n;
    int[] res = new int[n];
    System.arraycopy(nums, 0, res, 0, n);
    System.arraycopy(res, n - k, nums, 0, k);
    System.arraycopy(res, 0, nums, k, n - k);
  }

  // rotate array by storing array twice in single array,
  // then extracting from n - k to 2n - k and copying into original array
  // O(n) time and O(n) space complexity
  public void rotate2(int[] nums, int k) {
    int n = nums.length;
    k = k % n;
    int[] res = new int[n * 2];
    for (int i = 0; i < n; i++) {
      res[i] = nums[i];
      res[i + n] = nums[i];
    }

    int j = 0;
    for (int i = n - k; i < 2 * n - k; i++) {
      nums[j++] = res[i];
    }
  }

  // rotate array by reversing it; first, reverse entire array, second, reverse 0-k part and
  // then reverse k - (n - 1) part, e.g.
  // [1, 2, 3, 4, 5], k = 2 => [5, 4, 3, 2, 1] => [4, 5, 3, 2, 1] => [4, 5, 1, 2, 3]
  private void reverse(int[] nums, int start, int end) {
    while (start < end) {
      int tmp = nums[start];
      nums[start] = nums[end];
      nums[end] = tmp;
      start++;
      end--;
    }
  }

  public void rotate(int[] nums, int k) {
    k = k % nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
  }
}
