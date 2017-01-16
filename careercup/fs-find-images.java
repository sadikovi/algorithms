package test;

/*
scala> val fs = """|/usr
     | | /local
     | |  profile.jpg
     | |  /bin
     | |   config.txt
     | |   dest.png
     | |  /rbin
     | | image.gif
     | |/sys
     | | /re
     | | /tmp
     | |  pic.jpg""".stripMargin
fs: String =
/usr
 /local
  profile.jpg
  /bin
   config.txt
   dest.png
  /rbin
 image.gif
/sys
 /re
 /tmp
  pic.jpg

scala>

scala> test.Test.findImages(fs)
res0: java.util.List[String] = [/usr/local/profile.jpg, /usr/local/bin/dest.png, /usr/image.gif, /sys/tmp/pic.jpg]
*/

import java.util.ArrayList;
import java.util.List;

public class Test {
  static class TreeNode {
    String data;
    List<TreeNode> ls;

    @Override
    public String toString() {
      return "TreeNode(" + this.data + ") -> " + this.ls;
    }
  }

  private static int depth(String str) {
    int i = 0;
    while (i < str.length() && str.charAt(i) == ' ') {
      i++;
    }
    return i;
  }

  private static void fs(String[] nodes, TreeNode root, int depth, int i, int j) {
    if (root == null || i > j) return;
    List<TreeNode> ls = new ArrayList<TreeNode>();
    for (int k = i; k <= j; k++) {
      if (depth(nodes[k]) == depth) {
        TreeNode node = new TreeNode();
        node.data = nodes[k].trim();
        k++;
        int p = k;
        while (k <= j && depth(nodes[k]) > depth) {
          k++;
        }
        int q = --k;
        fs(nodes, node, depth + 1, p, q);
        ls.add(node);
      }
    }
    root.ls = ls;
  }

  public static TreeNode fs(String[] nodes) {
    TreeNode root = new TreeNode();
    root.data = "";
    fs(nodes, root, 0, 0, nodes.length - 1);
    return root;
  }

  private static List<String> ls(TreeNode root, String[] ext) {
    List<String> list = new ArrayList<String>();
    if (root != null) {
      if (!root.data.isEmpty() && !root.data.startsWith("/")) {
        for (String ex : ext) {
          if (root.data.endsWith(ex)) {
            list.add("/" + root.data);
            break;
          }
        }
      } else if (root.ls != null && !root.ls.isEmpty()) {
        for (TreeNode node : root.ls) {
          List<String> tmp = ls(node, ext);
          for (String a : tmp) {
            list.add(root.data + a);
          }
        }
      }
    }
    return list;
  }

  public static List<String> findImages(String fs) {
    String[] nodes = fs.split("\n"); // split by new line
    TreeNode root = fs(nodes);
    return ls(root, new String[] {".jpg", "png", ".gif"});
  }
}
