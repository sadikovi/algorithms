package test;

/**
scala> map
res6: java.util.HashMap[String,java.util.List[String]] = {AAA=[BBB, CCC, EEE], CCC=[DDD]}

scala> test.Test.printCompany(map)
-AAA
 -BBB
 -CCC
  -DDD
 -EEE
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Test {
  public static void printCompany(HashMap<String, List<String>> graph) {
    if (graph.size() == 0) return;
    HashSet<String> roots = new HashSet<String>();
    for (String v : graph.keySet()) {
      roots.add(v);
    }

    for (String v : graph.keySet()) {
      for (String l : graph.get(v)) {
        roots.remove(l);
      }
    }

    for (String v : roots) {
      printCompany(graph, v, 0);
    }
  }

  private static void printCompany(HashMap<String, List<String>> graph, String vertex, int depth) {
    printVertex(vertex, depth);
    if (graph.containsKey(vertex)) {
      for (String v : graph.get(vertex)) {
        printCompany(graph, v, depth + 1);
      }
    }
  }

  private static void printVertex(String vertex, int depth) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < depth; i++) {
      sb.append(" ");
    }
    sb.append("-");
    sb.append(vertex);
    System.out.println(sb.toString());
  }
}
