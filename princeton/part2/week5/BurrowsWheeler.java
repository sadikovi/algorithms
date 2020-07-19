import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
  // apply Burrows-Wheeler transform,
  // reading from standard input and writing to standard output
  public static void transform() {
    String s = BinaryStdIn.readString();
    int len = s.length();

    CircularSuffixArray arr = new CircularSuffixArray(s);

    for (int i = 0; i < len; i++) {
      if (arr.index(i) == 0) {
        BinaryStdOut.write(i);
        break;
      }
    }

    for (int i = 0; i < len; i++) {
      char t = s.charAt((arr.index(i) + len - 1) % len);
      BinaryStdOut.write(t);
    }

    BinaryStdIn.close();
    BinaryStdOut.close();
  }

  private static class Index {
    private List<Integer> indices = new ArrayList<Integer>();

    @Override
    public String toString() {
      return indices.toString();
    }
  }

  // apply Burrows-Wheeler inverse transform,
  // reading from standard input and writing to standard output
  public static void inverseTransform() {
    int first = BinaryStdIn.readInt();
    char[] orig = BinaryStdIn.readString().toCharArray();
    int len = orig.length;

    Index[] r = new Index[256]; // ASCII range

    for (int i = 0; i < len; i++) {
      if (r[orig[i]] == null) {
        r[orig[i]] = new Index();
      }
      r[orig[i]].indices.add(i);
    }

    // System.out.println(java.util.Arrays.toString(r));

    char[] sorted = new char[len];
    int k = 0;
    for (int i = 0; i < r.length; i++) {
      int size = r[i] == null ? 0 : r[i].indices.size();
      while (size > 0) {
        sorted[k++] = (char) i;
        size--;
      }
    }

    // System.out.println(java.util.Arrays.toString(sorted));

    int[] next = new int[len];
    int kn = 0;
    for (int i = 0; i < r.length; i++) {
      int size = r[i] == null ? 0 : r[i].indices.size();
      for (int j = 0; j < size; j++) {
        next[kn++] = r[i].indices.get(j);
      }
    }

    // System.out.println(java.util.Arrays.toString(next));

    int tmp = first, iter = 0;
    while (iter < len) {
      BinaryStdOut.write(sorted[tmp]);
      tmp = next[tmp];
      iter++;
    }

    BinaryStdIn.close();
    BinaryStdOut.close();
  }

  // if args[0] is "-", apply Burrows-Wheeler transform
  // if args[0] is "+", apply Burrows-Wheeler inverse transform
  public static void main(String[] args) {
    if (args.length == 1 && args[0].equals("-")) {
      transform();
    } else if (args.length == 1 && args[0].equals("+")) {
      inverseTransform();
    } else {
      throw new IllegalArgumentException("Expected one argument '-' or '+'");
    }
  }
}
