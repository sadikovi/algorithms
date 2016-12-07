class Solution {
  public int solution(int N) {
    int i = 1;
    int cnt = 0;
    while (i*i <= N) {
      if (N % i == 0) {
        if (i*i == N) {
          cnt += 1;
        } else {
          cnt += 2;
        }
      }

      i++;
    }

    return cnt;
  }
}
