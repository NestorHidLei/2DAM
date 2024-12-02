package psp.unidad02.relacion02.actividad01;

/**
 * Worker thread that performs actual job
 */
public class WorkerThread extends Thread {

  // Range limits
  private long rangeStart;
  private long rangeEnd;
  // Prime set to store found primes
  private PrimeSet set;
  
  /**
   * Constructor. Takes necessary parameters
   * @param rangeStart Start of sub range assigned to this thread (included)
   * @param rangeEnd End of subrange assigned to this thread (included)
   * @param set Set of primes where to store primes found
   */
  public WorkerThread(long rangeStart, long rangeEnd, PrimeSet set) {
    // Stores assigned parameters
    this.rangeStart = rangeStart;
    this.rangeEnd = rangeEnd;
    this.set = set;
  }

  @Override
  public void run() {
    // For each number in range
    for (long number = rangeStart; number <= rangeEnd; number++) {
      // If number is prime
      if (isPrime(number)) {
        // Adds it to set
        set.addPrime(number);
      }
    }
  }

  /**
   * Checks whether given number is prime or not
   * @param number Number to check
   * @return true if number is prime. false otherwise
   */
  private boolean isPrime(long number) {
    // For each number from 2 to number / 2
    for (int divisor = 2; divisor <= (number / 2); divisor++) {
      // If divides exactly
      if (number % divisor == 0) {
        // Not prime
        return false;
      }
    }
    // If not divisor found, then it is prime
    return true;
  }
}
