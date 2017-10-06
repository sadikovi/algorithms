// Idea of this solution is using breadth-first search to discover open regions that cannot be
// covered.
//
// We start by marking all non-perimeter 'O' elements as unknown '?'. Add remaining 'O' to
// the queue. We know that any element discovered from queue will have to be part of open region.
// As we traverse we simply enqueue any adjacent elements that are '?' and turn them into 'O'.
//
// Note that there might be regions left that are closed and not reached by the traversal. Those
// regions are explicitly marked as 'X'.
//
// runtime O(N * M) and space complexity is O(N + M), where N and M are dimensions of the board.
// O(N * M) is required to check every element on a board, dequeue/enqueue take constant time.
// O(N + M) is required to keep elements in the queue, because at most we will have to keep entire
// perimeter (2 * (N + M)) elements in the queue when we start.
class Solution {
  static class Pair {
    int i = 0, j = 0;
    Pair(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }

  public void solve(char[][] board) {
    if (board.length == 0 || board[0].length == 0) return;
    for (int i = 1; i < board.length - 1; i++) {
      for (int j = 1; j < board[0].length - 1; j++) {
        if (board[i][j] == 'O') board[i][j] = '?';
      }
    }

    // breadth-first search to find adjacent sub-regions
    LinkedList<Pair> queue = new LinkedList<Pair>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == 'O') {
          queue.add(new Pair(i, j));
        }
      }
    }

    while (!queue.isEmpty()) {
      Pair t = queue.remove();
      process(board, t.i + 1, t.j, queue);
      process(board, t.i - 1, t.j, queue);
      process(board, t.i, t.j + 1, queue);
      process(board, t.i, t.j - 1, queue);
    }

    // any remaining regions are closed and can be covered
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == '?') board[i][j] = 'X';
      }
    }
  }

  private void process(char[][] board, int i, int j, LinkedList<Pair> queue) {
    if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;
    if (board[i][j] == '?') {
      board[i][j] = 'O';
      queue.add(new Pair(i, j));
    }
  }
}
