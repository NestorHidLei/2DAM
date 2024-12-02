package psp.unidad02.relacion02.actividad01;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Stores primes
 */
public class PrimeSet implements Iterable {

  // Real storage set
  Set<Long> set;
  
  /**
   * Construct empty prime set
   */
  public PrimeSet() {
    // Uses sorted set for simplifying last sorting
    set = new TreeSet<>();
  }
  
  /**
   * Adds prime to set (synchronized)
   * @param prime Prime to add
   */
  public synchronized void addPrime(long prime) {
    this.set.add(prime);
  }
  
  /**
   * Adds iteration to set
   * @return Iterator over sorted set
   */
  public Iterator<Long> iterator() {
    return this.set.iterator();
  }
}
