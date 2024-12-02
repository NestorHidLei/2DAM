package psp.unidad02.relacion02.actividad01;

import java.util.Iterator;

/**
 * Pretty print prime set
 */
public class PrimePrinter {

  // Prime set
  private PrimeSet primes;
  
  /**
   * Constructor
   * @param primes Prime set to print
   */
  public PrimePrinter(PrimeSet primes) {
    this.primes = primes;
  }
  
  /**
   * Prints prime set to standard output
   */
  public void print() {
    // Obtains iterator over primes
    Iterator<Long> iterator = primes.iterator();
    // While there are more primes to print
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
}
