package test;

public class Test {
  public static int flips(int a, int b) {
    int cnt = 0;
    int x = a ^ b;
    while (x != 0) {
      if ((x & 1) != 0) {
        cnt++;
      }
      x >>>= 1;
    }
    return cnt;
  }
}
