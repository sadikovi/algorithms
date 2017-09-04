import java.util.ArrayList;
import java.util.Iterator;

// Build code: sbt package
// Run: java -cp target/scala-2.10/algorithms_2.10-0.1-SNAPSHOT.jar Main
class Main {
  public static void main(String[] args) {
    HashMapQueue<Counter> heap = new HashMapQueue<Counter>();
    System.out.println("Initial heap: " + heap);

    ArrayList<String> values = new ArrayList<String>();
    values.add("a");
    values.add("a");

    values.add("b");
    values.add("b");
    values.add("b");

    values.add("c");
    values.add("c");
    values.add("c");
    values.add("c");

    values.add("a");
    values.add("a");
    values.add("a");

    // total a: 5, b: 3, c: 4
    System.out.println("List of values: " + values);

    Iterator<String> iter = values.iterator();
    while (iter.hasNext()) {
      Counter elem = new Counter(iter.next());
      if (heap.contains(elem)) {
        Counter t = heap.storedElem(elem);
        t.inc();
        heap.refresh(t);
      } else {
        elem.inc();
        heap.insertOrRefresh(elem);
      }
      System.out.println("Top 2: " + heap.topK(2));
    }

    // Output:
    /*
    Initial heap: {size: 0, arr: [null, null, null, null, null, null, null, null], map: {}}
    List of values: [a, a, b, b, b, c, c, c, c, a, a, a]
    Top 2: [a[1]]
    Top 2: [a[2]]
    Top 2: [a[2], b[1]]
    Top 2: [b[2], a[2]]
    Top 2: [b[3], a[2]]
    Top 2: [b[3], a[2]]
    Top 2: [b[3], c[2]]
    Top 2: [c[3], b[3]]
    Top 2: [c[4], b[3]]
    Top 2: [c[4], a[3]]
    Top 2: [a[4], c[4]]
    Top 2: [a[5], c[4]]
    */
  }
}
