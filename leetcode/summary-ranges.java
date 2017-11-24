// Solution runs in O(n) time and O(n) space, where n is the length of the array
class Solution {
  public List<String> summaryRanges(int[] nums) {
    List<String> res = new ArrayList<String>();
    if (nums.length == 0) return res;
    int start = nums[0], end = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > nums[i-1] + 1) {
        addRange(res, start, end);
        start = nums[i];
        end = nums[i];
      } else {
        end = nums[i];
      }
    }
    addRange(res, start, end);
    return res;
  }

  private void addRange(List<String> res, int start, int end) {
    if (start == end) {
      res.add("" + start);
    } else {
      res.add(start + "->" + end);
    }
  }
}
