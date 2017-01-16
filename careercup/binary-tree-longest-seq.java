package test;

/*
Find longest consecutive path in a binary tree.
1. the path can be decreasing or increasing, i.e [1,2,3,4] and [4,3,2,1] are both valid
2. the path can be child-parent-child, not necessarily a parent-to-child path

similar to this question: http://www.geeksforgeeks.org/longest-consecutive-sequence-binary-tree

Tests:
scala> import test.Test._
import test.Test._
scala> def node(data: Int, left: TreeNode, right: TreeNode): TreeNode = {
     |   val root = new TreeNode()
     |   root.data = data
     |   root.left = left
     |   root.right = right
     |   root
     | }
node: (data: Int, left: test.Test.TreeNode, right: test.Test.TreeNode)test.Test.TreeNode
scala> val root = node(9,
     |   node(4,
     |     node(5,
     |       node(6, null, null),
     |       node(4,
     |         node(3,
     |           node(2, null, null),
     |           null
     |         ),
     |         null
     |       )
     |     ),
     |     null
     |   ),
     |   node(10,
     |     node(9,
     |       null,
     |       node(8, null, null)
     |     ),
     |     node(11,
     |       node(12,
     |         null,
     |         node(13, null, null)
     |       ),
     |       null
     |     )
     |   )
     | )
root: test.Test.TreeNode = TreeNode(9, left=TreeNode(4, left=TreeNode(5, left=TreeNode(6, left=null,
  right=null), right=TreeNode(4, left=TreeNode(3, left=TreeNode(2, left=null, right=null),
  right=null), right=null)), right=null), right=TreeNode(10, left=TreeNode(9, left=null,
  right=TreeNode(8, left=null, right=null)), right=TreeNode(11, left=TreeNode(12, left=null,
  right=TreeNode(13, left=null, right=null)), right=null)))

scala> test.Test.findLongestSequence(root)
res0: Int = 6
scala> test.Test.findLongestSequence(null)
res1: Int = 0
scala> test.Test.findLongestSequence(node(4, null, null))
res2: Int = 1
scala> test.Test.findLongestSequence(node(4, node(2, null, null), null))
res3: Int = 1
scala> test.Test.findLongestSequence(node(4, node(3, null, null), null))
res4: Int = 2
scala> test.Test.findLongestSequence(node(3, node(4, null, null), null))
res5: Int = 2
*/

public class Test {
  public static class TreeNode {
    public int data;
    public TreeNode left;
    public TreeNode right;

    @Override
    public String toString() {
      return "TreeNode(" + data + ", left=" + left + ", right=" + right + ")";
    }
  }

  static class Result {
    int max; // longest sequence found so far
    int current; // length of currently maintained sequence
    boolean increasing; // whether or not current sequence is increasing
    int latest; // latest element for sequence (value of tree node)

    Result() {
      this.max = 0;
      this.current = 0;
      this.increasing = false;
      this.latest = Integer.MIN_VALUE;
    }
  }

  private static Result mergeResult(Result res, TreeNode root) {
    if (res == null) {
      res = new Result();
      res.max = 1;
      res.current = 1;
      res.increasing = true;
      res.latest = root.data;
    } else if (Math.abs(res.latest - root.data) == 1) {
      if (res.current == 1) {
        res.current += 1;
        res.max = Math.max(res.max, res.current);
        // set increasing flag in this case
        res.increasing = res.latest < root.data;
      } else if (res.latest < root.data && res.increasing) {
        res.current += 1;
        res.max = Math.max(res.current, res.max);
      } else if (res.latest > root.data && !res.increasing) {
        res.current += 1;
        res.max = Math.max(res.current, res.max);
      } else {
        // root and latest do not match increasing flag, reset current
        res.max = Math.max(res.current, res.max);
        res.current = 1;
      }
      res.latest = root.data;
    } else {
      // reset path
      res.max = Math.max(res.current, res.max);
      res.current = 1;
      res.latest = root.data;
    }
    return res;
  }

  private static Result longest(TreeNode root) {
    if (root == null) return null;
    Result res1 = longest(root.left);
    Result res2 = longest(root.right);
    Result res;
    if (res1 == null && res2 == null) {
      res = mergeResult(null, root);
    } else if (res1 == null || res2 == null) {
      res = mergeResult((res1 == null) ? res2 : res1, root);
    } else {
      Result in1 = mergeResult(res1, root);
      Result in2 = mergeResult(res2, root);
      res = new Result();
      // check child-parent-child path
      if (in1.current > 1 && in2.current > 1 &&
          (in1.increasing && !in2.increasing || !in1.increasing && in2.increasing)) {
        int cpcLen = in1.current + in2.current - 1; // already includes root
        res.max = Math.max(cpcLen, Math.max(in1.max, in2.max));
      } else {
        res.max = Math.max(in1.max, in2.max);
      }

      if (in1.current > in2.current) {
        res.current = in1.current;
        res.increasing = in1.increasing;
      } else {
        res.current = in2.current;
        res.increasing = in2.increasing;
      }
      res.latest = root.data;
    }
    return res;
  }

  public static int findLongestSequence(TreeNode root) {
    Result res = longest(root);
    if (res == null) return 0;
    return Math.max(res.max, res.current);
  }
}
