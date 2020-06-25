import java.util.ArrayList;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
  private static class Move implements Comparable<Move> {
    Board board;
    Move previous;
    int moves;

    Move(Board board, Move previous, int moves) {
      this.board = board;
      this.previous = previous;
      this.moves = moves;
    }

    @Override
    public int compareTo(Move that) {
      return
        (this.board.manhattan() - that.board.manhattan()) +
        (this.moves - that.moves);
    }
  }

  private final Move solution;

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null) throw new IllegalArgumentException();
    this.solution = solve(initial);
  }

  private Move solve(Board initial) {
    MinPQ<Move> queue = new MinPQ<Move>();
    queue.insert(new Move(initial, null, 0));

    MinPQ<Move> queueTwin = new MinPQ<Move>();
    queueTwin.insert(new Move(initial.twin(), null, 0));

    while (!queue.isEmpty() && !queueTwin.isEmpty()) {
      Move move = queue.delMin();
      if (move.board.isGoal()) {
        return move;
      }

      for (Board b : move.board.neighbors()) {
        if (move.previous == null || !move.previous.board.equals(b)) {
          queue.insert(new Move(b, move, move.moves + 1));
        }
      }

      Move moveTwin = queueTwin.delMin();
      if (moveTwin.board.isGoal()) {
        return null;
      }

      for (Board b : moveTwin.board.neighbors()) {
        if (moveTwin.previous == null || !moveTwin.previous.board.equals(b)) {
          queueTwin.insert(new Move(b, moveTwin, moveTwin.moves + 1));
        }
      }
    }

    return null;
  }

  private void add(Move solution, ArrayList<Board> res) {
    if (solution == null) return;
    add(solution.previous, res);
    res.add(solution.board);
  }

  // is the initial board solvable? (see below)
  public boolean isSolvable() {
    return solution != null;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if (!isSolvable()) return -1;
    return solution.moves;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (!isSolvable()) return null;
    ArrayList<Board> boards = new ArrayList<Board>();
    add(solution, boards);
    return boards;
  }

  // test client (see below)
  public static void main(String[] args) {
    // TODO.
  }
}
