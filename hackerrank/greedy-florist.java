/* Sample program illustrating input/output methods */
import java.util.*;

class Solution {
  // time = O(n log n)
  // space = O(k)
  public static int buyFlowersOkay(int[] arr, int k) {
    int[] cust = new int[k];
    Arrays.sort(arr);
    int i = arr.length - 1;
    int rnd = 1;
    while (i >= 0) {
      for (int j = 0; j < k; j++) {
        int price = (i >= 0) ? arr[i] : 0;
        cust[j] += price * rnd;
        i--;
      }
      rnd++;
    }

    int sum = 0;
    for (int s : cust) {
      sum += s;
    }
    return sum;
  }

  // time = O(n log n)
  // space = O(1)
  public static int buyFlowersBetter(int[] arr, int k) {
    int sum = 0;
    int rnd = 1;
    int ktmp = k;
    Arrays.sort(arr);
    for (int i = arr.length - 1; i >= 0; i--) {
      sum += arr[i] * rnd;
      ktmp--;
      if (ktmp == 0) {
        rnd += 1;
        ktmp = k;
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N, K;
    N = in.nextInt();
    K = in.nextInt();

    int[] C = new int[N];
    for (int i = 0; i < N; i++) {
      C[i] = in.nextInt();
    }

    int result = buyFlowersBetter(C, K);
    System.out.println(result);
  }
}
