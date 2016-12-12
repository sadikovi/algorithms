// LFU cache algorithm, O(1) time complexity on search, O(n) time complexity on insert.
// Cache can be also implemented as max heap with support for rebalancing when value is modified,
// this should make search O(logN) and insert O(logN).
//
// This is the most naive implementation, for all-O(1) time complexity operations for LFU see
// paper: http://dhruvbird.com/lfu.pdf

public class LFUCache {
    static class Container {
        int key;
        int value;
        int freq;
        int time;

        Container(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 0;
            this.time = 0;
        }
    }

    private HashMap<Integer, Container> lookup;
    private Container[] arr;
    private int time;

    public LFUCache(int capacity) {
        assert(capacity >= 0);
        lookup = new HashMap<Integer, Container>();
        arr = new Container[capacity];
        time = 0;
    }

    public int get(int key) {
        Container cnt = lookup.get(key);
        if (cnt != null) {
            cnt.freq++;
            cnt.time = this.time++;
            return cnt.value;
        } else {
            return -1;
        }
    }

    public void set(int key, int value) {
        if (lookup.containsKey(key)) {
            Container cnt = lookup.get(key);
            cnt.value = value;
            cnt.freq++;
            cnt.time = this.time++;
        } else if (arr.length > 0) {
            Container cnt = new Container(key, value);
            cnt.time = this.time++;
            int least = 0;
            Container leastCnt = null;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    leastCnt = null;
                    least = i;
                    break;
                }

                if (leastCnt == null) {
                    leastCnt = arr[i];
                    least = i;
                } else if (leastCnt.freq > arr[i].freq) {
                    leastCnt = arr[i];
                    least = i;
                } else if (leastCnt.freq == arr[i].freq && leastCnt.time > arr[i].time) {
                    leastCnt = arr[i];
                    least = i;
                }
            }

            if (leastCnt != null) {
                lookup.remove(leastCnt.key);
                arr[least] = null;
            }
            lookup.put(key, cnt);
            arr[least] = cnt;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.set(key,value);
 */
