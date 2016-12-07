class Solution {
  public int solution(int N) {
    int i = 2;
    int min = 2*(1+N);

    while (i*i <= N) {
      if (N % i == 0)
        if (min > 2*(i+N/i))
          min = 2*(i+N/i);
      i++;
    }

    return min;
  }
}
