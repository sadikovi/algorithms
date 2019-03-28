/**
 * Idea behind this problem is using rolling product from left to right and right to left, and
 * merging two sequences together, e.g. for array [3, 4, 5, 6], you will have 2 sequences:
 * left -> right
 * [3, 12, 60, 360]
 * right -> left
 * [360, 120, 30, 6]
 * Resulting array is constructed as follows: each element at index i is a product of
 * left->right[i - 1] and right->left[i + 1]; essentially excluding ith element. For corner cases
 * we just take whatever array is relevant, e.g. for 0 index we look at right->left[1] element and
 * vice versa. This solution requires O(2*n) => O(n) space complexity.
 *
 * We can observe that container arrays are not necessary, because we can use resulting array as a
 * container. We put rolling product (either left -> right or right -> left, does not really matter)
 * first into resulting array, and then we iterate backwards and treat i-1 index as element from
 * opposite container.
 *
 * See steps code for explanation how we come to the solution below.
 */
public class Solution {
  // final fast solution, runs in O(1) space complexity (without `out`) and O(n) time complexity
  // works on empty arrays and arrays of 1 element.
  public int[] productExceptSelf(int[] nums) {
    int[] out = new int[nums.length];
    int p = 1; // assume product does not overflow
    for (int i = 0; i < nums.length; i++) { // Bulding rolling product left -> right
      p *= nums[i];
      out[i] = p;
    }
    p = 1; // reset to use from right to left
    for (int i = nums.length - 1; i >= 0; i--) {
      out[i] = i == 0 ? p : (out[i - 1] * p);
      p *= nums[i];
    }
    return out;
  }
}

// Improvement 1: using 2 arrays as containers for rolling products (left, right)
public class Solution {
  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    int[] left = new int[n];
    int[] right = new int[n];

    for (int i = 0; i < n; i++) {
      int k = (i == 0) ? 1 : left[i - 1];
      left[i] = k * nums[i];
    }

    for (int i = n - 1; i >= 0; i--) {
      int k = (i == n - 1) ? 1 : right[i + 1];
      right[i] = k * nums[i];
    }

    for (int i = 0; i < n; i++) {
      if (i == 0) {
        res[i] = right[i + 1];
      } else if (i == n - 1) {
        res[i] = left[i - 1];
      } else {
        res[i] = left[i - 1] * right[i + 1];
      }
    }

    return res;
  }
}

// Improvement 2: Realising that one of the containers is not neccessary and can be replaced with
// resulting array. Still using one container. See actual solution above for removing container and
// storing everything in resulting array.
public class Solution {
  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    int[] left = new int[n];

    for (int i = 0; i < n; i++) {
      int k = (i == 0) ? 1 : left[i - 1];
      left[i] = k * nums[i];
    }

    int right = 1;
    for (int i = n - 1; i > 0; i--) {
      res[i] = left[i - 1] * right;
      right *= nums[i];
    }
    res[0] = right;

    return res;
  }
}
