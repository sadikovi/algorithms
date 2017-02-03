// Naive solution using sorting
public class Solution {
  public int findKthLargest(int[] nums, int k) {
    if (k < 1 || k > nums.length) return -1;
    Arrays.sort(nums);
    return nums[nums.length - k];
  }
}

// Solution using Quickselect algorithm, runtime is O(n)
public class Solution {
  public int findKthLargest(int[] nums, int k) {
    if (k < 1 || k > nums.length) return -1;
    return select(nums, 0, nums.length - 1, nums.length - k);
  }

  private int select(int[] nums, int left, int right, int k) {
    if (left == right) return nums[left];
    int pivot = right;
    pivot = partition(nums, left, right, pivot);
    if (k == pivot) return nums[k];
    if (k < pivot) {
      return select(nums, left, pivot - 1, k);
    } else {
      return select(nums, pivot + 1, right, k);
    }
  }

  private int partition(int[] nums, int left, int right, int pivot) {
    int pivotValue = nums[pivot];
    swap(nums, pivot, right);
    int sindex = left;
    for (int i = left; i < right; i++) {
      if (nums[i] < pivotValue) {
        swap(nums, sindex, i);
        sindex++;
      }
    }
    swap(nums, sindex, right);
    return sindex;
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
