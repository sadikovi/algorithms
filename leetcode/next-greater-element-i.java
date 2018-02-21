// Solution runs in O(num1 + nums2) time and uses O(nums2) space.
// Idea is using stack to keep next greater element for the current number in nums2.
class Solution {
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    HashMap<Integer, Integer> nums2map = new HashMap<Integer, Integer>();
    int[] stack = new int[nums2.length];
    int k = -1;
    for (int i = nums2.length - 1; i >= 0; i--) {
      while (k >= 0 && stack[k] <= nums2[i]) k--;
      nums2map.put(nums2[i], (k < 0) ? -1 : stack[k]);
      stack[++k] = nums2[i];
    }
    int[] res = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
      res[i] = nums2map.get(nums1[i]);
    }
    return res;
  }
}
