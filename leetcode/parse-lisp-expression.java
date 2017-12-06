// My solution to parse Lisp expression, beats 71.27% of submissions.
//
// List of test examples:
// "(add 1 2)"
// "(mult 3 (add 2 3))"
// "(let x 2 (mult x 5))"
// "(let x 2 (add (let x 3 (let x 4 x)) x))"
// "(let x 3 x 2 x)"
// "(let x 1 y 2 x (add x y) (add x y))"
// "(let x 2 (add (let x 3 (let x 4 x)) x))"
// "(let a1 3 b2 (add a1 1) b2)"
// "(let x 7 -12)"
//
// Idea is splitting functions into "parse expression" without parentheses, "extract" that extracts
// curren token (either number or variable or scope) and "parse scope" which an unresolved
// expression with parentheses, note that scope can be a number of variable or an expression, but it
// has to evaluate to an integer result.
//
class Solution {
  public int evaluate(String expression) {
    HashMap<String, Integer> variables = new HashMap<String, Integer>();
    return parseScope(expression, variables);
  }

  private int parseExpression(String exp, HashMap<String, Integer> variables) {
    int i = 0;
    while (i < exp.length() && exp.charAt(i) != ' ') ++i;
    String cmd = exp.substring(0, i);
    while (i < exp.length() && exp.charAt(i) == ' ') ++i;
    if (cmd.equals("add") || cmd.equals("mult")) {
      int end = extract(exp, i);
      String scope1 = exp.substring(i, end+1);
      i = end+1;
      while (i < exp.length() && exp.charAt(i) == ' ') ++i;
      end = extract(exp, i);
      String scope2 = exp.substring(i, end+1);
      if (cmd.equals("add")) {
        return parseScope(scope1, variables) + parseScope(scope2, variables);
      } else {
        return parseScope(scope1, variables) * parseScope(scope2, variables);
      }
    } else {
      List<String> scopes = new ArrayList<String>();
      while (i < exp.length()) {
        while (exp.charAt(i) == ' ') ++i;
        int j = extract(exp, i);
        scopes.add(exp.substring(i, j+1));
        i = j+1;
      }

      HashMap<String, Integer> scopeVariables = new HashMap<String, Integer>(variables);
      int k = 0;
      while (k < scopes.size() - 1) {
        scopeVariables.put(scopes.get(k), parseScope(scopes.get(k+1), scopeVariables));
        k += 2;
      }
      return parseScope(scopes.get(scopes.size() - 1), scopeVariables);
    }
  }

  private int extract(String exp, int i) {
    if (i >= exp.length()) return i;
    int j = i + 1;
    if (exp.charAt(i) == '(') {
      int p = 1;
      while (j < exp.length() && p > 0) {
        if (exp.charAt(j) == '(') p++;
        if (exp.charAt(j) == ')') p--;
        ++j;
      }
    } else {
      while (j < exp.length() && exp.charAt(j) != ' ') ++j;
    }
    return j - 1;
  }

  private int parseScope(String scope, HashMap<String, Integer> variables) {
    if (scope.length() == 0) throw new RuntimeException("Empty scope");
    if (scope.charAt(0) >= '0' && scope.charAt(0) <= '9' || scope.charAt(0) == '-') {
      return Integer.parseInt(scope);
    } else if (scope.charAt(0) >= 'a' && scope.charAt(0) <= 'z') {
      // variable
      return variables.get(scope);
    } else {
      // full scope, has left and right parentheses
      return parseExpression(scope.substring(1, scope.length() - 1), variables);
    }
  }
}
