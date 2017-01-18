package test;

/*
scala> test.Test.multiply(39998, 39999)
res17: Int = 1599880002

scala> test.Test.multiply2(39998, 39999)
res18: Int = 1599880002

scala> test.Test.multiply3(39998, 39999)
res19: Int = 1599880002
*/

public class Test {
  public static int multiply(int a, int b) {
    int bigger = a < b ? b : a;
    int smaller = a < b ? a : b;
    int product = 0;
    while (smaller > 0) {
      product += bigger;
      smaller--;
    }
    return product;
  }

  public static int multiply2(int a, int b) {
    int bigger = a < b ? b : a;
    int smaller = a < b ? a : b;
    int product = 0;
    int i = 0;
    while (smaller > 0) {
      if ((smaller & 1) == 1) {
        product += bigger << i;
      }
      i++;
      smaller >>= 1;
    }
    return product;
  }

  // Solution from CTCI, runs in O(log s), where s is a smaller of two numbers
  public static int multiply3(int a, int b) {
    int bigger = a < b ? b : a;
    int smaller = a < b ? a : b;
    return minProductHelper(smaller, bigger);
  }

  private static int minProductHelper(int smaller, int bigger) {
    if (smaller == 0) return 0;
    else if (smaller == 1) return bigger;
    int s = smaller >> 1; // divide by 2
    int halfProd = minProductHelper(s, bigger);
    if (smaller % 2 == 0) {
      return halfProd + halfProd;
    } else {
      return halfProd + halfProd + bigger;
    }
  }
}
