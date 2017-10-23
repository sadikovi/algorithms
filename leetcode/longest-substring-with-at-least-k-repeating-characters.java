// I found this problem to be difficult at the time.
// First, I came up with brute-force solution that basically creates all substrings and checks if
// all characters are greater than or equal to k. Then, I tried to build character frequency table
// and turn it into prefix-sums.
//
// Final solution is adapted from Python solution on Leetcode:
// https://discuss.leetcode.com/topic/57092/4-lines-python/2
// Idea is splitting by least-frequent characters (< k) because the longest substring must reside
// between them, and then recurse into small parts of string to find the longest.
//
// Worst time complexity appears to be O(n^2) wih O(n) space due to n recursion levels.
//
class Solution {
    public int longestSubstring(String s, int k) {
        int[] freq = new int[27];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int c = 0; c < freq.length; c++) {
            if (freq[c] > 0 && freq[c] < k) {
                String[] parts = s.split("" + ((char) ('a' + c)));
                int max = 0;
                for (String part : parts) {
                    max = Math.max(longestSubstring(part, k), max);
                }
                return max;
            }
        }
        return s.length();
    }
}
