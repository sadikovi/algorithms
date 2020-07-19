import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
  private static void shift(char[] ascii, int idx) {
    char v = ascii[idx];
    ascii[idx] = ascii[0];
    ascii[0] = v;
  }

  // apply move-to-front encoding, reading from standard input and writing to standard output
  public static void encode() {
    char[] ascii = new char[256];
    for (int i = 0; i < ascii.length; i++) {
      ascii[i] = (char) i;
    }

    while (!BinaryStdIn.isEmpty()) {
      char b = BinaryStdIn.readChar();
      int idx = -1;
      for (int i = 0; i < ascii.length; i++) {
        if (ascii[i] == b) {
          idx = i;
          BinaryStdOut.write((byte) i);
          break;
        }
      }
      if (idx < 0) throw new RuntimeException("Invalid index " + idx);
      shift(ascii, idx);
    }

    BinaryStdIn.close();
    BinaryStdOut.close();
  }

  // apply move-to-front decoding, reading from standard input and writing to standard output
  public static void decode() {
    char[] ascii = new char[256];
    for (int i = 0; i < ascii.length; i++) {
      ascii[i] = (char) i;
    }

    while (!BinaryStdIn.isEmpty()) {
      char b = BinaryStdIn.readChar();
      BinaryStdOut.write(ascii[b]);
      shift(ascii, b);
    }

    BinaryStdIn.close();
    BinaryStdOut.close();
  }

  // if args[0] is "-", apply move-to-front encoding
  // if args[0] is "+", apply move-to-front decoding
  public static void main(String[] args) {
    if (args.length == 1 && args[0].equals("-")) {
      encode();
    } else if (args.length == 1 && args[0].equals("+")) {
      decode();
    } else {
      throw new IllegalArgumentException("Expected one argument '-' or '+'");
    }
  }
}
