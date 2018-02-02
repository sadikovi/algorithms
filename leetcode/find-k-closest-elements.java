// Solution runs in O(logN + k) time and O(1) space
class Solution {
  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    LinkedList<Integer> res = new LinkedList<Integer>();
    if (arr.length == 0) return res;
    int pos = search(arr, x);
    int i = pos, j = pos + 1;
    while (k > 0 && (i >= 0 || j < arr.length)) {
      int l = (i >= 0) ? Math.abs(arr[i] - x) : Integer.MAX_VALUE;
      int r = (j < arr.length) ? Math.abs(arr[j] - x) : Integer.MAX_VALUE;
      if (l <= r) {
        res.addFirst(arr[i--]);
      } else {
        res.addLast(arr[j++]);
      }
      k--;
    }
    return res;
  }

  private int search(int[] arr, int target) {
    int start = 0, end = arr.length - 1;
    while (start <= end) {
      int mid = (start + end) / 2;
      if (arr[mid] == target) {
        return mid;
      } else if (arr[mid] > target) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return end;
  }
}
