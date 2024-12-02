package psp.unidad02.relacion02.actividad02;

/**
 * Pizzeria worker thread
 */
public class WorkerThread extends Thread {

  // Minimum and maxiimum times to wait
  private static final long MIN_WAIT = 5000;
  private static final long MAX_WAIT = 10000;

  // Thread id
  private int id;
  
  // Shared pizza tray
  Tray tray;
  
  // End flag
  private boolean bEnd;
  
  /**
   * Construtor
   * @param id Worker unique id. Used for messages
   * @param tray Tray where to store the pizzas this worker makes
   */
  public WorkerThread(int id, Tray tray) {
    this.id = id;
    this.tray = tray;
    this.bEnd = false;
  }

  /**
   * Signals worker to end
   */
  public void endJob() {
    this.bEnd = true;
  }
  
  @Override
  public void run() {

    // Starting message
    message("Starting");
    
    // While not ended
    while (!bEnd) {
      // Starts making a pizza
      message("Starting a new pizza");
      // Waits until pizza is ready
      waitPizza();
      // Puts pizza in tray
      int pizzaCount = tray.putPizza();
      // Messages
      message("Pizza Finished and put in tray. Pizzas in tray: " + pizzaCount);
    }
    
    // message
    message("No more clients. Terminating");
  }

  private void waitPizza() {
    // Calculates time to wait
    long timeToWait = (long)(Math.random() * (MAX_WAIT - MIN_WAIT + 1) + MIN_WAIT);
    // And waits it
    try {
      sleep(timeToWait);
    } catch (InterruptedException e) {}
  }

  private void message(String messageText) {
    System.out.println("Worker (" + this.id + ") - " + messageText);
  }
}
