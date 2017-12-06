// Solution that I came up with.
// `addNum` takes O(n) time in the worst case and O(n) space
// `getIntervals` takes O(1) time (we just return reference to a list of intervals), but it will
// be O(n) if we copy them.
// Solution beats 75.81% of submissions.
//
// Idea is performing compaction every time an interval has been changed in main list, this should
// not happen frequently, and only happens for a single interval.
//
class SummaryRanges {
  private ArrayList<Interval> intervals;

  /** Initialize your data structure here. */
  public SummaryRanges() {
    intervals = new ArrayList<Interval>();
  }

  public void addNum(int val) {
    boolean compact = false;
    int i = 0;
    while (i < intervals.size()) {
      Interval curr = intervals.get(i);
      if (val < curr.start) {
        intervals.add(i, new Interval(val, val));
        compact = true;
        break;
      } else if (val >= curr.start && val <= curr.end) {
        break;
      }
      ++i;
    }
    if (i == intervals.size()) {
      intervals.add(new Interval(val, val));
      compact = true;
    }

    if (compact && intervals.size() > 0) {
      ArrayList<Interval> tmp = intervals;
      intervals = new ArrayList<Interval>();

      Interval curr = tmp.get(0);
      for (i = 1; i < tmp.size(); i++) {
        if (curr.end == tmp.get(i).start || curr.end + 1 == tmp.get(i).start) {
          curr.start = Math.min(curr.start, tmp.get(i).start);
          curr.end = Math.max(curr.end, tmp.get(i).end);
        } else {
          intervals.add(curr);
          curr = tmp.get(i);
        }
      }
      intervals.add(curr);
    }
  }

  public List<Interval> getIntervals() {
    return intervals;
  }
}

// Very nice solution from Leetcode by using treemap, this is similar to the approach I was building
// by using binary search tree, except that solution was slow on Leetcode:
// https://discuss.leetcode.com/topic/46887/java-solution-using-treemap-real-o-logn-per-adding/2
// Even though it is asymptotically better, it is still slower on Leetcode and beats only 24.91% of
// submissions.
class SummaryRanges {
  TreeMap<Integer, Interval> map;

  /** Initialize your data structure here. */
  public SummaryRanges() {
    map = new TreeMap<Integer, Interval>();
  }

  public void addNum(int val) {
    if (map.containsKey(val)) return;
    Integer l = map.lowerKey(val);
    Integer h = map.higherKey(val);
    if (l != null && h != null && map.get(l).end == val - 1 && h == val + 1) {
      map.get(l).end = map.get(h).end;
      map.remove(h);
    } else if (l != null && map.get(l).end >= val - 1) {
      map.get(l).end = Math.max(map.get(l).end, val);
    } else if (h != null && h == val + 1) {
      map.put(val, new Interval(val, map.get(h).end));
      map.remove(h);
    } else {
      map.put(val, new Interval(val, val));
    }
  }

  public List<Interval> getIntervals() {
    return new ArrayList<Interval>(map.values());
  }
}
