// Solution for Zuma game, my approach takes 26ms and beats 25.85% of submissions.
// I am not sure what the time and space complexity of this solution is, but each recusrive call
// will take O(n^2 * m), where n is the length of board and m is the length of hand. In reality,
// search space is fairly limited based on problem constraints.
class Solution {
  public int findMinStep(String board, String hand) {
    int[] res = new int[1];
    res[0] = Integer.MAX_VALUE;
    find(board, hand, hand.length(), res, new HashSet<String>());
    return res[0] == Integer.MAX_VALUE ? -1 : res[0];
  }

  private void find(String board, String hand, int total, int[] res, HashSet<String> memo) {
    if (board.length() == 0) {
      res[0] = Math.min(res[0], total - hand.length());
    } else {
      if (memo.contains(board)) return;
      for (int i = 0; i < hand.length(); i++) {
        char ball = hand.charAt(i);
        int j = 0;
        while (j < board.length()) {
          // find run
          while (j < board.length() && board.charAt(j) != ball) j++;
          if (j < board.length()) {
            // insert ball into string
            String newBoard = removeTriplets(board.substring(0, j) + ball + board.substring(j));
            find(newBoard, hand.substring(0, i) + hand.substring(i+1), total, res, memo);
            while (j < board.length() && board.charAt(j) == ball) j++;
          }
        }
      }
      memo.add(board);
    }
  }

  private String removeTriplets(String board) {
    int start = 0, end = 1, count = 1;
    for (end = 1; end < board.length(); end++) {
      if (board.charAt(end) != board.charAt(start)) {
        if (count >= 3) break;
        start = end;
        count = 1;
      } else {
        count++;
      }
    }
    if (count >= 3) {
      return removeTriplets(board.substring(0, start) + board.substring(end));
    } else {
      return board;
    }
  }
}
