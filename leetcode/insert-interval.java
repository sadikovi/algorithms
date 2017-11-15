// Solution runs in O(n) time and O(n) space (output list).
// Beats 78.85% of submissions.
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> res = new ArrayList<Interval>();
    int i = 0, len = intervals.size();
    // add non-overlapping smaller intervals
    while (i < len && intervals.get(i).end < newInterval.start) {
      res.add(intervals.get(i++));
    }

    // perform merge with overlapping intervals
    while (i < len && intervals.get(i).start <= newInterval.end) {
      newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
      newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
      ++i;
    }
    res.add(newInterval);

    // add non-overlapping larger intervals
    while (i < len) {
      res.add(intervals.get(i++));
    }

    return res;
  }
}

// Slower solution which is more convoluted as it was my first attempt.
// Runs in O(n) time and O(n) space.
// Beats 58.46%
class Solution {
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> res = new ArrayList<Interval>();
    Interval curr = null, inter = null;
    int i = 0;
    while (i < intervals.size()) {
      inter = intervals.get(i);
      if (newInterval != null && newInterval.start < inter.start) {
        inter = newInterval;
        newInterval = null;
      } else if (newInterval != null && newInterval.start <= inter.end) {
        inter.end = Math.max(newInterval.end, inter.end);
        newInterval = null;
      } else {
        ++i;
      }

      // merge curr with inter
      if (curr == null || curr.end < inter.start) {
        if (curr != null) res.add(curr);
        curr = inter;
      }
      curr.end = Math.max(curr.end, inter.end);
    }

    if (curr != null) res.add(curr);
    if (newInterval != null) res.add(newInterval);

    return res;
  }
}
