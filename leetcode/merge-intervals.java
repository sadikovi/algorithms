/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
// Solution runs in O(n*logn) time and O(n) space (sorting and output storage).
// Sort by start index and merge them one by one as any intersection will result in merge.
class Solution {
  public List<Interval> merge(List<Interval> intervals) {
    if (intervals.size() == 0) return intervals;

    Comparator<Interval> cmp = new Comparator<Interval>() {
      @Override
      public int compare(Interval a, Interval b) {
        if (a.start == b.start) return 0;
        if (a.start < b.start) return -1;
        return 1;
      }
    };
    Collections.sort(intervals, cmp);

    List<Interval> res = new ArrayList<Interval>();
    Interval current = intervals.get(0);
    for (int i = 1; i < intervals.size(); i++) {
      if (intervals.get(i).start > current.end) {
        res.add(current);
        current = intervals.get(i);
      } else {
        current.start = Math.min(current.start, intervals.get(i).start);
        current.end = Math.max(current.end, intervals.get(i).end);
      }
    }
    res.add(current);
    return res;
  }
}

// Alternative solution, O(n * log n) time and O(n) space.
class Solution {
  public List<Interval> merge(List<Interval> intervals) {
    Collections.sort(intervals, new Comparator<Interval>() {
      public int compare(Interval a, Interval b) {
        return Integer.compare(a.start, b.start);
      }
    });

    List<Interval> res = new ArrayList<Interval>();
    Interval curr = null;
    for (Interval inter : intervals) {
      if (curr == null) {
        curr = inter;
      } else if (curr.end < inter.start) {
        res.add(curr);
        curr = inter;
      }
      curr.end = Math.max(curr.end, inter.end);
    }

    if (curr != null) {
      res.add(curr);
    }

    return res;
  }
}
