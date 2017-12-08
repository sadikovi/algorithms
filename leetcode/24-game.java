// Solution that is based on backtracking and possible time complexity is either exponential or
// even factorial, but it does not really matter here, because of the problem constaints -
// there are 495 solutions in total (404 solvable, 91 not solvable).
//
// Solution beats 89.58% of submissions.
//
// Idea:
// Initially we have 4 numbers, at each recursive call we take the number from array and build
// tmp array of (len - 1) elements left. Then we try all operators (*, +, -, /) on every number in
// tmp array with number we extracted, and replace with the result in tmp array for that position.
// We keep doing it until array has only one element, at this point it is either 24 (within
// threshold) or not.
class Solution {
  public boolean judgePoint24(int[] nums) {
    double[] tmp = new double[nums.length];
    for (int i = 0; i < nums.length; i++) tmp[i] = nums[i];
    return solve(tmp);
  }

  public boolean solve(double[] nums) {
    if (nums.length == 1) return Math.abs(nums[0] - 24) <= 1e-3;
    for (int i = 0; i < nums.length; i++) {
      double[] tmp = new double[nums.length - 1];
      int p = 0;
      for (int j = 0; j < i; j++) tmp[p++] = nums[j];
      for (int j = i+1; j < nums.length; j++) tmp[p++] = nums[j];

      double a = nums[i];
      for (int k = 0; k < tmp.length; k++) {
        double orig = tmp[k];
        tmp[k] = orig + a;
        if (solve(tmp)) return true;
        tmp[k] = orig - a;
        if (solve(tmp)) return true;
        tmp[k] = orig / a;
        if (solve(tmp)) return true;
        tmp[k] = orig * a;
        if (solve(tmp)) return true;
        tmp[k] = orig;
      }
    }
    return false;
  }
}
