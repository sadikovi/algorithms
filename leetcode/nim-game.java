// This problem is a bit odd. It turns out that when there are 4 stones on the table first player
// always loses, actually it extends to any X % 4 == 0, such as 8, because they are reduced to the
// 4 stones (both players play optimal strategy). Any other combination would result in the first
// player picking the last stone, since such sitations are reduced to (4 + 3) or (4 + 2) or (4 + 1).

// My first solution
class Solution {
  public boolean canWinNim(int n) {
    if (n <= 3) return true;
    if (n % 4 == 0) return false;
    return true;
  }
}

// My second solution, it is enough to test if n % 4 == 0
class Solution {
  public boolean canWinNim(int n) {
    return n % 4 != 0;
  }
}
