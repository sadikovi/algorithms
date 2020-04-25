class Solution {
  public int bestRotation(int[] A) {
    if (A == null) return -1; // throw an exception
    // return smallest K with the highest score
    // we can modify array A in place
    int maxScore = computePoints(A), minK = 0;
    for (int K = 1; K < A.length; K++) {
      shiftLeft(A);
      int score = computePoints(A);
      if (maxScore < score) {
        minK = K;
        maxScore = score;
      }
    }

    return minK;
  }

  // Rotates array by shifting to left by 1
  private void shiftLeft(int[] A) {
    int tmp = A[0];
    for (int i = 1; i < A.length; i++) {
      A[i - 1] = A[i];
    }
    A[A.length - 1] = tmp;
  }

  // Computes number of points in array
  private int computePoints(int[] A) {
    int points = 0;
    for (int i = 0; i < A.length; i++) {
      if (A[i] <= i) points++;
    }
    return points;
  }
}
