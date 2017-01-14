package test;

/**
scala> val cars = Array(1, 2, 3, -1, 4, 5)
scala> val exp = Array(5, 1, -1, 3, 2, 4)
scala> test.Test.shiftCars(cars, exp)
res0: java.util.List[Integer] = [3, 1, 5, 4, 2, 1]

scala> val cars = Array(1, 2, 3, -1, 4, 5)
scala> val exp = Array(1, 2, 3, -1, 4, 5)
scala> test.Test.shiftCars(cars, exp)
res2: java.util.List[Integer] = []

scala> val cars = Array(1, -1, 2, 3, 4, 5, 6)
scala> val exp = Array(1, 4, 5, 3, 2, -1, 6)
scala> test.Test.shiftCars(cars, exp)
res7: java.util.List[Integer] = [4, 2, 5]
*/
public class Test {
  Test() { }

  public static java.util.List<Integer> shiftCars(int[] cars, int[] expected) {
    boolean same = true;
    for (int i = 0; i < cars.length; i++) {
      same = same && cars[i] == expected[i];
    }
    java.util.List<Integer> steps = new java.util.ArrayList<Integer>();
    int minusOnePos = -1;
    for (int i = 0; i < cars.length; i++) {
      if (cars[i] == -1) {
        minusOnePos = i;
        break;
      }
    }

    while (!same) {
      if (expected[minusOnePos] != -1) {
        int pos = find(cars, expected[minusOnePos]);
        steps.add(cars[pos]);
        cars[minusOnePos] = cars[pos];
        cars[pos] = -1;
        minusOnePos = pos;
      } else {
        int pos = findFirstDiff(cars, expected);
        if (pos >= 0) {
          steps.add(cars[pos]);
          cars[minusOnePos] = cars[pos];
          cars[pos] = -1;
          minusOnePos = pos;
        } else {
          same = true;
        }
      }
    }

    return steps;
  }

  private static int find(int[] cars, int value) {
    for (int i = 0; i < cars.length; i++) {
      if (cars[i] == value) return i;
    }
    return -1;
  }

  private static int findFirstDiff(int[] cars, int[] expected) {
    for (int i = 0; i < cars.length; i++) {
      if (cars[i] != -1 && cars[i] != expected[i]) return i;
    }
    return -1;
  }
}
