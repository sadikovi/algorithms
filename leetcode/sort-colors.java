public class Solution {
    public void sortColors(int[] nums) {
      // since we only have 3 buckets, we put each colour into
      // its own bucket and count
      // then we overwrite original array from those buckets
      int[] buckets = new int[3];
      for (int elem : nums) {
        buckets[elem]++;
      }

      int index = 0;
      for (int i = 0; i < buckets.length; i++) {
        while (buckets[i] > 0) {
          nums[index] = i;
          index++;
          buckets[i]--;
        }
      }
    }

  // This solution is taken from leetcode forum
  // single pass and constant space (two pointers)
  public void sortColors2(int[] nums) {
    int zero = 0;
    int two = nums.length - 1;
    for (int i = zero; i <= two; i++) {
      while (nums[i] == 2 && i < two) {
        swap(nums, i, two);
        two--;
      }
      while (nums[i] == 0 && i > zero) {
        swap(nums, i, zero);
        zero++;
      }
    }
  }

  private void swap(int[] arr, int i, int j) {
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
  }

  // My single-pass and constant space solution
  public void sortColors3(int[] nums) {
    int i = 0;
    int j = nums.length - 1;
    int bucket = 0;
    while (i < j && bucket < 2) {
      while (i < j && nums[i] == bucket) i++;
      while (i < j && nums[j] != bucket) j--;
      if (i < j && nums[i] > nums[j]) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
      } else {
        j = nums.length - 1;
        bucket++;
      }
    }
  }

  // My another single-pass and constant space solution,
  // slightly simplified version above
  public void sortColors(int[] nums) {
    int i = 0, j = nums.length - 1, colour = 0;
    while (i < j && colour < 2) {
      while (i < nums.length && nums[i] <= colour) i++;
      while (j > 0 && nums[j] > colour) j--;
      if (i < j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
      } else {
        colour++;
        j = nums.length - 1;
      }
    }
  }
}
