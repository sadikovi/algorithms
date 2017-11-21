public class Prime {
  private Prime() { }

  public static void printPrimes(int max) {
    boolean[] flags = sieveOfEratosthenes(max);
    System.out.println("== Primes up to " + max + " ==");
    for (int i = 0; i < flags.length; i++) {
      if (flags[i]) {
        System.out.println("* " + i);
      }
    }
  }

  public static boolean[] sieveOfEratosthenes(int max) {
    boolean[] flags = new boolean[max + 1];
    // initialize flags except 0 and 1
    for (int i = 2; i < flags.length; i++) {
      flags[i] = true;
    }

    int prime = 2;
    while (prime * prime <= max) {
      crossOff(flags, prime);
      prime = getNextPrime(flags, prime);
    }

    return flags;
  }

  private static void crossOff(boolean[] flags, int prime) {
    for (int i = prime * prime; i < flags.length; i += prime) {
      flags[i] = false;
    }
  }

  private static int getNextPrime(boolean[] flags, int prime) {
    int next = prime + 1;
    while (next < flags.length && !flags[next]) {
      next++;
    }
    return next;
  }

  public static boolean isPrime(int num) {
    if (num < 2) return false;
    int prime = 2;
    while (prime * prime <= num) {
      if (num % prime == 0) return false;
      ++prime;
    }
    return true;
  }
}
