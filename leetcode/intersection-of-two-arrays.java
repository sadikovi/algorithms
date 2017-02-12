// 2 pointers solution, runs in O(AlgA + BlgB), space complexity is O(lgA + lgB)
// does not include storage for resulting array
public class Solution {
  public int[] intersection(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    List<Integer> lst = new ArrayList<Integer>();
    int i = 0, j = 0;
    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] == nums2[j]) {
        int t = nums1[i];
        lst.add(t);
        while (i < nums1.length && nums1[i] == t) i++;
        while (j < nums2.length && nums2[j] == t) j++;
      } else if (nums1[i] > nums2[j]) {
        while (j < nums2.length && nums2[j] < nums1[i]) j++;
      } else {
        while (i < nums1.length && nums1[i] < nums2[j]) i++;
      }
    }

    int[] res = new int[lst.size()];
    int index = 0;
    for (int elem : lst) {
        res[index++] = elem;
    }
    return res;
  }
}

// HashSet based solution, runs in O(A + B), O(max(A, B)) space;
// can be reduced to O(min(A, B))
public class Solution {
  public int[] intersection(int[] nums1, int[] nums2) {
    HashSet<Integer> set = new HashSet<Integer>();
    for (int i = 0; i < nums1.length; i++) {
      set.add(nums1[i]);
    }

    List<Integer> lst = new ArrayList<Integer>();
    for (int i = 0; i < nums2.length; i++) {
      if (set.contains(nums2[i])) {
        lst.add(nums2[i]);
        set.remove(nums2[i]);
      }
    }

    int[] res = new int[lst.size()];
    int index = 0;
    for (int num : lst) {
      res[index++] = num;
    }

    return res;
  }
}
