// Solution runs in O(n) time and uses O(1) space.
//
// The idea is realising that if we count majority elements, it is always greater than the
// half of the elements in the array. So what we do is counting repeated elements, and subtracting
// when new element is encountered (if after subtraction cnt is 0 - reset to the new element):
// arr: [1, 2, 3, 3, 4, 3, 5, 3, 3]
// maj: [1, 2, 3, 3, 3, 3, 3, 3, 3]
// cnt: [1, 1, 1, 2, 1, 2, 1, 2, 3]
//
// Alternative solutions include using hash table (O(n) time) or sorting elements in the
// array (O(n log n) time).
//
class Solution {
  public int majorityElement(int[] nums) {
    int cnt = 1, elem = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (elem == nums[i]) {
        cnt++;
      } else {
        cnt--;
        if (cnt == 0) {
          cnt = 1;
          elem = nums[i];
        }
      }
    }
    return elem;
  }
}
