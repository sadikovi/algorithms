// Solution runs in O(n) time and O(n) space in case of increasing sequence (stack will keep all
// values), beats 89.71% of submissions.
//
// Solution is inspired by following explanation:
// https://discuss.leetcode.com/topic/67881/single-pass-c-o-n-space-and-time-solution-8-lines-with-detailed-explanation
//
// QUESTION: To search for a subsequence (s1,s2,s3) such that s1 < s3 < s2.
//
// INTUITION: Suppose we want to find a 123 sequence with s1 < s2 < s3, we just need to find s3,
// followed by s2 and s1. Now if we want to find a 132 sequence with s1 < s3 < s2, we need to switch
// up the order of searching. we want to first find s2, followed by s3, then s1.
//
// DETECTION: More precisely, we keep track of highest value of s3 for each valid (s2 > s3) pair
// while searching for a valid s1 candidate to the left. Once we encounter any number on the left
// that is smaller than the largest s3 we have seen so far, we know we found a valid sequence,
// since s1 < s3 implies s1 < s2.
//
// ALGORITHM: We can start from either side but I think starting from the right allow us to finish
// in a single pass. The idea is to start from end and search for valid (s2,s3) pairs, we just need
// to remember the largest valid s3 value, using a stack will be effective for this purpose.
// A number becomes a candidate for s3 if there is any number on the left bigger than it.
//
// CORRECTNESS: As we scan from right to left, we can easily keep track of the largest s3 value of
// all (s2,s3) candidates encountered so far. Hence, each time we compare nums[i] with the largest
// candidate for s3 within the interval nums[i+1]...nums[n-1] we are effectively asking the
// question: Is there any 132 sequence with s1 = nums[i]? Therefore, if the function returns false,
// there must be no 132 sequence.
//
// IMPLEMENTATION:
// - Have a stack, each time we store a new number, we first pop out all numbers that are smaller
//   than that number. The numbers that are popped out becomes candidate for s3.
// - We keep track of the maximum of such s3 (which is always the most recently popped number from
//   the stack).
// - Once we encounter any number smaller than s3, we know we found a valid sequence since s1 < s3
//   implies s1 < s2.
//
// RUNTIME: Each item is pushed and popped once at most, the time complexity is therefore O(n).
//
// EXAMPLE:
// i = 6, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 9, S3 candidate = None, Stack = Empty
// i = 5, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 7, S3 candidate = None, Stack = [9]
// i = 4, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 10, S3 candidate = None, Stack = [9,7]
// i = 3, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 9, S3 candidate = 9, Stack = [10]
// i = 2, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 8, S3 candidate = 9, Stack = [10,9]
// We have 8 < 9, sequence (8,10,9) found!
//
class Solution {
  public boolean find132pattern(int[] nums) {
    LinkedList<Integer> stack = new LinkedList<Integer>();
    int i = nums.length - 1;
    int s1 = 0, s3 = Integer.MIN_VALUE;
    while (i >= 0) {
      s1 = nums[i];
      while (!stack.isEmpty() && stack.peek() < s1) {
        s3 = stack.pop();
      }
      if (s1 < s3) return true;
      stack.push(s1);
      --i;
    }
    return false;
  }
}
