import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  // iterative solution
  public static int chocolate1(int money, int cost, int trade) {
    int total = 0;
    int wrappers = 0;
    while (money >= cost || wrappers >= trade) {
      int choc = money / cost;
      choc += wrappers / trade;
      money %= cost;
      wrappers %= trade;
      wrappers += choc;
      total += choc;
    }
    return total;
  }

  // recursive solution
  public static int chocolate2(int money, int cost, int trade) {
    return chocolateRecur(money, 0, cost, trade);
  }

  public static int chocolateRecur(int money, int wrappers, int cost, int trade) {
    if (money < cost && wrappers < trade) return 0;
    int total = 0;
    if (money >= cost) {
      total += money / cost;
      money %= cost;
    }
    if (wrappers >= trade) {
      total += wrappers / trade;
      wrappers %= trade;
    }
    return total + chocolateRecur(money, wrappers + total, cost, trade);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for(int a0 = 0; a0 < t; a0++){
      int n = in.nextInt();
      int c = in.nextInt();
      int m = in.nextInt();
      System.out.println(chocolate1(n, c, m));
    }
  }
}
