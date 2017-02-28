public class Solution {
  // O(n + m) time and O(n + m) space
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int[] res = new int[nums1.length + nums2.length];
    int index = 0, i = 0, j = 0;
    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] < nums2[j]) {
        res[index++] = nums1[i++];
      } else {
        res[index++] = nums2[j++];
      }
    }

    while (i < nums1.length) res[index++] = nums1[i++];
    while (j < nums2.length) res[index++] = nums2[j++];

    if (res.length % 2 == 1) {
      return (double) res[(res.length - 1) / 2];
    } else {
      int a = res[(res.length - 1) / 2];
      int b = res[res.length / 2];
      return (a + b) / 2.0;
    }
  }
}
