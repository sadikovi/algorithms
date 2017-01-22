package test;

public class Test {
  public int pairwiseSwap(int value) {
    int even = 0xAAAAAAAA; // 1010...10
    int odd = 0x55555555;  // 0101...01
    int a = (value & even) >>> 1;
    int b = (value & odd) << 1;
    return a | b;
  }
}
