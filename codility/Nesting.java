class Solution {
  public int solution(String S) {
    int brackets = 0;
    for (int i=0; i<S.length(); i++) {
      if (S.charAt(i) == '(') {
        brackets++;
      } else {
        if (brackets == 0)
          return 0;
        brackets--;
      }
    }

    return (brackets > 0) ? 0 : 1;
  }
}
