package test;

/**
val dict = scala.io.Source.fromFile("words.txt").getLines.toSet
class A extends test.Test {
  override def lookup(word: String): Boolean = dict.contains(word)
}
val a = new A()

scala> a.findWord("hellzou")
res0: String = hello
scala> a.findWord("abdkog")
res3: String = dog
scala> a.findWord("created")
res4: String = created
scala> a.findWord("createk")
res5: String = create
*/
public abstract class Test {
  public String findWord(String word) {
    return findWord(word, new java.util.HashSet<String>());
  }

  private String findWord(String word, java.util.HashSet<String> set) {
    if (word == null || word.isEmpty() || set.contains(word)) return null;
    if (lookup(word)) return word;
    set.add(word);
    String min = "";
    for (int i = 0; i < word.length(); i++) {
      String res = findWord(word.substring(0, i) + word.substring(i + 1));
      if (res != null && res.length() > min.length()) {
        min = res;
      }
    }
    return min.isEmpty() ? null : min;
  }

  // Provided dictionary method to look up a word
  // and return true, if word is part of dictionary
  public abstract boolean lookup(String word);
}
