package psp.unidad02.relacion02.actividad01;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates workers, divides workload between them and collects results
 */
public class WorkerManager {

  // Shared object for collecting results
  private PrimeSet results;
  
  // Worker threads
  private List<WorkerThread> threads;
  
  // Full range limits
  private long rangeStart;
  private long rangeEnd;
  
  /**
   * Constructor with range limits
   * @param rangeStart Start of range for looking for primes 
   * @param rangeEnd   End of range for looking for primes. Must be greater than or equal to rangeStart
   */
  public WorkerManager(long rangeStart, long rangeEnd) {
    // Checks range
    if (rangeStart > rangeEnd)  {
      throw new IllegalArgumentException("Range start can not be greater than range end");
    }
    
    // Stores range limits
    this.rangeStart = rangeStart;
    this.rangeEnd = rangeEnd;
    
    // Creates prime set empty object
    results = new PrimeSet();
    
    // Creates empty worker thread list
    threads = new ArrayList<>();
  }

  /**
   * Performs multithread calculation
   */
  public void performCalculation() {
    // Decides how many threads to create
    int threadCount = Runtime.getRuntime().availableProcessors();
    
    // Sub range size for each thread
    long rangeSize = (rangeEnd - rangeStart + 1) / threadCount;
    // Remaining numbers in case division is not exact
    long remainingNumbers = (rangeEnd - rangeStart + 1) % threadCount;
    
    // Subrange start
    long subRangeStart = 0;
    // For each sub-range
    for (int range = 0; range < threadCount; range++) {
      // Calculates range end
      long subRangeEnd = subRangeStart + rangeSize - 1;
      // If remaining
      if (remainingNumbers > 0) {
        // Adds one to range
        subRangeEnd++;
        // And one less remaining
        remainingNumbers--;
      }
      // Creates thread and passes info
      WorkerThread thread = new WorkerThread(subRangeStart, subRangeEnd, results);
      // Starts it
      thread.start();
      // And adds it to list
      threads.add(thread);
      // Updates sub range start
      subRangeStart = subRangeEnd + 1;
    }
    
    // Threads created and running
    // Waits for all to end
    for (WorkerThread thread: threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {}
    }
  }

  /**
   * Return results
   * @return Set with primes obtained
   */
  public PrimeSet getResults() {
    return results;
  }
 
}
